package ua.nure.semianikhin.elective.controller;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = {"/index", "/admin", "/coach", "/student"})
public class MainController extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("MainController::doGet - Main controller started");
        process(req, resp, true);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("MainController::doPost - Main controller started");
        process(req, resp, false);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, boolean method) throws ServletException, IOException{
        log.debug("MainController::process - Main controller started");
        String uri = req.getRequestURI();
        log.debug("MainController::process - Get URI from request, URI = " + uri);

        //extract command name from the request
        String commandName = req.getParameter("command");

        log.trace("MainController::process - Get request parameter \"command\": " + commandName);

        //obtain command by name
        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command = commandFactory.createCommand(commandName);
        log.trace("MainController::process - obtained command from CommandFactory" + command.getClass().getName());

        //execute command and get forward address
        String forward = command.execute(req, resp);
        log.trace("MainController::process - Got forward address: " + forward);
        HttpSession session = req.getSession();
        boolean dispatcher = (boolean)session.getAttribute("dispatcher");
        if (forward != null){
            if (dispatcher) {
                log.trace("MainController::process - Use RequestDispatcher.forward");
                log.debug("MainController::process - Main controller finished, go to forward address: " + forward);
                RequestDispatcher rd = req.getRequestDispatcher(forward);
                rd.forward(req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_FOUND);
                String contexPath = req.getContextPath();
                forward = contexPath+forward;
                log.trace("MainController::process - Use Response SendRedirect");
                log.debug("MainController::process - Main controller finished, go to forward address: " + forward);
                resp.sendRedirect(forward);
            }
        }
    }


}

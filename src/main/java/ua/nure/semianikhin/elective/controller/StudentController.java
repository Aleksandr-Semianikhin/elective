package ua.nure.semianikhin.elective.controller;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StudentController extends HttpServlet {
    private static final Logger log = Logger.getLogger(StudentController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("StudentController::doGet - Student controller started");
        process(req, resp, true);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("StudentController::doPost - Student controller started");
        process(req, resp, true);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, boolean method) throws ServletException, IOException{
        //extract command name from the request
        String commandName = req.getParameter("command");
        log.trace("StudentController::process - Get request parameter \"command\": " + commandName);

        //obtain command by name
        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command = commandFactory.createCommand(commandName);
        log.trace("StudentController::process -  - obtained command from CommandFactory" + command);

        //execute command and get forward address
        String forward = command.execute(req, resp);
        log.trace("StudentController::process -  - Got forward address: " + forward);

        log.debug("StudentController::process -  - Main controller finished, go to forward address: " + forward);

        if (forward != null){
            if (method) {
                RequestDispatcher rd = req.getRequestDispatcher(forward);
                rd.forward(req, resp);
            } else {
                String contexPath = req.getContextPath();
                resp.sendRedirect(contexPath+forward);
            }
        }
    }
}

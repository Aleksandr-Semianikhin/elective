package ua.nure.semianikhin.elective.controller;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.command.CommandFactory;
import ua.nure.semianikhin.elective.enteties.Role;
import ua.nure.semianikhin.elective.enteties.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class AdminController extends HttpServlet {
    private static final Logger log = Logger.getLogger(AdminController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("AdminController::doGet - Admin controller started");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("AdminController::doPost - Admin controller started");
        process(req,resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //extract command name from the request
        String commandName = req.getParameter("command");
        log.trace("AdminController::process - Get request parameter \"command\": " + commandName);

        //obtain command by name
        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command = commandFactory.createCommand(commandName);
        log.trace("AdminController::process -  - obtained command from CommandFactory" + command);

        //execute command and get forward address
        String forward = command.execute(req, resp);
        log.trace("AdminController::process -  - Got forward address: " + forward);

        log.debug("AdminController::process -  - Admin controller finished, go to forward address: " + forward);

        if (forward != null){
            RequestDispatcher rd = req.getRequestDispatcher(forward);
            rd.forward(req, resp);
        }
    }
}

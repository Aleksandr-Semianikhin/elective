package ua.nure.semianikhin.elective.command;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.UserDAO;
import ua.nure.semianikhin.elective.enteties.Role;
import ua.nure.semianikhin.elective.enteties.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *Login command.
 *
 *@author Aleksandr Semianikhin
 *
 */

public class LoginCommand implements Command {

    private static final Logger log = Logger.getLogger(LoginCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("LoginCommand::execute - LoginCommand started");
        String forward = null;

        HttpSession session = request.getSession();
        Role userRole = null;
        userRole = (Role) session.getAttribute("userRole");
        log.trace("LoginCommand::execute - Got from session \"userRole\": " + userRole);
        if (userRole != null){
            forward = Path.COMMAND_NO_COMMAND;
            session.setAttribute("dispatcher", false);
            log.debug("LoginCommand::execute - LoginCommand finished");
            return forward;
        }

        // obtain login and password from the request
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        log.trace("LoginCommand::execute - Got from request parameter \"login\": " + userLogin);
        UserDAO userDAO = null;

        if (userLogin == null || userPassword == null || userLogin.isEmpty() || userPassword.isEmpty()){
            log.trace("LoginCommand::execute - Request didn't have login or password back to login page");
            log.debug("LoginCommand::execute - LoginCommand finished");
            session.setAttribute("dispatcher", true);
            forward = Path.LOGIN_PAGE;
            return forward;
        }

        //try to find user in DB
        userDAO =DAOFactory.getUserDAO();
        User user = userDAO.getUserByLogin(userLogin);

        if (user != null && user.isBlocked()){
            String error = "You blocked. Ask administrator for permission";
            request.setAttribute("error", error);
            session.setAttribute("dispatcher", true);
            forward = Path.LOGIN_PAGE;
            return forward;
        }

        if (user == null || !user.getPasw().equals(userPassword)){
            log.trace("LoginCommand::execute - Request had incorrect login or password, back to login page");
            String error = "Your login or password incorrect";
            request.setAttribute("error", error);
            log.trace("LoginCommand::execute - Set to request \"error\": " + error);
            session.setAttribute("dispatcher", true);
            forward = Path.LOGIN_PAGE;
            log.debug("LoginCommand::execute - LoginCommand finished");
            return forward;
        }
        //detect user Role and add user and userRole to session
        userRole = Role.getRole(user);
        session.setAttribute("user", user);
        session.setAttribute("userRole", userRole);
        //forward to MainPage for Role
        if(userRole == Role.ADMIN){
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
            log.debug("LoginCommand::execute - LoginCommand finished");
            return forward;
        }

        if(userRole == Role.COACH){
            forward = Path.COMMAND_COACH_PANEL;
            session.setAttribute("dispatcher", false);
            log.debug("LoginCommand::execute - LoginCommand finished");
            return forward;
        }

        if(userRole == Role.STUDENT){
            forward = Path.COMMAND_STUDENT_PAGE;
            session.setAttribute("dispatcher", false);
            log.debug("LoginCommand::execute - LoginCommand finished");
            return forward;
        }
        log.debug("LoginCommand::execute - LoginCommand finished");
        return forward;
    }
}

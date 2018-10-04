package ua.nure.semianikhin.elective.command;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.enteties.Role;
import ua.nure.semianikhin.elective.enteties.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private static final Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("LogoutCommand::execute - LogoutCommand started");
        String forward = null;
        //Get user and userRole from HttpSession
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role userRole = (Role) session.getAttribute("userRole");
        log.trace("LogoutCommand::execute - Get from request \"user\": " + user);
        log.trace("LogoutCommand::execute - Get from request \"userRole\": " + userRole);
        if (user != null || userRole != null){
            log.trace("LogoutCommand::execute - Invalidate session, forward to Login Page");
            session.setAttribute("user", null);
            session.setAttribute("userRole", null);
        }
        session.setAttribute("dispatcher", false);
        log.debug("LogoutCommand::execute - LogoutCommand finished");
        return Path.COMMAND_NO_COMMAND;
    }
}

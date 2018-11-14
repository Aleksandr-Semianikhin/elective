package ua.nure.semianikhin.elective.command;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.domain.Role;
import ua.nure.semianikhin.elective.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.debug("LogoutCommand::execute - LogoutCommand started");

        //Get user and userRole from HttpSession
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role userRole = (Role) session.getAttribute("userRole");
        log.trace("LogoutCommand::execute - Get from request \"user\": " + user);
        log.trace("LogoutCommand::execute - Get from request \"userRole\": " + userRole);
        if (user != null || userRole != null){
            log.trace("LogoutCommand::execute - Invalidate session, forward to Login Page");
            session.invalidate();
        }
        request.setAttribute("dispatcher", false);
        log.debug("LogoutCommand::execute - LogoutCommand finished");
        return Path.COMMAND_NO_COMMAND;
    }
}

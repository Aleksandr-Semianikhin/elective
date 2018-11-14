package ua.nure.semianikhin.elective.command.admin;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.IUserDAO;
import ua.nure.semianikhin.elective.domain.Role;
import ua.nure.semianikhin.elective.domain.User;
import ua.nure.semianikhin.elective.exception.UserAlreadyExistException;
import ua.nure.semianikhin.elective.services.UserService;
import ua.nure.semianikhin.elective.utils.ValidateData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class AdminActionCoachCommand implements Command {
    private static UserService userService;
    static {
        userService = new UserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("AdminActionCoachCommand::execute - AdminActionCoachCommand started");
        String forward = null;
        //get from request action
        String action = request.getParameter("crud");

        if (action.equals("page")){
            log.trace("AdminActionCoachCommand::execute - Got command to show Create Coach Page");
            forward = Path.ADMIN_CREATE_COACH_PAGE;
        }

        if (action.equals("create")){
            log.trace("AdminActionCoachCommand::execute - Got command to create new Coach");

            boolean isCreateCoach = userService.registerNewCoach(request);
            if (isCreateCoach){
                forward = Path.COMMAND_ADMIN_PAGE;
            } else {
                forward = Path.ADMIN_CREATE_COACH_PAGE;
            }
        }

        return forward;
    }
}

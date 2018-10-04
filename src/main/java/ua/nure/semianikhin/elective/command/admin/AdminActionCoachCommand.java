package ua.nure.semianikhin.elective.command.admin;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.UserDAO;
import ua.nure.semianikhin.elective.enteties.Role;
import ua.nure.semianikhin.elective.enteties.User;
import ua.nure.semianikhin.elective.exception.UserAlreadyExistException;
import ua.nure.semianikhin.elective.utils.ValidateData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminActionCoachCommand implements Command {

    private static final Logger log = Logger.getLogger(AdminActionCoachCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("AdminActionCoachCommand::execute - AdminActionCoachCommand started");
        String forward = null;
        //get from request action
        String action = request.getParameter("crud");
        HttpSession session = request.getSession();
        if (action.equals("page")){
            log.trace("AdminActionCoachCommand::execute - Got command to show Create Coach Page");
            session.setAttribute("dispatcher", true);
            forward = Path.ADMIN_CREATE_COACH_PAGE;
        }

        if (action.equals("create")){
            log.trace("AdminActionCoachCommand::execute - Got command to create new Coach");
            //get login for New Coach from request and validate it
            String login = request.getParameter("login");
            log.trace("AdminActionCoachCommand::execute - Got login from request: " + login);
            //validate login
            boolean canUse = ValidateData.validateNewLogin(login);
            if (!canUse){
                String error = "User with such login exist. Try another one";
                request.setAttribute("error", error);
                session.setAttribute("dispatcher", true);
                forward = Path.ADMIN_CREATE_COACH_PAGE;
            }else{
                String password = request.getParameter("password");
                String firstName = request.getParameter("userFirstName");
                String lastName = request.getParameter("userLastName");
                User user = new User();
                user.setLogin(login);
                user.setPasw(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setRoleId(Role.COACH.ordinal());
                log.trace("AdminActionCoachCommand::execute - create New Coach: " + user);
                UserDAO userDAO = DAOFactory.getUserDAO();
                try {
                    userDAO.createUser(user);
                    session.setAttribute("dispatcher", false);
                    forward = Path.COMMAND_ADMIN_PAGE;
                } catch (UserAlreadyExistException e) {
                    log.error("UserAlreadyExistException in AdminActionCoachCommand::execute - ", e.getCause());
                    String error = "User with such login exist. Try another one";
                    request.setAttribute("error", error);
                    session.setAttribute("dispatcher", true);
                    forward = Path.ADMIN_CREATE_COACH_PAGE;
                }
            }
        }

        return forward;
    }
}

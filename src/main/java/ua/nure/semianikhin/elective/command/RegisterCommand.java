package ua.nure.semianikhin.elective.command;

import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.UserDAO;
import ua.nure.semianikhin.elective.enteties.Role;
import ua.nure.semianikhin.elective.enteties.User;
import ua.nure.semianikhin.elective.exception.UserAlreadyExistException;
import ua.nure.semianikhin.elective.utils.ValidateData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forward = null;
        //Get login from request and check in database
        String login = request.getParameter("login");
        User user = null;
        UserDAO userDAO = DAOFactory.getUserDAO();
        HttpSession session = request.getSession();
        boolean canUse = ValidateData.validateNewLogin(login);
        if (canUse){

            //user with same login not exist in database
            //create and add user with StudentRole to database
            user = new User();
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            user.setRoleId(Role.STUDENT.ordinal());
            user.setLogin(login);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPasw(password);
            try {
                userDAO.createUser(user);
                session.setAttribute("dispatcher", false);
                forward = Path.COMMAND_LOGIN;
            } catch (UserAlreadyExistException e) {
                String error = "Sorry, this username is already busy. Try another one.";
                request.setAttribute("error", error);
                session.setAttribute("dispatcher", true);
                forward = Path.REGISTER_PAGE;
            }
        }else{
            String error = "Sorry, this username is already busy. Try another one.";
            request.setAttribute("error", error);
            session.setAttribute("dispatcher", true);
            forward = Path.REGISTER_PAGE;
        }
        return forward;
    }
}

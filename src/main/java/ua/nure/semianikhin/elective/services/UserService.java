package ua.nure.semianikhin.elective.services;

import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.IUserDAO;
import ua.nure.semianikhin.elective.domain.Role;
import ua.nure.semianikhin.elective.domain.User;
import ua.nure.semianikhin.elective.exception.UserAlreadyExistException;


import javax.servlet.http.HttpServletRequest;

public class UserService {
    private static IUserDAO userDAO;
    static {
        userDAO = DAOFactory.getUserDAO();
    }

    public boolean registerNewStudent(HttpServletRequest request){
        return registerNewUser(request, Role.STUDENT.ordinal());
    }

    public User getUserByLogin(String login){
        return userDAO.getUserByLogin(login);
    }

    public boolean registerNewCoach(HttpServletRequest request) {
        return registerNewUser(request, Role.COACH.ordinal());
    }

    private boolean registerNewUser(HttpServletRequest request, int roleId){
        //Get login from request
        String login = request.getParameter("login");

        //create and add user with StudentRole to database
        User user = new User();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        user.setRoleId(roleId);
        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPasw(password);
        try {
            userDAO.createUser(user);
            return true;
        } catch (UserAlreadyExistException e) {
            String error = "Sorry, this username is already busy. Try another one.";
            request.setAttribute("error", error);
            request.setAttribute("dispatcher", true);
            return false;
        }
    }
}

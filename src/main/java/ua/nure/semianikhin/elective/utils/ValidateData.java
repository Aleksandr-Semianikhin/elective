package ua.nure.semianikhin.elective.utils;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.dao.UserDAO;
import ua.nure.semianikhin.elective.enteties.User;

public class ValidateData {
    private static final Logger log = Logger.getLogger(ValidateData.class);

    public static boolean validateNewLogin(String login){
        log.debug("ValidateData::validateNewLogin - Login validation started");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByLogin(login);
        if (user != null){
            log.debug("ValidateData::validateNewLogin - Login validation finished, return false");
            return false;
        }
        log.debug("ValidateData::validateNewLogin - Login validation finished, return true");
        return true;
    }





    public static String validate(Object obj){
        String validationError = null;
        return validationError;
    }
}

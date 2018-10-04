package ua.nure.semianikhin.elective.dao;

import org.apache.log4j.Logger;

public class DAOFactory {
    private static final Logger log = Logger.getLogger(DAOFactory.class);

    public static CourseDAO getCourseDAO(){
        log.trace("DAOFactory::getCourseDAO - Create and forward new CourseDAO()");
        return new CourseDAO();
    }

    public static RegisterDAO getRegisterDao(){
        log.trace("DAOFactory::getRegisterDAO - Create and forward new RegisterDAO()");
        return new RegisterDAO();
    }

    public static TagDAO getTagDAO(){
        log.trace("DAOFactory::getTagDAO - Create and forward new TagDAO()");
        return new TagDAO();
    }

    public static UserDAO getUserDAO(){
        log.trace("DAOFactory::getUserDAO - Create and forward new UserDAO()");
        return new UserDAO();
    }
}

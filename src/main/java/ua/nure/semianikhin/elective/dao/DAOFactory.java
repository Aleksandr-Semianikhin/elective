package ua.nure.semianikhin.elective.dao;

import ua.nure.semianikhin.elective.dao.MySqlImpl.CourseDAOImplMySql;
import ua.nure.semianikhin.elective.dao.MySqlImpl.RegisterDAOImplMySql;
import ua.nure.semianikhin.elective.dao.MySqlImpl.TagDAOImplMySql;
import ua.nure.semianikhin.elective.dao.MySqlImpl.UserDAOImplMySql;

public class DAOFactory {

    public static ICourseDAO getCourseDAO(){
        return new CourseDAOImplMySql();
    }

    public static IRegisterDAO getRegisterDao(){
        return new RegisterDAOImplMySql();
    }

    public static ITagDAO getTagDAO(){
        return new TagDAOImplMySql();
    }

    public static IUserDAO getUserDAO(){
        return new UserDAOImplMySql();
    }
}

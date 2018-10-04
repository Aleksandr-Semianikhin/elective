package ua.nure.semianikhin.elective.dao;

public class DAOFactory {

    public static CourseDAO getCourseDAO(){
        return new CourseDAO();
    }

    public static RegisterDAO getRegisterDao(){
        return new RegisterDAO();
    }

    public static TagDAO getTagDAO(){
        return new TagDAO();
    }

    public static UserDAO getUserDAO(){
        return new UserDAO();
    }
}

package ua.nure.semianikhin.elective.dao;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.enteties.Course;
import ua.nure.semianikhin.elective.enteties.Status;
import ua.nure.semianikhin.elective.enteties.Tag;
import ua.nure.semianikhin.elective.enteties.User;
import ua.nure.semianikhin.elective.exception.CourseAlreadyExistException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CourseDAO {
    static final Logger log = Logger.getLogger(CourseDAO.class);
    private Properties sqlStatements;
    private EntityMapper mapper;

    public CourseDAO() {
        sqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        mapper = new CourseMapper();
        try{
            sqlStatements.load(inputStream);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in CourseDAO::CourseDAO - can't open properties file", e);
        } catch (IOException e) {
            log.error("IOException in CourseDAO::CourseDAO - can't open properties file", e);
        }
        try{
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException in CourseDAO::CourseDAO - can't close properties file", e);
        }
    }

    public List<Course> getCoursesByTag(int tagId){
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_ALL_COURSE_BY_TAG"));

            ps.setInt(1, tagId);
            rs = ps.executeQuery();
            while (rs.next()){
                Course course = (Course)mapper.mapRow(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            log.error("SQLException in CourseDAO::getCoursesByTag", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getCoursesByTag - can't close Result Set", e);
                }
            }

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getCoursesByTag - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }

        return courses;
    }

    public Course getCourseById(int id){
        Course course = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_COURSE_BY_ID"));

            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()){
                course = (Course)mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            log.error("SQLException in CourseDAO::getCourseById", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getCourseById - can't close Result Set", e);
                }
            }

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getCourseById - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }

        return course;
    }

    public List<Course> getCoursesByCoach(int userId){
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_ALL_COURSE_BY_COACH"));

            ps.setInt(1,userId);
            rs = ps.executeQuery();
            while (rs.next()){
                Course course = (Course)mapper.mapRow(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            log.error("SQLException in CourseDAO::getCoursesByCoach", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getCoursesByCoach - can't close Result Set", e);
                }
            }

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getCoursesByCoach - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }

        return courses;
    }

    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_ALL_COURSE"));

            rs = ps.executeQuery();
            while (rs.next()){
                Course course = (Course)mapper.mapRow(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            log.error("SQLException in CourseDAO::getAllCourses", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getAllCourses - can't close Result Set", e);
                }
            }

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getAllCourses - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }

        return courses;
    }

    public List<Course> getAllCoursesAvailableForRegistrationForUser(User user){
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_ALL_COURSE_AVAILABLE_FOR_REGISTRATION_FOR_USER"));
            ps.setInt(1,user.getIdUser());
            ps.setInt(2,Status.OPENED.ordinal());
            rs = ps.executeQuery();
            while (rs.next()){
                Course course = (Course)mapper.mapRow(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            log.error("SQLException in CourseDAO::getAllCoursesAvailableForRegistrationForUser", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getAllCoursesAvailableForRegistrationForUser - can't close Result Set", e);
                }
            }

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getAllCoursesAvailableForRegistrationForUser - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
        return courses;
    }

    public List<Course> getAllUserCoursesByStatus(User user, Status status){
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_ALL_USER_COURSES_BY_STATUS"));
            ps.setInt(1,user.getIdUser());
            ps.setInt(2,status.ordinal());
            rs = ps.executeQuery();
            while (rs.next()){
                Course course = (Course)mapper.mapRow(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            log.error("SQLException in CourseDAO::getAllUserCoursesByStatus", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getAllUserCoursesByStatus - can't close Result Set", e);
                }
            }

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::getAllUserCoursesByStatus - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
        return courses;
    }

    public void createCourse(Course course) throws CourseAlreadyExistException {
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("CREATE_NEW_COURSE"));

            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCourseCoach().getIdUser());
            ps.setInt(3, course.getStatusId());
            ps.setInt(4, course.getTag().getIdTag());
            ps.setDate(5, course.getStartDate());
            ps.setDate(6, course.getEndDate());
            ps.setInt(7, course.getDays());
            ps.setString(8, course.getDescription());

            ps.executeUpdate();

        }catch (SQLIntegrityConstraintViolationException e){
            log.error("SQLIntegrityConstraintViolationException in CourseDAO::createCourse" +
                    " - Inserting duplicate key", e);
            throw new CourseAlreadyExistException("SQLIntegrityConstraintViolationException in " +
                    "CourseDAO::createCourse - Inserting duplicate key", e);
        }catch (SQLException e) {
            log.error("SQLException in CourseDAO::createCourse", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::createCourse - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    public void updateCourse(Course course) throws CourseAlreadyExistException {
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("UPDATE_COURSE_BY_ID"));

            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCourseCoach().getIdUser());
            ps.setInt(3, course.getStatusId());
            if (course.getTag() != null) {
                ps.setInt(4, course.getTag().getIdTag());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            ps.setDate(5, course.getStartDate());
            ps.setDate(6, course.getEndDate());
            ps.setInt(7, course.getDays());
            ps.setString(8, course.getDescription());
            ps.setInt(9, course.getIdCourse());

            ps.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e){
            log.error("SQLIntegrityConstraintViolationException in CourseDAO::updateCourse" +
                    " - Inserting duplicate key", e);
            throw new CourseAlreadyExistException("SQLIntegrityConstraintViolationException in " +
                    "CourseDAO::updateCourse - Inserting duplicate key", e);
        } catch (SQLException e) {
            log.error("SQLException in CourseDAO::updateCourse", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::updateCourse - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    public void updateCountStudents(int courseId, int newCount){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("UPDATE_COURSE_COUNT_STUDENTS_BY_ID"));

            ps.setInt(1, newCount);
            ps.setInt(2, courseId);

            ps.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException in CourseDAO::updateCourse", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::updateCourse - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    public void deleteCourse(Course course){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("DELETE_COURSE_BY_ID"));

            ps.setInt(1, course.getIdCourse());

            ps.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException in CourseDAO::deleteCourse", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in CourseDAO::deleteCourse - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }


    private static class CourseMapper implements EntityMapper<Course>{

        @Override
        public Course mapRow(ResultSet rs) {
            try{
                Course course = new Course();
                course.setIdCourse(rs.getInt(Fields.COURSE_ID));
                course.setCourseName(rs.getString(Fields.COURSE_NAME));
                User user = DAOFactory.getUserDAO().getUserById(rs.getInt(Fields.COURSE_COACH));
                course.setCourseCoach(user);
                course.setStatusId(rs.getInt(Fields.COURSE_STATUS_ID));
                Tag tag = DAOFactory.getTagDAO().getTagById(rs.getInt(Fields.COURSE_TAG_ID));
                course.setTag(tag);
                course.setStartDate(rs.getDate(Fields.COURSE_START_DATE));
                course.setEndDate(rs.getDate(Fields.COURSE_END_DATE));
                course.setDays(rs.getInt(Fields.COURSE_DAYS));
                course.setCountStudents(rs.getInt(Fields.COURSE_COUNT_STUDENTS));
                course.setDescription(rs.getString(Fields.COURSE_DESCRIPTION));
                return course;
            } catch (SQLException e) {
                log.error("SQLException in CourseDao::CourseMapper::mapRow",e);
                throw new IllegalStateException();
            }
        }
    }

}

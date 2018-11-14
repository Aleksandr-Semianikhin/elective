package ua.nure.semianikhin.elective.dao.MySqlImpl;

import lombok.Synchronized;
import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.ICourseDAO;
import ua.nure.semianikhin.elective.dao.Mappers.CourseListMapper;
import ua.nure.semianikhin.elective.dao.Mappers.CourseMapper;
import ua.nure.semianikhin.elective.dao.executor.Executor;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.domain.Status;
import ua.nure.semianikhin.elective.exception.CourseAlreadyExistException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Properties;

@Log4j
public class CourseDAOImplMySql implements ICourseDAO {
    private Properties sqlStatements;
    private Executor executor;

    public CourseDAOImplMySql() {
        executor = Executor.getInstance();
        sqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        try{
            sqlStatements.load(inputStream);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in CourseDAOImplMySql::CourseDAOImplMySql - can't open properties file", e);
        } catch (IOException e) {
            log.error("IOException in CourseDAOImplMySql::CourseDAOImplMySql - can't open properties file", e);
        }
        try{
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException in CourseDAOImplMySql::CourseDAOImplMySql - can't close properties file", e);
        }
    }

    public List<Course> getCoursesByTag(Integer tagId){
        String query = sqlStatements.getProperty("READ_ALL_COURSE_BY_TAG");
        return executor.executeQuery(query, new CourseListMapper(), tagId);
    }

    public Course getCourseById(Integer id){
        String query = sqlStatements.getProperty("READ_COURSE_BY_ID");
        return executor.executeQuery(query, new CourseMapper(), id);
    }

    public List<Course> getCoursesByCoach(Integer userId){
        String query = sqlStatements.getProperty("READ_ALL_COURSE_BY_COACH");
        return executor.executeQuery(query, new CourseListMapper(), userId);
    }

    public List<Course> getAllCourses(){
        String query = sqlStatements.getProperty("READ_ALL_COURSE");
        return executor.executeQuery(query, new CourseListMapper());
    }

    public List<Course> getAllCoursesAvailableForRegistrationForUser(Integer userId){
        String query = sqlStatements.getProperty("READ_ALL_COURSE_AVAILABLE_FOR_REGISTRATION_FOR_USER");
        return executor.executeQuery(query, new CourseListMapper(), userId, Status.OPENED.ordinal());
    }

    public List<Course> getAllUserCoursesByStatus(Integer userId, Status status){
        String query = sqlStatements.getProperty("READ_ALL_USER_COURSES_BY_STATUS");
        return executor.executeQuery(query, new CourseListMapper(), userId, status.ordinal());
    }

    public void createCourse(Course course) throws CourseAlreadyExistException {
        String query = sqlStatements.getProperty("CREATE_NEW_COURSE");
        try {
            executor.executeUpdate(query, course.getCourseName(), course.getCourseCoach().getIdUser(),
                    course.getStatusId(), course.getTag().getIdTag(), course.getStartDate(),
                    course.getEndDate(), course.getDays(), course.getDescription());
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in CourseDAOImplMySql::createCourse - can't create course");
            throw new CourseAlreadyExistException("SQLIntegrityConstraintViolationException in " +
                    "CourseDaoImplMySql::createCourse - Inserting duplicate key", e);
        }
    }

    public void updateCourse(Course course) throws CourseAlreadyExistException {
        String query = sqlStatements.getProperty("UPDATE_COURSE_BY_ID");
        try {
            executor.executeUpdate(query, course.getCourseName(), course.getCourseCoach().getIdUser(),
                    course.getStatusId(), course.getTag().getIdTag(), course.getStartDate(),
                    course.getEndDate(), course.getDays(), course.getDescription(), course.getIdCourse());
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in CourseDAOImplMySql::updateCourse - can't update course");
            throw new CourseAlreadyExistException("SQLIntegrityConstraintViolationException in " +
                    "CourseDaoImplMySql::updateCourse - Inserting duplicate key", e);
        }
    }

    public void updateCountStudents(Integer courseId, Integer newCount) {
        String query = sqlStatements.getProperty("UPDATE_COURSE_COUNT_STUDENTS_BY_ID");
        try {
            executor.executeUpdate(query, newCount, courseId);
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in CourseDAOImplMySql::updateCountStudents" +
                    " - can't update count of students");
            throw new IllegalStateException(e);
        }
    }

    public void deleteCourse(Course course){
        String query = sqlStatements.getProperty("DELETE_COURSE_BY_ID");
        try {
            executor.executeUpdate(query, course.getIdCourse());
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in CourseDAOImplMySql::deleteCourse" +
                    " - can't delete course");
            throw new IllegalStateException(e);
        }
    }

}

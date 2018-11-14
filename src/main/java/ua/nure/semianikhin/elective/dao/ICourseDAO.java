package ua.nure.semianikhin.elective.dao;

import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.domain.Status;
import ua.nure.semianikhin.elective.domain.User;
import ua.nure.semianikhin.elective.exception.CourseAlreadyExistException;

import java.util.List;

public interface ICourseDAO {
    List<Course> getCoursesByTag(Integer tagId);
    List<Course> getCoursesByCoach(Integer userId);
    List<Course> getAllCourses();
    List<Course> getAllCoursesAvailableForRegistrationForUser(Integer userId);
    List<Course> getAllUserCoursesByStatus(Integer userId, Status status);
    Course getCourseById(Integer id);
    void createCourse(Course course) throws CourseAlreadyExistException;
    void updateCourse(Course course) throws CourseAlreadyExistException;
    void updateCountStudents(Integer courseId, Integer newCount);
    void deleteCourse(Course course);
}

package ua.nure.semianikhin.elective.dao.Mappers;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.executor.EntityMapper;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.domain.Tag;
import ua.nure.semianikhin.elective.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CourseListMapper implements EntityMapper<List<Course>> {
    @Override
    public List<Course> mapRow(ResultSet rs) {
        final List<Course> courses = new ArrayList<>();
        try {
            while(rs.next()){
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
                courses.add(course);
            }
        } catch (SQLException e){
            log.error("SQLException in CourseListMapper::mapRow",e);
        }
        return courses;
    }
}

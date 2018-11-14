package ua.nure.semianikhin.elective.dao.Mappers;


import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.executor.EntityMapper;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.domain.Tag;
import ua.nure.semianikhin.elective.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j
public class CourseMapper implements EntityMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs) {
        try {
            if(rs.next()){
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
            }
        } catch (SQLException e){
            log.error("SQLException in CourseMapper::mapRow",e);
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }
}

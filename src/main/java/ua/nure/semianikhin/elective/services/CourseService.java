package ua.nure.semianikhin.elective.services;


import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.ICourseDAO;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Log4j
public class CourseService {
    private static ICourseDAO courseDAO;
    static{
        courseDAO = DAOFactory.getCourseDAO();
    }

    public List<Course> getAllCourses(HttpServletRequest request){
        String sort = request.getParameter("sort");
        List<Course> courses = courseDAO.getAllCourses();
        if (sort != null && !sort.isEmpty()) {
            HttpSession session = request.getSession();
            Comparator<Course> comparator = Utils.getComparator(sort, session);
            courses.sort(comparator);
        }
        return courses;
    }

    public List<Course> getAllCourseByTag(HttpServletRequest request){
        int tagId = Integer.parseInt(request.getParameter("tagId"));
        return courseDAO.getCoursesByTag(tagId);
    }

    public List<Course> getAllCourseByCoach(HttpServletRequest request) {
        int coachId = Integer.parseInt(request.getParameter("coachId"));
        return courseDAO.getCoursesByCoach(coachId);
    }

}

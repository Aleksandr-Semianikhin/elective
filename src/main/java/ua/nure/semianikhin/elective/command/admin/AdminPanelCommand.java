package ua.nure.semianikhin.elective.command.admin;

import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.*;
import ua.nure.semianikhin.elective.dao.MySqlImpl.CourseDAOImplMySql;
import ua.nure.semianikhin.elective.dao.MySqlImpl.UserDAOImplMySql;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.domain.Role;
import ua.nure.semianikhin.elective.domain.Tag;
import ua.nure.semianikhin.elective.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminPanelCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //get all coaches and their course
        HttpSession session = request.getSession();
        String forward = null;
        List<User> listCoachs = null;
        List<User> students = null;
        List<Course> courses = null;
        List<Tag> tags = null;
        IUserDAO userDAO = DAOFactory.getUserDAO();
        ICourseDAO courseDAO = DAOFactory.getCourseDAO();
        ITagDAO tagDao = DAOFactory.getTagDAO();
        tags = tagDao.getAllTags();
        students = userDAO.getUsersByRole(Role.STUDENT.ordinal());
        courses = courseDAO.getAllCourses();
        listCoachs = userDAO.getUsersByRole(Role.COACH.ordinal());
        //Create map with entry Coach - Courses Name
        Map<User, String> coaches = new HashMap<>();
        for (User user : listCoachs){
            StringBuilder sb = new StringBuilder();
            for (Course course : courses){
                if (course.getCourseCoach().getIdUser() == user.getIdUser()){
                    sb.append(course.getCourseName()).append(", ");
                }
            }
            if (!sb.toString().isEmpty()) {
                sb.setLength(sb.length() - 2);
                coaches.put(user, sb.toString());
            } else {
                coaches.put(user, "Coach doesn't have any courses");
            }

        }
        request.setAttribute("coaches", coaches);
        request.setAttribute("courses", courses);
        request.setAttribute("students", students);
        request.setAttribute("tags", tags);

        session.setAttribute("dispatcher", true);
        forward = Path.ADMIN_PAGE;

        return forward;

    }
}

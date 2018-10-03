package ua.nure.semianikhin.elective.command.student;

import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.CourseDAO;
import ua.nure.semianikhin.elective.dao.DAOFactory;

import ua.nure.semianikhin.elective.dao.RegisterDAO;
import ua.nure.semianikhin.elective.enteties.Course;
import ua.nure.semianikhin.elective.enteties.Register;
import ua.nure.semianikhin.elective.enteties.Status;
import ua.nure.semianikhin.elective.enteties.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentPanelCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //get student from session
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String forward = Path.STUDENT_PAGE;
        //courses available for registration
        List<Course> coursesAvailable = null;
        //courses with status = OPENED which student register
        List<Course> coursesOpened = null;
        //courses with status = STARTED which student register
        List<Course> coursesStarted = null;
        //courses with status = ENDED which student register
        List<Register> coursesEnded = null;

        //get all opened courses where student haven't registered yet
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        RegisterDAO registerDAO = DAOFactory.getRegisterDao();
        coursesAvailable = courseDAO.getAllCoursesAvaibleForRegistrationForUser(user);
        coursesOpened = courseDAO.getAllUserCoursesByStatus(user, Status.OPENED);
        coursesStarted = courseDAO.getAllUserCoursesByStatus(user, Status.STARTED);
        coursesEnded = registerDAO.getAllEntryForUserByStatus(user.getIdUser(), Status.ENDED);

        //set all necessary lists and map to request
        session.setAttribute("dispatcher", true);
        request.setAttribute("coursesAvailable", coursesAvailable);
        request.setAttribute("coursesOpened", coursesOpened);
        request.setAttribute("coursesStarted", coursesStarted);
        request.setAttribute("coursesEnded", coursesEnded);
        return forward;
    }
}

package ua.nure.semianikhin.elective.command.coach;

import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.CourseDAO;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.enteties.Course;
import ua.nure.semianikhin.elective.enteties.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CoachPanelCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //get coach from session
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String forward = Path.COACH_PAGE;
        //get all own courses with different status
        List<Course> courses = null;
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        courses = courseDAO.getCoursesByCoach(user.getIdUser());
        session.setAttribute("dispatcher", true);
        //set all necessary list and data to request
        request.setAttribute("courses", courses);
        return forward;
    }
}

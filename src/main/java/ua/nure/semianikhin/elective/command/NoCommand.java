package ua.nure.semianikhin.elective.command;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.dao.CourseDAO;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.enteties.Course;
import ua.nure.semianikhin.elective.enteties.Role;
import ua.nure.semianikhin.elective.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class NoCommand implements Command {

    private static final Logger log = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("NoCommand::execute - NoCommand started");
        String forward = null;
        //get userRole from HttpSession
        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute("userRole");
        log.trace("NoCommand::execute - Get from HttpSession User Role: " + userRole);
        if (userRole == Role.ADMIN){
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
        } else if (userRole == Role.COACH){
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_COACH_PANEL;
        } else if (userRole == Role.STUDENT){
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_STUDENT_PAGE;
        } else {
            log.trace("NoCommand::execute - User not registered");
            String sort = request.getParameter("sort");
            List<Course> courses = null;
            CourseDAO courseDAO = DAOFactory.getCourseDAO();
            courses = courseDAO.getAllCourses();
            if (sort != null && !sort.isEmpty()) {
                Comparator<Course> comparator = Utils.getComparator(sort, session);
                courses.sort(comparator);
            }
            request.setAttribute("courses", courses);
            session.setAttribute("dispatcher", true);
            forward = Path.INDEX_PAGE;
        }
        log.trace("NoCommand::execute - Get \"forward\" = " + forward);
        log.debug("NoCommand::execute - NoCommand finished");
        return forward;
    }
}

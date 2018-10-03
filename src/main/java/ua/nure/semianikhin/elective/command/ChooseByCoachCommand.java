package ua.nure.semianikhin.elective.command;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.dao.CourseDAO;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.enteties.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ChooseByCoachCommand implements Command {

    private static final Logger log = Logger.getLogger(ChooseByCoachCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("ChooseByCoachCommand::execute - ChooseByCoachCommand started");
        String forward = null;
        HttpSession session = request.getSession();
        int coachId = Integer.parseInt(request.getParameter("coachId"));
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        List<Course> courses = courseDAO.getCoursesByCoach(coachId);
        request.setAttribute("courses", courses);
        session.setAttribute("dispatcher", true);
        forward = Path.INDEX_COACH_PAGE;
        log.trace("ChooseByCoachCommand::execute - Got \"forward\":" + forward);
        log.debug("ChooseByCoachCommand::execute - ChooseByCoachCommand finished");
        return forward;
    }
}

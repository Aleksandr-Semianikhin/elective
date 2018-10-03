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

public class ChooseByTagCommand implements Command {

    private static final Logger log = Logger.getLogger(ChooseByTagCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("ChooseByTagCommand::execute - ChooseByTagCommand started");
        String forward = null;
        HttpSession session = request.getSession();
        //get idTag from request
        int tagId = Integer.parseInt(request.getParameter("tagId"));
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        List<Course> courses = courseDAO.getCoursesByTag(tagId);
        request.setAttribute("courses", courses);
        session.setAttribute("dispatcher", true);
        forward = Path.INDEX_TAG_PAGE;
        log.trace("ChooseByTagCommand::execute - Got \"forward\":" + forward);
        log.debug("ChooseByTagCommand::execute - ChooseByTagCommand finished");
        return forward;
    }
}

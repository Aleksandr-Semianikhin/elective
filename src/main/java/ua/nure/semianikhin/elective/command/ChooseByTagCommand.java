package ua.nure.semianikhin.elective.command;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.ICourseDAO;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.services.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j
public class ChooseByTagCommand implements Command {
    private static CourseService courseService;
    static {
        courseService = new CourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("ChooseByTagCommand::execute - ChooseByTagCommand started");
        //get idTag from request
        List<Course> courses = courseService.getAllCourseByTag(request);
        request.setAttribute("courses", courses);
        String forward = Path.INDEX_TAG_PAGE;
        log.trace("ChooseByTagCommand::execute - Got \"forward\":" + forward);
        log.debug("ChooseByTagCommand::execute - ChooseByTagCommand finished");
        return forward;
    }
}

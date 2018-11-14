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
public class ChooseByCoachCommand implements Command {

    private static CourseService courseService;

    static {
        courseService = new CourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("ChooseByCoachCommand::execute - ChooseByCoachCommand started");
        String forward;
        List<Course> courses = courseService.getAllCourseByCoach(request);
        request.setAttribute("courses", courses);
        forward = Path.INDEX_COACH_PAGE;
        log.trace("ChooseByCoachCommand::execute - Got \"forward\":" + forward);
        log.debug("ChooseByCoachCommand::execute - ChooseByCoachCommand finished");
        return forward;
    }
}

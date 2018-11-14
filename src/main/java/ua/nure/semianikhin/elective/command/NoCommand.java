package ua.nure.semianikhin.elective.command;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.domain.Role;
import ua.nure.semianikhin.elective.services.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j
public class NoCommand implements Command {
    private static CourseService courseService;
    static{
        courseService = new CourseService();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("NoCommand::execute - NoCommand started");
        String forward = null;
        //get userRole from HttpSession
        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute("userRole");
        log.trace("NoCommand::execute - Get from HttpSession User Role: " + userRole);
        if (userRole == Role.ADMIN){
            request.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
        } else if (userRole == Role.COACH){
            request.setAttribute("dispatcher", false);
            forward = Path.COMMAND_COACH_PANEL;
        } else if (userRole == Role.STUDENT){
            request.setAttribute("dispatcher", false);
            forward = Path.COMMAND_STUDENT_PAGE;
        } else {
            log.trace("NoCommand::execute - User not registered");
            List<Course> courses = courseService.getAllCourses(request);
            request.setAttribute("courses", courses);
            forward = Path.INDEX_PAGE;
        }
        log.trace("NoCommand::execute - Get \"forward\" = " + forward);
        log.debug("NoCommand::execute - NoCommand finished");
        return forward;
    }
}

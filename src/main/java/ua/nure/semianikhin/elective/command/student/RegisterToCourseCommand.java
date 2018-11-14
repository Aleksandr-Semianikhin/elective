package ua.nure.semianikhin.elective.command.student;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.*;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterToCourseCommand implements Command {

    private static final Logger log = Logger.getLogger(RegisterToCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("RegisterToCourseCommand::execute - RegisterToCourseCommand started");
        String forward = null;
        int courseId = Integer.parseInt(request.getParameter("course"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getIdUser();
        IRegisterDAO registerDAO = DAOFactory.getRegisterDao();
        registerDAO.createEntryInRegister(userId, courseId);
        //increment course's count
        ICourseDAO courseDAO = DAOFactory.getCourseDAO();
        Course course = courseDAO.getCourseById(courseId);
        int count = course.getCountStudents();
        courseDAO.updateCountStudents(courseId, count+1);
        session.setAttribute("dispatcher", false);
        forward = Path.COMMAND_STUDENT_PAGE;
        log.trace("RegisterToCourseCommand::execute - Got \"forward\":" + forward);
        log.debug("RegisterToCourseCommand::execute - AdminActionTagCommand finished");
        return forward;
    }
}

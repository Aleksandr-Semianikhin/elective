package ua.nure.semianikhin.elective.command.coach;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.IRegisterDAO;
import ua.nure.semianikhin.elective.dao.MySqlImpl.RegisterDAOImplMySql;
import ua.nure.semianikhin.elective.domain.Register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetCourseUsersCommand implements Command {

    private static final Logger log = Logger.getLogger(GetCourseUsersCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("GetCourseUsersCommand::execute - GetCourseUsersCommand started");
        String forward = null;
        HttpSession session = request.getSession();
        int courseId = Integer.parseInt(request.getParameter("course"));
        IRegisterDAO registerDAO = DAOFactory.getRegisterDao();
        List<Register> entries = registerDAO.getAllEntryByCourse(courseId);
        request.setAttribute("entries", entries);
        session.setAttribute("dispatcher", true);
        forward = Path.COURSE_USER_PAGE;
        log.trace("GetCourseUsersCommand::execute - Got \"forward\":" + forward);
        log.debug("GetCourseUsersCommand::execute - GetCourseUsersCommand finished");
        return forward;
    }
}

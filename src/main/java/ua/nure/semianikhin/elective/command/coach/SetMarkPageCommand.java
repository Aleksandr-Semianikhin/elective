package ua.nure.semianikhin.elective.command.coach;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.RegisterDAO;
import ua.nure.semianikhin.elective.enteties.Register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SetMarkPageCommand implements Command {
    private static final Logger log = Logger.getLogger(SetMarkPageCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("SetMarkPageCommand::execute - SetMarkPageCommand started");
        String forward = null;
        HttpSession session = request.getSession();
        int courseId = Integer.parseInt(request.getParameter("course"));
        RegisterDAO registerDAO = DAOFactory.getRegisterDao();
        List<Register> entries = registerDAO.getAllEntryByCourse(courseId);
        request.setAttribute("entries", entries);
        session.setAttribute("dispatcher", true);
        forward = Path.SET_MARK_PAGE;
        log.trace("SetMarkPageCommand::execute - Got \"forward\":" + forward);
        log.debug("SetMarkPageCommand::execute - SetMarkPageCommand finished");
        return forward;
    }
}

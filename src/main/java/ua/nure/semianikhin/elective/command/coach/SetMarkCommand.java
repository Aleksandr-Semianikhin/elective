package ua.nure.semianikhin.elective.command.coach;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.IRegisterDAO;
import ua.nure.semianikhin.elective.dao.MySqlImpl.RegisterDAOImplMySql;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetMarkCommand implements Command {

    private static final Logger log = Logger.getLogger(SetMarkCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("SetMarkCommand::execute - SetMarkCommand started");
        HttpSession session = request.getSession();
        String forward = null;
        String[] marks = request.getParameterValues("marks");
        String[] ids = request.getParameterValues("ids");
        IRegisterDAO registerDAO = DAOFactory.getRegisterDao();
        if (marks != null && marks.length == ids.length){
            for (int i = 0; i < marks.length; i++) {
                int id = Integer.parseInt(ids[i]);
                double mark = Double.parseDouble(marks[i]);
                registerDAO.setUserMarkByIdEntry(id, mark);
            }
        }
        session.setAttribute("dispatcher", false);
        forward = Path.COMMAND_COACH_PANEL;
        log.trace("SetMarkCommand::execute - Got \"forward\":" + forward);
        log.debug("SetMarkCommand::execute - SetMarkCommand finished");
        return forward;
    }
}

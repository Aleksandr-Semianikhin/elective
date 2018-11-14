package ua.nure.semianikhin.elective.command.admin;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.IUserDAO;
import ua.nure.semianikhin.elective.dao.MySqlImpl.UserDAOImplMySql;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BlockedStudentCommand implements Command {
    private static final Logger log = Logger.getLogger(BlockedStudentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("BlockedStudentCommand::execute - Blocked Student Command started");
        String forward = null;
        HttpSession session = request.getSession();
        String state = request.getParameter("state");
        int studentId = Integer.parseInt(request.getParameter("student"));
        boolean blockedFlag=false;
        if ("true".equals(state)){
            blockedFlag = true;
        }
        log.trace("BlockedStudentCommand::execute - Get flag=" + blockedFlag + " for user with id=" + studentId);
        IUserDAO userDAO = DAOFactory.getUserDAO();
        userDAO.setBlockedFlag(blockedFlag, studentId);
        session.setAttribute("dispatcher", false);
        forward = Path.COMMAND_ADMIN_PAGE;
        return forward;
    }
}

package ua.nure.semianikhin.elective.command.admin;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.ITagDAO;
import ua.nure.semianikhin.elective.domain.Tag;
import ua.nure.semianikhin.elective.exception.TagAlreadyExistException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class AdminActionTagCommand implements Command{

    private static final Logger log = Logger.getLogger(AdminActionTagCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("AdminActionTagCommand::execute - AdminActionTagCommand started");
        String forward = null;
        //get action from request
        String action = request.getParameter("crud");
        ITagDAO tagDao = DAOFactory.getTagDAO();
        HttpSession session = request.getSession();
        if ("createPage".equals(action)){
            session.setAttribute("dispatcher", true);
            forward = Path.ADMIN_CREATE_TAG_PAGE;
        }

        if("create".equals(action)){
            String name = request.getParameter("name");
            Tag tag = new Tag();
            tag.setName(name);
            try {
                tagDao.createTag(tag);
            } catch (TagAlreadyExistException e) {
                e.printStackTrace();
            }
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
        }

        if ("editPage".equals(action)){
            int tagId = Integer.parseInt(request.getParameter("tag"));
            Tag tag = null;
            tag = tagDao.getTagById(tagId);
            request.setAttribute("tag", tag);
            session.setAttribute("dispatcher", true);
            forward = Path.ADMIN_EDIT_TAG_PAGE;
        }

        if ("edit".equals(action)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int tagId = Integer.parseInt(request.getParameter("tag"));
            Tag tag = new Tag();
            tag.setName(name);
            tag.setIdTag(tagId);
            try {
                tagDao.updateTag(tag);
            } catch (TagAlreadyExistException e) {
                e.printStackTrace();
            }
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
        }

        if ("delete".equals(action)){
            Integer tagId = Integer.parseInt(request.getParameter("tag"));
            tagDao.deleteTag(tagId);
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
        }
        session.setAttribute("dispatcher", true);
        log.trace("AdminActionTagCommand::execute - Got \"forward\":" + forward);
        log.debug("AdminActionTagCommand::execute - AdminActionTagCommand finished");
        return forward;
    }
}

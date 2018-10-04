package ua.nure.semianikhin.elective.command.admin;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.TagDAO;
import ua.nure.semianikhin.elective.enteties.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminActionTagCommand implements Command{

    private static final Logger log = Logger.getLogger(AdminActionTagCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("AdminActionTagCommand::execute - AdminActionTagCommand started");
        String forward = null;
        //get action from request
        String action = request.getParameter("crud");
        TagDAO tagDAO = DAOFactory.getTagDAO();
        HttpSession session = request.getSession();
        if ("createPage".equals(action)){
            session.setAttribute("dispatcher", true);
            forward = Path.ADMIN_CREATE_TAG_PAGE;
        }

        if("create".equals(action)){
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Tag tag = new Tag();
            tag.setName(name);
            tag.setDescription(description);
            tagDAO.createTag(tag);
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
        }

        if ("editPage".equals(action)){
            int tagId = Integer.parseInt(request.getParameter("tag"));
            Tag tag = tagDAO.getTagById(tagId);
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
            tag.setDescription(description);
            tag.setIdTag(tagId);
            tagDAO.updateTag(tag);
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
        }

        if ("delete".equals(action)){
            int tagId = Integer.parseInt(request.getParameter("tag"));
            tagDAO.deleteTag(tagId);
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
        }
        session.setAttribute("dispatcher", true);
        log.trace("AdminActionTagCommand::execute - Got \"forward\":" + forward);
        log.debug("AdminActionTagCommand::execute - AdminActionTagCommand finished");
        return forward;
    }
}

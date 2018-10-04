package ua.nure.semianikhin.elective.command.admin;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.command.Command;
import ua.nure.semianikhin.elective.dao.CourseDAO;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.TagDAO;
import ua.nure.semianikhin.elective.dao.UserDAO;
import ua.nure.semianikhin.elective.enteties.*;
import ua.nure.semianikhin.elective.exception.CourseAlreadyExistException;
import ua.nure.semianikhin.elective.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

public class AdminActionCourseCommand implements Command {

    private static final Logger log = Logger.getLogger(AdminActionCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("AdminActionCourseCommand::execute - AdminActionCourseCommand started");
        String forward = null;
        //get from request action
        HttpSession session = request.getSession();
        String action = request.getParameter("crud");
        log.trace("AdminActionCourseCommand::execute - Get from request parameter \"crud\": " + action);
        UserDAO userDAO = DAOFactory.getUserDAO();
        TagDAO tagDAO = DAOFactory.getTagDAO();
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        List<Tag> tags;
        List<User> coaches;
        if (action.equals("page")){
            //get courseId from request
            String courseId = request.getParameter("course");
            int id = Integer.parseInt(courseId);
            //get course by id from database
            Course course = courseDAO.getCourseById(id);

            //get default coach for course and set to request
            User coachDefault = course.getCourseCoach();
            request.setAttribute("coachDefault", coachDefault);
            request.setAttribute("course", course);

            //get all coaches from DB and except Default, set to request
            coaches = userDAO.getUsersByRole(Role.COACH.ordinal());
            if (coachDefault != null){
                Iterator<User> it = coaches.iterator();
                while (it.hasNext()){
                    if (it.next().getIdUser() == coachDefault.getIdUser()){
                        it.remove();
                    }
                }
            }
            request.setAttribute("coaches", coaches);
            //add tags to request
            tags = tagDAO.getAllTags();
            request.setAttribute("tags", tags);
            session.setAttribute("dispatcher", true);
            log.trace("AdminActionCourseCommand::execute - Got command to show Edit Course Page");
            forward = Path.ADMIN_EDIT_COURSE_PAGE;
        }
        if (action.equals("edit")){
            log.trace("AdminActionCourseCommand::execute - Got command to edit course in DataBase");
            //get parameters from request
            String courseName = request.getParameter("courseName");
            int courseId = Integer.parseInt(request.getParameter("idCourse"));
            int courseCoachId = Integer.parseInt(request.getParameter("newCoach"));
            int tagId = Integer.parseInt(request.getParameter("newTag"));
            String strStartDate = request.getParameter("startDate");
            String strEndDate = request.getParameter("endDate");
            String description = request.getParameter("description");
            //create new course to update in DB
            Course course = new Course();
            course.setIdCourse(courseId);
            course.setCourseName(courseName);
            User newCoach = userDAO.getUserById(courseCoachId);
            course.setCourseCoach(newCoach);
            Date startDate = Utils.getMySQLDate(strStartDate);
            Date endDate = Utils.getMySQLDate(strEndDate);
            Status courseStatus = Utils.getStatus(startDate, endDate);
            course.setStatusId(courseStatus.ordinal());
            Tag newTag = tagDAO.getTagById(tagId);
            course.setTag(newTag);
            course.setStartDate(startDate);
            course.setEndDate(endDate);
            int days = Utils.daysBetween(startDate, endDate);
            course.setDays(days);
            course.setDescription(description);
            try {
                courseDAO.updateCourse(course);
                session.setAttribute("dispatcher", false);
                forward = Path.COMMAND_ADMIN_PAGE;
            } catch (CourseAlreadyExistException e) {
                log.error("CourseAlreadyExistException in AdminActionCoachCommand::execute - ", e.getCause());
                String error = "Can't update course in DataBase";
                request.setAttribute("error", error);
                session.setAttribute("dispatcher", true);
                forward = Path.ADMIN_EDIT_COURSE_PAGE;
            }
        }

        if (action.equals("delete")){
            int courseId = Integer.parseInt(request.getParameter("course"));
            Course course = courseDAO.getCourseById(courseId);
            courseDAO.deleteCourse(course);
            session.setAttribute("dispatcher", false);
            forward = Path.COMMAND_ADMIN_PAGE;
        }

        if (action.equals("createPage")){
            log.trace("AdminActionCourseCommand::execute - Got command to show create new course page");
            tags = tagDAO.getAllTags();
            request.setAttribute("tags", tags);
            coaches = userDAO.getUsersByRole(Role.COACH.ordinal());
            request.setAttribute("coaches", coaches);
            session.setAttribute("dispatcher", true);
            forward = Path.ADMIN_CREATE_COURSE_PAGE;
        }

        if (action.equals("create")){
            log.trace("AdminActionCourseCommand::execute - Got command to create new course");
            String courseName = request.getParameter("courseName");
            int courseCoachId = Integer.parseInt(request.getParameter("coach"));
            int tagId = Integer.parseInt(request.getParameter("tag"));
            String strStartDate = request.getParameter("startDate");
            String strEndDate = request.getParameter("endDate");
            String description = request.getParameter("description");
            //create new course to update in DB
            Date startDate = Utils.getMySQLDate(strStartDate);
            Date endDate = Utils.getMySQLDate(strEndDate);
            if (!Utils.validateDates(startDate, endDate)){
                String error = "Something wrong with your date. Look carefully. " +
                        "Start Date can't be less Today. End Date can't be less Start Date";
                request.setAttribute("error", error);
                session.setAttribute("dispatcher", true);
                forward = Path.ADMIN_CREATE_COURSE_PAGE;
            }else {
                Course course = new Course();
                course.setCourseName(courseName);
                User newCoach = userDAO.getUserById(courseCoachId);
                course.setCourseCoach(newCoach);
                Status courseStatus = Utils.getStatus(startDate, endDate);
                course.setStatusId(courseStatus.ordinal());
                Tag newTag = tagDAO.getTagById(tagId);
                course.setTag(newTag);
                course.setStartDate(startDate);
                course.setEndDate(endDate);
                int days = Utils.daysBetween(startDate, endDate);
                course.setDays(days);
                course.setDescription(description);
                try {
                    courseDAO.createCourse(course);
                    session.setAttribute("dispatcher", false);
                    forward = Path.COMMAND_ADMIN_PAGE;
                } catch (CourseAlreadyExistException e) {
                    log.error("CourseAlreadyExistException in AdminActionCoachCommand::execute - ", e.getCause());
                    String error = "Can't create course in DataBase";
                    request.setAttribute("error", error);
                    session.setAttribute("dispatcher", true);
                    forward = Path.ADMIN_CREATE_COURSE_PAGE;
                }
            }
        }
        return forward;
    }
}

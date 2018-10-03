package ua.nure.semianikhin.elective;

public class Path {

    //pages
    public static final String PAGE_ERROR = "/WEB-INF/view/jsp/error_page.jsp";
    public static final String LOGIN_PAGE = "/WEB-INF/view/jsp/login.jsp";
    public static final String REGISTER_PAGE = "/WEB-INF/view/jsp/register.jsp";
    public static final String AUTHORIZATION_ERROR_PAGE = "/WEB-INF/view/jsp/authorization_error.jsp";
    public static final String AUTHENTICATION_ERROR_PAGE ="/WEB-INF/view/jsp/authentication_error.jsp";
    public static final String ADMIN_PAGE = "/WEB-INF/view/jsp/admin_panel.jsp";
    public static final String COACH_PAGE = "/WEB-INF/view/jsp/coach_panel.jsp";
    public static final String SET_MARK_PAGE = "/WEB-INF/view/jsp/set_mark.jsp";
    public static final String COURSE_USER_PAGE = "/WEB-INF/view/jsp/course_user.jsp";
    public static final String STUDENT_PAGE = "/WEB-INF/view/jsp/student_panel.jsp";
    public static final String INDEX_PAGE="/WEB-INF/view/jsp/index.jsp";
    public static final String INDEX_TAG_PAGE="/WEB-INF/view/jsp/index_tag.jsp";
    public static final String INDEX_COACH_PAGE="/WEB-INF/view/jsp/index_coach.jsp";
    public static final String CREATE_COACH_PAGE ="/WEB-INF/view/jsp/create_coach.jsp";
    public static final String ADMIN_EDIT_COURSE_PAGE = "/WEB-INF/view/jsp/edit_course.jsp";
    public static final String ADMIN_CREATE_COURSE_PAGE = "/WEB-INF/view/jsp/create_course.jsp";
    public static final String ADMIN_CREATE_TAG_PAGE = "/WEB-INF/view/jsp/create_tag.jsp";
    public static final String ADMIN_EDIT_TAG_PAGE = "/WEB-INF/view/jsp/edit_tag.jsp";

    //commands
    public static final String COMMAND_ADMIN_PAGE ="/admin?command=mainAdminPanel";
    public static final String COMMAND_STUDENT_PAGE ="/student?command=mainStudentPanel";
    public static final String COMMAND_COACH_PANEL = "/coach?command=mainCoachPanel";
    public static final String COMMAND_NO_COMMAND = "/index?command=noCommand";
}

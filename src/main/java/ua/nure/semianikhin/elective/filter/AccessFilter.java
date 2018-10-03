package ua.nure.semianikhin.elective.filter;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.enteties.Role;
import ua.nure.semianikhin.elective.enteties.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter{
    public static final Logger log = Logger.getLogger(AccessFilter.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("AccessFilter::doFilter - Access filter started");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        String uri = httpRequest.getRequestURI();
        log.trace("AccessFilter::doFilter - Get URI from request: " + uri);
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            String forward = null;
            if (user.getRoleId() == Role.ADMIN.ordinal()&& (uri.contains("/coach")|| uri.contains("/student"))){
                log.trace("AccessFilter::doFilter - User with role ADMIN don't have permission to this page");
                forward = Path.AUTHORIZATION_ERROR_PAGE;
            } else if (user.getRoleId() == Role.COACH.ordinal() && (uri.contains("/admin") || uri.contains("/student"))){
                log.trace("AccessFilter::doFilter - User with role COACH don't have permission to this page");
                forward = Path.AUTHORIZATION_ERROR_PAGE;
            } else if (user.getRoleId() == Role.STUDENT.ordinal() && (uri.contains("/admin") || uri.contains("/coach"))){
                log.trace("AccessFilter::doFilter - User with role STUDENT don't have permission to this page");
                forward = Path.AUTHORIZATION_ERROR_PAGE;
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            if (forward !=null){
                log.trace("AccessFilter::doFilter - Get \"forward\": " + forward);
                RequestDispatcher rd = httpRequest.getRequestDispatcher(forward);
                rd.forward(httpRequest, httpResponse);
            }
        } else {
            RequestDispatcher rd = servletRequest.getRequestDispatcher(Path.LOGIN_PAGE);
            rd.forward(httpRequest,httpResponse);
        }
        log.debug("AccessFilter::doFilter - Access filter finished");
    }

    @Override
    public void destroy() {

    }
}

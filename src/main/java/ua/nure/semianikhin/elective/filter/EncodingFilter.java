package ua.nure.semianikhin.elective.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final Logger log = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("EncodingFilter::init - Filter initialization started");
        //Get encoding from web.xml
        encoding = filterConfig.getInitParameter("encoding");
        log.trace("EncodingFilter::init - encoding from web.xml - " + encoding);
        log.debug("EncodingFilter::init - Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.debug("EncodingFilter::doFilter - Filter started");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        log.trace("EncodingFilter::doFilter - Request URI - " + httpRequest.getRequestURI());

        String requestEncoding = servletRequest.getCharacterEncoding();
        if (requestEncoding == null){
            log.trace("EncodingFilter::doFilter - Request encoding = null, set encoding to " + encoding);
            servletRequest.setCharacterEncoding(encoding);
        }
        log.debug("EncodingFilter::doFilter - Filter finished");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("EncodingFilter::destroy - Filter destruction started");
        //action before destruction
        log.debug("EncodingFilter::destroy - Filter destruction finished");
    }
}

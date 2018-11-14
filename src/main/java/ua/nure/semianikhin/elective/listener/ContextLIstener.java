package ua.nure.semianikhin.elective.listener;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.semianikhin.elective.dao.ICourseDAO;
import ua.nure.semianikhin.elective.dao.MySqlImpl.CourseDAOImplMySql;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.domain.Status;
import ua.nure.semianikhin.elective.exception.CourseAlreadyExistException;
import ua.nure.semianikhin.elective.utils.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Date;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ContextLIstener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextLIstener.class);
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static Runnable updateStatus = new Runnable() {
        @Override
        public void run() {
            ICourseDAO courseDAO = DAOFactory.getCourseDAO();
            List<Course> courses = courseDAO.getAllCourses();
            for (Course course : courses){
                Date startDate = course.getStartDate();
                Date endDate = course.getEndDate();
                Status status = Utils.getStatus(startDate, endDate);
                course.setStatusId(status.ordinal());
                try {
                    courseDAO.updateCourse(course);
                } catch (CourseAlreadyExistException e) {
                    log.error("CourseAlreadyExistException in Runnable during update status");
                }
            }
        }
    };
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("ContextListener::contextInitialized - Servlet context initialization started");
        ServletContext context = servletContextEvent.getServletContext();
        initLog4J(context);
        initRealStatus();
        initSheduleThread();
        log.debug("ContextListener::contextInitialized - Servlet context initialization finished");
    }

    private void initRealStatus() {
        Thread t = new Thread(updateStatus);
        t.start();
    }

    private void initLog4J(ServletContext servletContext) {
        log.debug("ContextListener::initLog4J - Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
        } catch (Exception e) {
            log.error("Exception in ContextListener::initLog4J ", e);
        }
        log.debug("ContextListener::initLog4J - Log4J initialization finished");
    }

    private void initSheduleThread(){
        LocalTime current = LocalTime.now();
        long initialDelay = ChronoUnit.MILLIS.between(current, LocalTime.MAX)+60000;
        long period = 24*60*60*1000;

        scheduler.scheduleAtFixedRate(updateStatus, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("ContextListener::contextDestroy - Servlet context destruction started");
        log.debug("ContextListener::contextDestroy - Servlet context destruction finished");
    }
}
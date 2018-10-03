package ua.nure.semianikhin.elective.utils;


import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.enteties.Course;
import ua.nure.semianikhin.elective.enteties.Status;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class Utils {
    private static final Logger log = Logger.getLogger(Utils.class);
    private static final Comparator<Course> sortByNameAsc = (x, y)->(x.getCourseName().compareTo(y.getCourseName()));
    private static final Comparator<Course> sortByNameDesc = (x, y)->(y.getCourseName().compareTo(x.getCourseName()));
    private static final Comparator<Course> sortByDaysAsc = (x, y)->(x.getDays()-y.getDays());
    private static final Comparator<Course> sortByDaysDesc = (x, y)->(y.getDays()-x.getDays());
    private static final Comparator<Course> sortByNumbersAsc = (x, y)->(x.getCountStudents()-y.getCountStudents());
    private static final Comparator<Course> sortByNumbersDesc = (x, y)->(y.getCountStudents()-x.getCountStudents());


    public static Status getStatus(Date startDate, Date endDate){
        java.util.Date date = new java.util.Date();
        Date currentDate = new Date(date.getTime());
        if (currentDate.after(startDate) && currentDate.before(endDate)){
            return Status.STARTED;
        } else if (currentDate.after(endDate)){
            return Status.ENDED;
        } else{
            return Status.OPENED;
        }
    }

    public static int daysBetween(Date d1, Date d2) {
        return Math.round((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static Date getMySQLDate(String date){
        Date sqlDate = null;
        try {
            java.util.Date inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            sqlDate = new Date(inputDate.getTime());
        } catch (ParseException e) {
            log.error("ParseException in ValidateData::getMySQLDate");
        }
        return sqlDate;
    }

    public static Comparator<Course> getComparator(String typeSort, HttpSession session){
        String duration = (String) session.getAttribute(typeSort);
        if ("DESC".equals(duration) || duration == null) {
            session.setAttribute(typeSort, "ASC");
            switch (typeSort){
                case "sortByName": return sortByNameAsc;
                case "sortByDays": return sortByDaysAsc;
                case "sortByNumbers": return sortByNumbersAsc;
            }
        } else{
            session.setAttribute(typeSort, "DESC");
            switch (typeSort){
                case "sortByName": return sortByNameDesc;
                case "sortByDays": return sortByDaysDesc;
                case "sortByNumbers": return sortByNumbersDesc;
            }
        }
        return sortByNameAsc;
    }
}

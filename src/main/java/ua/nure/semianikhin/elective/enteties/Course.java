package ua.nure.semianikhin.elective.enteties;

import java.io.Serializable;
import java.sql.Date;

/**
 *Category Entity
 *
 *@author Aleksandr Semianikhin
 *
*/

public class Course implements Serializable{

    private static final long serialVersionUID = -4453441621121046291L;

    private int idCourse;
    private String courseName;
    private User courseCoach;
    private int statusId;
    private Tag tag;
    private Date startDate;
    private Date endDate;
    private int days;
    private int countStudents;
    private String description;

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public User getCourseCoach() {
        return courseCoach;
    }

    public void setCourseCoach(User courseCoach) {
        this.courseCoach = courseCoach;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCountStudents() {
        return countStudents;
    }

    public void setCountStudents(int countStudents) {
        this.countStudents = countStudents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", courseName='" + courseName + '\'' +
                ", statusId=" + statusId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

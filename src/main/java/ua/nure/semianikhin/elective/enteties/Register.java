package ua.nure.semianikhin.elective.enteties;

import java.io.Serializable;

/**
 *Category Entity
 *
 *@author Aleksandr Semianikhin
 *
 */

public class Register implements Serializable {

    private static final long serialVersionUID = 7159674605097615914L;

    private int idRegister;
    private User user;
    private Course course;
    private double averageMark;

    public int getIdRegister() {
        return idRegister;
    }

    public void setIdRegister(int idRegister) {
        this.idRegister = idRegister;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }
}

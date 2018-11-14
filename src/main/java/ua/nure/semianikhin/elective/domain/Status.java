package ua.nure.semianikhin.elective.domain;

/**
 *Category Entity
 *
 *@author Aleksandr Semianikhin
 *
 */

public enum Status{
    OPENED, STARTED, ENDED;

    public static Status getStatus(Course course){
        int statusId = course.getStatusId();
        return Status.values()[statusId];
    }

    public String getName(){
        return name().toLowerCase();
    }

}

package ua.nure.semianikhin.elective.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *Category Entity
 *
 *@author Aleksandr Semianikhin
 *
*/
@Data
@NoArgsConstructor
@ToString(of={"idCourse", "courseName"})
public class Course implements Serializable{

    private static final long serialVersionUID = 652982951113679305L;

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

}

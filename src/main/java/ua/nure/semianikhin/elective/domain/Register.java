package ua.nure.semianikhin.elective.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *Category Entity
 *
 *@author Aleksandr Semianikhin
 *
 */
@Data
@NoArgsConstructor
public class Register implements Serializable {

    private static final long serialVersionUID = -1967992050199227353L;

    private int idRegister;
    private User user;
    private Course course;
    private double averageMark;

}

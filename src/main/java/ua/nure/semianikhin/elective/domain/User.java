package ua.nure.semianikhin.elective.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 *Category Entity
 *
 *@author Aleksandr Semianikhin
 *
 */
@Data
@NoArgsConstructor
@ToString(of={"idUser", "login", "firstName", "lastName"})
public class User implements Serializable{

    private static final long serialVersionUID = -5464520691674214872L;

    private int idUser;
    private String login;
    private String pasw;
    private String firstName;
    private String lastName;
    private int roleId;
    private boolean blocked;


}

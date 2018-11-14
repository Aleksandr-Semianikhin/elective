package ua.nure.semianikhin.elective.domain;

/**
 *Category Entity
 *
 *@author Aleksandr Semianikhin
 *
 */

public enum Role{
    ADMIN, COACH, STUDENT;

    public static Role getRole(User user){
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName(){
        return name().toLowerCase();
    }
}

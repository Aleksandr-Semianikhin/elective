package ua.nure.semianikhin.elective.enteties;

import java.io.Serializable;

/**
 *Category Entity
 *
 *@author Aleksandr Semianikhin
 *
 */

public class Tag implements Serializable{

    private static final long serialVersionUID = -6967674412459875127L;

    private int idTag;
    private String name;
    private String description;

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

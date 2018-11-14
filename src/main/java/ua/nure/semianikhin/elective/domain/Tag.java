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
public class Tag implements Serializable{

    private static final long serialVersionUID = 4908783647629283973L;

    private Integer idTag;
    private String name;

}

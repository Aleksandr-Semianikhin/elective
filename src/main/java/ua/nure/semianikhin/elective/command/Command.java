package ua.nure.semianikhin.elective.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 *Main interface for the Command pattern implementation.
 *
 *@author Aleksandr Semianikhin
 *
 */
public interface Command extends Serializable{

    /**
     * Execution method for command.
     * @return Address to go once the command is executed.
     */
    String execute(HttpServletRequest request, HttpServletResponse response);

}

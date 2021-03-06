package ua.nure.semianikhin.elective.command;

import ua.nure.semianikhin.elective.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.REGISTER_PAGE;
    }
}

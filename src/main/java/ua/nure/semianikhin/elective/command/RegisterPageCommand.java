package ua.nure.semianikhin.elective.command;

import ua.nure.semianikhin.elective.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("dispatcher", true);
        String forward = Path.REGISTER_PAGE;
        return forward;
    }
}

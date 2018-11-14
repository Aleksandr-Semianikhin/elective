package ua.nure.semianikhin.elective.command;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.Path;
import ua.nure.semianikhin.elective.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j
public class RegisterCommand implements Command {
    private static UserService userService;
    static {
        userService = new UserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forward;

        boolean isCreateUser = userService.registerNewStudent(request);

        if (isCreateUser){
            forward = Path.COMMAND_LOGIN;
        }else{
            forward = Path.REGISTER_PAGE;
        }
        return forward;
    }
}

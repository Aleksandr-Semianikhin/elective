package ua.nure.semianikhin.elective.command;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.command.admin.*;
import ua.nure.semianikhin.elective.command.coach.CoachPanelCommand;
import ua.nure.semianikhin.elective.command.coach.GetCourseUsersCommand;
import ua.nure.semianikhin.elective.command.coach.SetMarkCommand;
import ua.nure.semianikhin.elective.command.coach.SetMarkPageCommand;
import ua.nure.semianikhin.elective.command.student.RegisterToCourseCommand;
import ua.nure.semianikhin.elective.command.student.StudentPanelCommand;
import ua.nure.semianikhin.elective.command.student.UnregisterFromCourseCommand;

import java.util.HashMap;
import java.util.Map;

@Log4j
public class CommandFactory {
    private static Map<String, Command> commands;
    private static CommandFactory factoryInstance;

    private CommandFactory(){
        log.debug("Command Factory::CommandFactory - Initialization started");
        commands = new HashMap<>();
        //commands for main controller
        commands.put("login", new LoginCommand());
        commands.put("registerPage", new RegisterPageCommand());
        commands.put("register", new RegisterCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("setLang", new SetLangCommand());
        commands.put("chooseByTag", new ChooseByTagCommand());
        commands.put("chooseByCoach", new ChooseByCoachCommand());

        //commands for admin controller
        commands.put("mainAdminPanel", new AdminPanelCommand());
        commands.put("adminActionCoach", new AdminActionCoachCommand());
        commands.put("adminActionCourse",new AdminActionCourseCommand());
        commands.put("blockedStudent", new BlockedStudentCommand());
        commands.put("adminActionTag", new AdminActionTagCommand());

        //commands for coach controller
        commands.put("mainCoachPanel", new CoachPanelCommand());
        commands.put("setMarkPage", new SetMarkPageCommand());
        commands.put("setMark", new SetMarkCommand());
        commands.put("getCourseUsers", new GetCourseUsersCommand());

        //commands for student controller
        commands.put("mainStudentPanel", new StudentPanelCommand());
        commands.put("registerToCourse", new RegisterToCourseCommand());
        commands.put("unregisterFromCourse", new UnregisterFromCourseCommand());
        log.debug("Command Factory::CommandFactory - Initialization finished");
    }

    public static synchronized CommandFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new CommandFactory();
        }
        return factoryInstance;
    }

    public Command createCommand(String command) {
        log.trace("CommandFactory::createCommand - Got command: " + command);
        Command toReturn = commands.get(command);
        if (toReturn == null) {
            log.trace("CommandFactory::createCommand - Command not exist on Factory, start noCommand");
            toReturn = commands.get("noCommand");
        }
        return toReturn;
    }
}

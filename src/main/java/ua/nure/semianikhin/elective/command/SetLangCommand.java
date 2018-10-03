package ua.nure.semianikhin.elective.command;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class SetLangCommand implements Command {

    private static final Logger log = Logger.getLogger(SetLangCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("SetLangCommand::execute - SetLangCommand started");
        String forward = null;
        //get language from request
        String lang = request.getParameter("lang");
        HttpSession session = request.getSession();
        if ("ru".equals(lang) || "en".equals(lang)){
            Config.set(session, Config.FMT_LOCALE, new Locale(lang));
        }
        session.setAttribute("dispatcher", true);
        forward = Path.COMMAND_NO_COMMAND;
        log.trace("SetLangCommand::execute - Got \"forward\":" + forward);
        log.debug("SetLangCommand::execute - SetLangCommand finished");
        return forward;
    }
}

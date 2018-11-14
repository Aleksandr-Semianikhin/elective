package ua.nure.semianikhin.elective.command;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

@Log4j
public class SetLangCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("SetLangCommand::execute - SetLangCommand started");

        //get language from request
        String lang = request.getParameter("lang");
        HttpSession session = request.getSession();
        if ("ru".equals(lang) || "en".equals(lang)){
            Config.set(session, Config.FMT_LOCALE, new Locale(lang));
        }
        String forward = Path.COMMAND_NO_COMMAND;
        log.trace("SetLangCommand::execute - Got \"forward\":" + forward);
        log.debug("SetLangCommand::execute - SetLangCommand finished");
        return forward;
    }
}

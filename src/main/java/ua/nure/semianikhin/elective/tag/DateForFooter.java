package ua.nure.semianikhin.elective.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;

public class DateForFooter extends TagSupport{
    private static final Logger log = Logger.getLogger(DateForFooter.class);
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try{
            out.print(Calendar.getInstance().getTime());
        } catch (IOException e) {
            log.error("IOException in DateForFooter::doStartTag");
        }
        return SKIP_BODY;
    }

}

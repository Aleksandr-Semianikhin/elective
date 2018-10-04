package ua.nure.semianikhin.elective.command;




import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.nure.semianikhin.elective.dao.CourseDAO;
import ua.nure.semianikhin.elective.dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ChooseByCoachCommand.class, DAOFactory.class})
public class ChooseByCoachCommandTest {
    @Mock
    HttpServletRequest mockRequest;

    @Mock
    HttpServletResponse mockResponse;

    @Mock
    private HttpSession mockSession;

    @Mock
    private CourseDAO mockCourseDao;


    public static final String INDEX_COACH_PAGE="/WEB-INF/view/jsp/index_coach.jsp";


    @Test(expected=Exception.class)
    public void executeWithException() throws Exception {
        when(mockRequest.getParameter(anyString())).thenReturn("asdf");
        ChooseByCoachCommand command = new ChooseByCoachCommand();
        command.execute(mockRequest, mockResponse);
    }
    @Test
    public void execute() throws Exception{
        when(mockRequest.getParameter(anyString())).thenReturn("1");
        when(mockRequest.getSession()).thenReturn(mockSession);
        doNothing().when(mockSession).setAttribute(anyString(),any());
        PowerMockito.mockStatic(DAOFactory.class);
        PowerMockito.when(DAOFactory.getCourseDAO()).thenReturn(mockCourseDao);
        when(mockCourseDao.getCoursesByCoach(anyInt())).thenReturn(null);
        ChooseByCoachCommand command = new ChooseByCoachCommand();
        String forward = command.execute(mockRequest, mockResponse);
        Assert.assertEquals(INDEX_COACH_PAGE, forward);
        verify(mockRequest, times(1)).getParameter(anyString());
    }

}
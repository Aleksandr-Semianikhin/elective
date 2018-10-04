package ua.nure.semianikhin.elective.command;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ChooseByTagCommand.class, DAOFactory.class})
public class ChooseByTagCommandTest {
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;
    @Mock
    private HttpSession mockSession;
    @Mock
    private CourseDAO mockCourseDao;
    private ChooseByTagCommand command;

    public static final String INDEX_TAG_PAGE="/WEB-INF/view/jsp/index_tag.jsp";

    @Before
    public void setUp() throws Exception {
        command = new ChooseByTagCommand();
    }

    @After
    public void tearDown() throws Exception {
        command = null;
    }

    @Test(expected=Exception.class)
    public void executeWithException() throws Exception {
        when(mockRequest.getParameter(anyString())).thenReturn("asdf");
        command = new ChooseByTagCommand();
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
        command = new ChooseByTagCommand();
        String forward = command.execute(mockRequest, mockResponse);
        Assert.assertEquals(INDEX_TAG_PAGE, forward);
        verify(mockRequest, times(1)).getParameter(anyString());
    }

}
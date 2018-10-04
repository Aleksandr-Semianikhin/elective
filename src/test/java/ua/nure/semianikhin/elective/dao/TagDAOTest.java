package ua.nure.semianikhin.elective.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import ua.nure.semianikhin.elective.enteties.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TagDAO.class, ConnectionPool.class, EntityMapper.class})
public class TagDAOTest {
    @Mock
    private ConnectionPool connPoolMock;
    @Mock
    private Connection connMock;
    @Mock
    private PreparedStatement psMock;
    @Mock
    private ResultSet rsMock;
    @Mock
    private EntityMapper mapperMock;

    private TagDAO tagDAO;
    private Tag tag1;
    private Tag tag2;
    private List<Tag> tags;


    @Before
    public void setUp() throws Exception {
        tagDAO =new TagDAO();
        PowerMockito.mockStatic(ConnectionPool.class);
        PowerMockito.when(ConnectionPool.getInstance()).thenReturn(connPoolMock);
        when(connPoolMock.getConnection()).thenReturn(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(psMock);
        doNothing().when(psMock).setInt(anyInt(),anyInt());
        doNothing().when(psMock).setString(anyInt(),anyString());
        when(psMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mapperMock.mapRow(rsMock)).thenReturn(tag1).thenReturn(tag2);
        tag1 = new Tag();
        tag1.setIdTag(1);
        tag1.setName("Linux");
        tag1.setDescription("Linux description");
        tag2 = new Tag();
        tag2.setIdTag(2);
        tag2.setName("Java");
        tag2.setDescription("Java description");
        tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);

    }

    @After
    public void tearDown() throws Exception {
        tagDAO = null;
        tag1 = null;
        tag2 = null;
    }

    @Test
    public void getTagByIdBehaviorTest() throws Exception {
        Whitebox.setInternalState(tagDAO, "mapper", mapperMock);
        Tag actual = tagDAO.getTagById(1);
        Whitebox.setInternalState(tagDAO, "mapper", mapperMock);
        actual = tagDAO.getTagById(2);
        verify(connPoolMock, times(2)).getConnection();
        verify(connMock, times(2)).prepareStatement(anyString());
        verify(psMock, times(2)).setInt(anyInt(), anyInt());
        verify(psMock, never()).setString(anyInt(), anyString());
        verify(rsMock, times(2)).next();
        verify(mapperMock, times(2)).mapRow(rsMock);
    }
}
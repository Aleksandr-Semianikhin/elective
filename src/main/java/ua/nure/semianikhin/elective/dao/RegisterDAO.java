package ua.nure.semianikhin.elective.dao;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.enteties.Course;
import ua.nure.semianikhin.elective.enteties.Register;
import ua.nure.semianikhin.elective.enteties.Status;
import ua.nure.semianikhin.elective.enteties.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RegisterDAO {
    static final Logger log = Logger.getLogger(RegisterDAO.class);
    private Properties sqlStatements;
    private EntityMapper mapper;

    RegisterDAO() {
        sqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        mapper = new RegisterMapper();
        try{
            sqlStatements.load(inputStream);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in RegisterDAO::RegisterDAO - can't open properties file", e);
        } catch (IOException e) {
            log.error("IOException in RegisterDAO::RegisterDAO - can't open properties file", e);
        }
        try{
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException in RegisterDAO::RegisterDAO - can't close properties file", e);
        }
    }

    public void createEntryInRegister(int userId, int courseId){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("REGISTER_NEW_ENTRY"));
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }catch (SQLException e) {
            log.error("SQLException in RegisterDAO::createEntryInRegister", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in RegisterDAO::createEntryInRegister - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    public List<Register> getAllEntryForUserByStatus(int userId, Status status){
        List<Register> entries = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_ALL_ENTRY_FOR_USER_ID_BY_STATUS"));
            ps.setInt(1, userId);
            ps.setInt(2, status.ordinal());
            rs = ps.executeQuery();
            while (rs.next()){
                Register entry = (Register) mapper.mapRow(rs);
                entries.add(entry);
            }
        }catch (SQLException e) {
            log.error("SQLException in RegisterDAO::getAllEntryForUser", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in RegisterDAO::getAllEntryForUser - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
        return entries;
    }

    public List<Register> getAllEntryByCourse(int courseId){
        List<Register> entries = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_ALL_ENTRY_BY_COURSE_ID"));
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            while (rs.next()){
                Register entry = (Register)mapper.mapRow(rs);
                entries.add(entry);
            }
        }catch (SQLException e) {
            log.error("SQLException in RegisterDAO::getAllEntryForUser", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in RegisterDAO::getAllEntryForUser - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
        return entries;
    }

    public void deleteEntry(int userId, int courseId){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("DELETE_ENTRY"));
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }catch (SQLException e) {
            log.error("SQLException in RegisterDAO::deleteEntry", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in RegisterDAO::deleteEntry - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    public void setUserMarkByIdEntry(int entryId, double mark){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("UPDATE_USER_MARK_IN_REGISTER_BY_ID"));
            ps.setDouble(1, mark);
            ps.setInt(2, entryId);
            ps.executeUpdate();
        }catch (SQLException e) {
            log.error("SQLException in RegisterDAO::createEntryInRegister", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in RegisterDAO::createEntryInRegister - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    private static class RegisterMapper implements EntityMapper<Register>{

        @Override
        public Register mapRow(ResultSet rs) {
            try {
                Register register = new Register();
                register.setIdRegister(rs.getInt(Fields.REGISTER_ID));
                User user = DAOFactory.getUserDAO().getUserById(rs.getInt(Fields.REGISTER_USER_ID));
                register.setUser(user);
                Course course = DAOFactory.getCourseDAO().getCourseById(rs.getInt(Fields.REGISTER_COURSE_ID));
                register.setCourse(course);
                register.setAverageMark(rs.getDouble(Fields.REGISTER_AVERAGE_MARK));
                return register;
            } catch (SQLException e) {
                log.error("SQLException in RegisterDao::RegisterMapper::mapRow",e);
                throw new IllegalStateException();
            }
        }
    }
}

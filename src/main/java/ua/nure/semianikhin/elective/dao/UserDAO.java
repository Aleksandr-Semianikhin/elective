package ua.nure.semianikhin.elective.dao;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.enteties.User;
import ua.nure.semianikhin.elective.exception.UserAlreadyExistException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDAO {
    static final Logger log = Logger.getLogger(UserDAO.class);
    private Properties sqlStatements;
    private EntityMapper mapper;

    public UserDAO() {
        sqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        mapper = new UserMapper();
        try{
            sqlStatements.load(inputStream);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in UserDAO::UserDAO - can't open properties file", e);
        } catch (IOException e) {
            log.error("IOException in UserDAO::UserDAO - can't open properties file", e);
        }
        try{
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException in UserDAO::UserDAO - can't close properties file", e);
        }
    }

    public User getUserById(int id){
        User user = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_USER_BY_ID"));

            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                user = (User)mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            log.error("SQLException in UserDAO::getUserById", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in UserDAO::getUserById - can't close Result Set", e);
                }
            }

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in UserDAO::getUserById - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
        return user;
    }

    public User getUserByLogin(String login){
        User user = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_USER_BY_LOGIN"));
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = (User)mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            log.error("SQLException in UserDAO::getUserByLogin", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in UserDAO::getUserByLogin - can't close Result Set", e);
                }
            }

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in UserDAO::getUserByLogin - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
        return user;
    }

    public List<User> getUsersByRole(int roleId){
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_ALL_USER_BY_ROLE_ID"));
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while(rs.next()){
                User user = (User)mapper.mapRow(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("SQLException in UserDAO::getUserByRole", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        }finally {

            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in UserDAO::getUserByRole - can't close Result Set", e);
                }
            }

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in UserDAO::getUserByRole - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
        return users;
    }

    public void createUser(User user) throws UserAlreadyExistException {
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("CREATE_NEW_USER"));

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPasw());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setInt(5,user.getRoleId());

            ps.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e){
            log.error("SQLIntegrityConstraintViolationException in UserDAO::createUser" +
                    " - Inserting duplicate key", e);
            throw new UserAlreadyExistException("SQLIntegrityConstraintViolationException in " +
                    "UserDAO::createUser - Inserting duplicate key", e);
        } catch (SQLException e) {
            log.error("SQLException in UserDAO::createUser", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in UserDAO::createUser - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    public void setBlockedFlag(boolean flag, int userId){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("UPDATE_USER_BLOCKED_FLAG_BY_ID"));

            ps.setBoolean(1, flag);
            ps.setInt(2, userId);

            ps.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException in UserDAO::setBlockedFlag", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in UserDAO::setBlockedFlag - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    private static class UserMapper implements EntityMapper<User>{

        @Override
        public User mapRow(ResultSet rs) {
            try{
                User user = new User();
                user.setIdUser(rs.getInt(Fields.USER_ID));
                user.setLogin(rs.getString(Fields.USER_LOGIN));
                user.setPasw(rs.getString(Fields.USER_PASSWORD));
                user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
                user.setLastName(rs.getString(Fields.USER_LAST_NAME));
                user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
                user.setBlocked(rs.getBoolean(Fields.USER_BLOCKED));
                return user;
            } catch (SQLException e) {
                log.error("SQLException in UserDao::UserMapper::mapRow",e);
                throw new IllegalStateException();
            }
        }

    }

}

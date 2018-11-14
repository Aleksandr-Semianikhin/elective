package ua.nure.semianikhin.elective.dao.MySqlImpl;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.dao.IUserDAO;
import ua.nure.semianikhin.elective.dao.Mappers.UserListMapper;
import ua.nure.semianikhin.elective.dao.Mappers.UserMapper;
import ua.nure.semianikhin.elective.dao.executor.EntityMapper;
import ua.nure.semianikhin.elective.dao.executor.Executor;
import ua.nure.semianikhin.elective.domain.User;
import ua.nure.semianikhin.elective.exception.UserAlreadyExistException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Log4j
public class UserDAOImplMySql implements IUserDAO {

    private Properties sqlStatements;
    private Executor executor;

    public UserDAOImplMySql() {
        sqlStatements = new Properties();
        executor = Executor.getInstance();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");

        try{
            sqlStatements.load(inputStream);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in UserDAOImplMySql::UserDAOImplMySql - can't open properties file", e);
        } catch (IOException e) {
            log.error("IOException in UserDAOImplMySql::UserDAOImplMySql - can't open properties file", e);
        }
        try{
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException in UserDAOImplMySql::UserDAOImplMySql - can't close properties file", e);
        }
    }

    public User getUserById(Integer id){
        String query = sqlStatements.getProperty("READ_USER_BY_ID");
        return executor.executeQuery(query, new UserMapper(), id);
    }

    public User getUserByLogin(String login){
        String query = sqlStatements.getProperty("READ_USER_BY_LOGIN");
        return executor.executeQuery(query, new UserMapper(), login);
    }

    public List<User> getUsersByRole(Integer roleId){
        String query = sqlStatements.getProperty("READ_ALL_USER_BY_ROLE_ID");
        return executor.executeQuery(query, new UserListMapper(), roleId);
    }

    public void createUser(User user) throws UserAlreadyExistException {
        String query = sqlStatements.getProperty("CREATE_NEW_USER");
        try {
            executor.executeUpdate(query, user.getLogin(), user.getPasw(), user.getFirstName(), user.getLastName(),
                    user.getRoleId());
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in UserDAOImplMySql::createUser" +
                    " - Inserting duplicate key", e);
            throw new UserAlreadyExistException("SQLIntegrityConstraintViolationException in " +
                    "UserDAOImplMySql::createUser - Inserting duplicate key", e);
        }
    }

    public void setBlockedFlag(Boolean flag, Integer userId){
        String query = sqlStatements.getProperty("CREATE_NEW_USER");
        try {
            executor.executeUpdate(query, flag, userId);
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in UserDAOImplMySql::setBlockedFlag" +
                    " - Inserting duplicate key", e);
            throw new IllegalStateException(e);
        }
    }

}

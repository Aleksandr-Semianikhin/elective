package ua.nure.semianikhin.elective.dao.MySqlImpl;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.IRegisterDAO;
import ua.nure.semianikhin.elective.dao.Mappers.RegisterListMapper;
import ua.nure.semianikhin.elective.dao.executor.Executor;
import ua.nure.semianikhin.elective.domain.Register;
import ua.nure.semianikhin.elective.domain.Status;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Properties;

@Log4j
public class RegisterDAOImplMySql implements IRegisterDAO {
    private Properties sqlStatements;
    private Executor executor;

    public RegisterDAOImplMySql() {
        executor = Executor.getInstance();
        sqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        try {
            sqlStatements.load(inputStream);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in RegisterDAOImplMySql::RegisterDAOImplMySql - can't open properties file", e);
        } catch (IOException e) {
            log.error("IOException in RegisterDAOImplMySql::RegisterDAOImplMySql - can't open properties file", e);
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException in RegisterDAOImplMySql::RegisterDAOImplMySql - can't close properties file", e);
        }
    }

    public void createEntryInRegister(Integer userId, Integer courseId) {
        String query = sqlStatements.getProperty("REGISTER_NEW_ENTRY");
        try {
            executor.executeUpdate(query, userId, courseId);
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in RegisterDAOImplMySql::createEntryInRegister " +
                    "- can't create entry in register");
            throw new IllegalStateException(e);
        }

    }

    public List<Register> getAllEntryForUserByStatus(Integer userId, Status status) {
        String query = sqlStatements.getProperty("READ_ALL_ENTRY_FOR_USER_ID_BY_STATUS");
        return executor.executeQuery(query, new RegisterListMapper(), userId, status.ordinal());
    }

    public List<Register> getAllEntryByCourse(Integer courseId) {
        String query = sqlStatements.getProperty("READ_ALL_ENTRY_BY_COURSE_ID");
        return executor.executeQuery(query, new RegisterListMapper(), courseId);
    }

    public void deleteEntry(Integer userId, Integer courseId) {
        String query = sqlStatements.getProperty("DELETE_ENTRY");
        try {
            executor.executeUpdate(query, userId, courseId);
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in RegisterDAOImplMySql::deleteEntry " +
                    "- can't delete entry in register");
            throw new IllegalStateException(e);
        }
    }

    public void setUserMarkByIdEntry(Integer entryId, Double mark) {
        String query = sqlStatements.getProperty("UPDATE_USER_MARK_IN_REGISTER_BY_ID");
        try {
            executor.executeUpdate(query, mark, entryId);
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in RegisterDAOImplMySql::setUserMarkByIdEntry " +
                    "- can't set user's mark by entry id");
            throw new IllegalStateException(e);
        }
    }

}

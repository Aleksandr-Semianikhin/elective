package ua.nure.semianikhin.elective.dao.MySqlImpl;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.ITagDAO;
import ua.nure.semianikhin.elective.dao.Mappers.TagListMapper;
import ua.nure.semianikhin.elective.dao.Mappers.TagMapper;
import ua.nure.semianikhin.elective.dao.executor.Executor;
import ua.nure.semianikhin.elective.domain.Tag;
import ua.nure.semianikhin.elective.exception.CourseAlreadyExistException;
import ua.nure.semianikhin.elective.exception.TagAlreadyExistException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Properties;

@Log4j
public class TagDAOImplMySql implements ITagDAO {
    private Properties sqlStatements;
    private Executor executor;

    public TagDAOImplMySql() {
        executor = Executor.getInstance();
        sqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        try{
            sqlStatements.load(inputStream);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in TagDAOImplMySql::TagDAOImplMySql - can't open properties file", e);
        } catch (IOException e) {
            log.error("IOException in TagDAOImplMySql::TagDAOImplMySql - can't open properties file", e);
        }
        try{
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException in TagDAOImplMySql::TagDAOImplMySql - can't close properties file", e);
        }
    }

    public Tag getTagById(Integer id) {
        return executor.executeQuery(sqlStatements.getProperty("READ_TAG_BY_ID"),
                new TagMapper(), id);
    }

    public List<Tag> getAllTags() {
        return executor.executeQuery(sqlStatements.getProperty("READ_ALL_TAGS"), new TagListMapper());
    }

    public void createTag(Tag tag) throws TagAlreadyExistException {
        try {
            executor.executeUpdate(sqlStatements.getProperty("CREATE_NEW_TAG"),tag.getName());
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in TagDAOImplMySql::createTag - can't create tag");
            throw new TagAlreadyExistException("SQLIntegrityConstraintViolationException in " +
                    "TagDaoImplMySql::createTag - Inserting duplicate key, can't create tag", e);
        }
    }

    public void updateTag(Tag tag) throws TagAlreadyExistException {
        try {
            executor.executeUpdate(sqlStatements.getProperty("UPDATE_TAG_BY_ID"),tag.getName(), tag.getIdTag());
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in TagDAOImplMySql::updateTag - can't update tag");
            throw new TagAlreadyExistException("SQLIntegrityConstraintViolationException in " +
                    "TagDaoImplMySql::updateTag - Inserting duplicate key, can't update tag", e);
        }
    }

    public void deleteTag(Integer tagId) {
        try {
            executor.executeUpdate(sqlStatements.getProperty("DELETE_TAG_BY_ID"), tagId);
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in TagDAOImplMySql::deleteTag - can't delete tag");
            throw new IllegalStateException(e);
        }

    }
}

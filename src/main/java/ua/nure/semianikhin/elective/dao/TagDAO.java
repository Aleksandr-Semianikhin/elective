package ua.nure.semianikhin.elective.dao;

import org.apache.log4j.Logger;
import ua.nure.semianikhin.elective.enteties.Tag;

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

public class TagDAO {
    static final Logger log = Logger.getLogger(TagDAO.class);
    private Properties sqlStatements;

    public TagDAO() {
        sqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        try{
            sqlStatements.load(inputStream);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in TagDAO::TagDAO - can't open properties file", e);
        } catch (IOException e) {
            log.error("IOException in TagDAO::TagDAO - can't open properties file", e);
        }
        try{
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException in TagDAO::TagDAO - can't close properties file", e);
        }
    }

    public Tag getTagById(int id){
        Tag tag = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            TagMapper mapper = new TagMapper();
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_TAG_BY_ID"));

            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                tag = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            log.error("SQLException in TagDAO::getTagById",e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in TagDAO::getTagById - can't close Result Set",e);
                }
            }

            if (ps != null){
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in TagDAO::getTagById - can't close Prepared Statement",e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
        return tag;
    }

    public List<Tag> getAllTags(){
        List<Tag> tags = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TagMapper mapper = new TagMapper();
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_ALL_TAGS"));

            rs=ps.executeQuery();
            while (rs.next()){
                Tag tag = mapper.mapRow(rs);
                tags.add(tag);
            }

        } catch (SQLException e) {
            log.error("SQLException in TagDAO::getAllTags",e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in TagDAO::getAllTags - can't close Result Set",e);
                }
            }

            if (ps != null){
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in TagDAO::getAllTags - can't close Prepared Statement",e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }

        return tags;
    }

    public Tag getTagByName(String name){
        Tag tag = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            TagMapper mapper = new TagMapper();
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("READ_TAG_BY_NAME"));

            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                tag = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            log.error("SQLException in TagDAO::getTagByName",e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    log.error("SQLException in TagDAO::getTagByName - can't close Result Set",e);
                }
            }

            if (ps != null){
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in TagDAO::getTagByName - can't close Prepared Statement",e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
        return tag;
    }

    public void createTag(Tag tag){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("CREATE_NEW_TAG"));

            ps.setString(1, tag.getName());
            ps.setString(2, tag.getDescription());

            ps.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException in TagDAO::createTag", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in TagDAO::createTag - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    public void updateTag(Tag tag){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("UPDATE_TAG_BY_ID"));

            ps.setString(1, tag.getName());
            ps.setString(2, tag.getDescription());
            ps.setInt(3,tag.getIdTag());

            ps.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException in TagDAO::updateTag", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in TagDAO::updateTag - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    public void deleteTag(int tagId){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(sqlStatements.getProperty("DELETE_TAG_BY_ID"));

            ps.setInt(1,tagId);

            ps.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException in TagDAO::deleteTag", e);
            ConnectionPool.getInstance().rollbackAndClose(connection);
        } finally {

            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {
                    log.error("SQLException in TagDAO::deleteTag - can't close Prepared Statement", e);
                }
            }

            ConnectionPool.getInstance().commitAndClose(connection);
        }
    }

    private static class TagMapper implements EntityMapper<Tag>{

        @Override
        public Tag mapRow(ResultSet rs) {
            try{
                Tag tag = new Tag();
                tag.setIdTag(rs.getInt(Fields.TAG_ID));
                tag.setName(rs.getString(Fields.TAG_NAME));
                tag.setDescription(rs.getString(Fields.TAG_DESCRIPTION));
                return tag;
            } catch (SQLException e) {
                log.error("SQLException in TagDao::UserMapper::mapRow",e);
                throw new IllegalStateException();
            }
        }
    }
}

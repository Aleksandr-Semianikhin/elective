package ua.nure.semianikhin.elective.dao.Mappers;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.executor.EntityMapper;
import ua.nure.semianikhin.elective.domain.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
@Log4j
public class TagMapper implements EntityMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs) {
        try{
            if (rs.next()){
                Tag tag = new Tag();
                tag.setIdTag(rs.getInt(Fields.TAG_ID));
                tag.setName(rs.getString(Fields.TAG_NAME));
                return tag;
            }
        } catch (SQLException e) {
            log.error("SQLException in TagMapper::mapRow",e);
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }
}

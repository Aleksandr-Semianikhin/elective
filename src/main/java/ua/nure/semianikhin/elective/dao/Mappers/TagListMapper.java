package ua.nure.semianikhin.elective.dao.Mappers;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.executor.EntityMapper;
import ua.nure.semianikhin.elective.domain.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class TagListMapper implements EntityMapper<List<Tag>> {
    @Override
    public List<Tag> mapRow(ResultSet rs) {
        final List<Tag> tags = new ArrayList<>();
        try{
            while (rs.next()){
                Tag tag = new Tag();
                tag.setIdTag(rs.getInt(Fields.TAG_ID));
                tag.setName(rs.getString(Fields.TAG_NAME));
                tags.add(tag);
            }
        } catch (SQLException e) {
            log.error("SQLException in TagListMapper::mapRow",e);
        }
        return tags;
    }
}

package ua.nure.semianikhin.elective.dao;

import ua.nure.semianikhin.elective.domain.Tag;
import ua.nure.semianikhin.elective.exception.TagAlreadyExistException;

import java.sql.SQLException;
import java.util.List;

public interface ITagDAO {

    Tag getTagById(Integer id);
    List<Tag> getAllTags();
    void createTag(Tag tag) throws TagAlreadyExistException;
    void updateTag(Tag tag) throws TagAlreadyExistException;
    void deleteTag(Integer tagId);

}

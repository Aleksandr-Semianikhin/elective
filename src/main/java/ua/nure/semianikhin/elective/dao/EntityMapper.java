package ua.nure.semianikhin.elective.dao;

import java.sql.ResultSet;

/**
 * Defines general contract for mapping database result set rows to application entities.
 * Implementations are not supposed to move cursor of the resultSet via next() method,
 * but only extract information from the row in current cursor position.
 */
public interface EntityMapper<T> {
    T mapRow(ResultSet rs);
}

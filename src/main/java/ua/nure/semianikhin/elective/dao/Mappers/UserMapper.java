package ua.nure.semianikhin.elective.dao.Mappers;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.executor.EntityMapper;
import ua.nure.semianikhin.elective.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j
public class UserMapper implements EntityMapper<User> {
    @Override
    public User mapRow(ResultSet rs) {
        try{
            if (rs.next()){
                User user = new User();
                user.setIdUser(rs.getInt(Fields.USER_ID));
                user.setLogin(rs.getString(Fields.USER_LOGIN));
                user.setPasw(rs.getString(Fields.USER_PASSWORD));
                user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
                user.setLastName(rs.getString(Fields.USER_LAST_NAME));
                user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
                user.setBlocked(rs.getBoolean(Fields.USER_BLOCKED));
                return user;
            }
        } catch (SQLException e) {
            log.error("SQLException in UserMapper::mapRow",e);
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }
}

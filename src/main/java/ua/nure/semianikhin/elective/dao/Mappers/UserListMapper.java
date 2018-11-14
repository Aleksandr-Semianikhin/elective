package ua.nure.semianikhin.elective.dao.Mappers;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.executor.EntityMapper;
import ua.nure.semianikhin.elective.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class UserListMapper implements EntityMapper<List<User>> {
    @Override
    public List<User> mapRow(ResultSet rs) {
        List<User> users = new ArrayList<>();
        try{
            while (rs.next()){
                User user = new User();
                user.setIdUser(rs.getInt(Fields.USER_ID));
                user.setLogin(rs.getString(Fields.USER_LOGIN));
                user.setPasw(rs.getString(Fields.USER_PASSWORD));
                user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
                user.setLastName(rs.getString(Fields.USER_LAST_NAME));
                user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
                user.setBlocked(rs.getBoolean(Fields.USER_BLOCKED));
                users.add(user);
            }

        } catch (SQLException e) {
            log.error("SQLException in UserListMapper::mapRow",e);
        }
        return users;
    }
}

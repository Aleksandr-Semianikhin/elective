package ua.nure.semianikhin.elective.dao;

import ua.nure.semianikhin.elective.domain.User;
import ua.nure.semianikhin.elective.exception.UserAlreadyExistException;

import java.util.List;

public interface IUserDAO {
    User getUserById(Integer id);
    User getUserByLogin(String login);
    List<User> getUsersByRole(Integer roleId);
    void createUser(User user) throws UserAlreadyExistException;
    void setBlockedFlag(Boolean flag, Integer userId);
}

package ua.nure.semianikhin.elective.dao;

import ua.nure.semianikhin.elective.domain.Register;
import ua.nure.semianikhin.elective.domain.Status;

import java.util.List;

public interface IRegisterDAO {
    void createEntryInRegister(Integer userId, Integer courseId);
    List<Register> getAllEntryForUserByStatus(Integer userId, Status status);
    List<Register> getAllEntryByCourse(Integer courseId);
    void deleteEntry(Integer userId, Integer courseId);
    void setUserMarkByIdEntry(Integer entryId, Double mark);
}

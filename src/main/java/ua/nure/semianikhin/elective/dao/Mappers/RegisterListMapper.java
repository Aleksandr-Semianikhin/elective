package ua.nure.semianikhin.elective.dao.Mappers;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.executor.EntityMapper;
import ua.nure.semianikhin.elective.domain.Course;
import ua.nure.semianikhin.elective.domain.Register;
import ua.nure.semianikhin.elective.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class RegisterListMapper implements EntityMapper<List<Register>> {
    @Override
    public List<Register> mapRow(ResultSet rs) {
        final List<Register> registers = new ArrayList<>();
        try {
            while(rs.next()) {
                Register register = new Register();
                register.setIdRegister(rs.getInt(Fields.REGISTER_ID));
                User user = DAOFactory.getUserDAO().getUserById(rs.getInt(Fields.REGISTER_USER_ID));
                register.setUser(user);
                Course course = DAOFactory.getCourseDAO().getCourseById(rs.getInt(Fields.REGISTER_COURSE_ID));
                register.setCourse(course);
                register.setAverageMark(rs.getDouble(Fields.REGISTER_AVERAGE_MARK));
                registers.add(register);
            }
        } catch (SQLException e) {
            log.error("SQLException in RegisterListMapper::mapRow",e);
            throw new IllegalStateException();
        }
        return registers;
    }
}

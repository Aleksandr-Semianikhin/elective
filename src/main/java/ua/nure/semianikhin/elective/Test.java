package ua.nure.semianikhin.elective;

import ua.nure.semianikhin.elective.dao.DAOFactory;
import ua.nure.semianikhin.elective.dao.TagDAO;
import ua.nure.semianikhin.elective.enteties.Course;
import ua.nure.semianikhin.elective.enteties.User;


import java.sql.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Class<?>[] mapper = TagDAO.class.getDeclaredClasses();
        for (Class cl : mapper){
            System.out.println(cl.getName());
        }

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager
//                    .getConnection("jdbc:mysql://localhost:3307/elective", "root","hvObPefEX0VbA8t");
//            //System.out.println(connection.getSchema());
//            PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE user_login=?");
//            st.setString(1, "admin");
//            ResultSet set = st.executeQuery();
//
//            ArrayList<User> courses = new ArrayList<>();
//            while (set.next()){
//                User user = new User();
//                user.setIdUser(set.getInt(1));
//                user.setLogin(set.getString(2));
//                user.setPasw(set.getString(3));
//                user.setFirstName(set.getString(4));
//                user.setLastName(set.getString(5));
//                user.setRoleId(set.getInt(6));
//                user.setBlocked(set.getBoolean(7));
//                courses.add(user);
//            }
//            for (User c : courses){
//                System.out.println(c);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}

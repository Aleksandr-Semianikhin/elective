package ua.nure.semianikhin.elective.dao;


import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *DB Manager works with
 * Only the required DAO methods are defined!
 *
 *@author Aleksandr Semianikhin
 *
 */

public class ConnectionPool {

    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    /**
    *
    * Singleton class
    *
    */

    private static ConnectionPool instance;

    public static synchronized ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }


    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in your
     * WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return A DB connection.
     */
    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");

            // elective - the name of data source
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/elective");
            con = dataSource.getConnection();
        } catch (NamingException e) {
            log.error("NamingException in ConnectionPool::getConnection - can't obtain a Connection " +
                    "from the pool", e);
        }
        return con;
    }


//     public Connection getConnection() throws SQLException{
//         try {
//             Class.forName("com.mysql.jdbc.Driver");
//         } catch (ClassNotFoundException e) {
//             e.printStackTrace();
//         }
//         Connection conn = DriverManager
//                 .getConnection("jdbc:mysql://localhost:3307/elective", "root","hvObPefEX0VbA8t");
//         return conn;
//     }

    /**
     * Commits and close the given connection.
     *
     * @param con
     *            Connection to be committed and closed.
     */
    public void commitAndClose(Connection con) {
        if (con != null) {
            try {
                con.commit();
                con.close();
            } catch (SQLException e) {
                log.error("SQLException in ConnectionPool::commitAndClose - can't close Connection", e);
            }
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con
     *            Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) {
        if (con != null){
            try {
                con.rollback();
                con.close();
            } catch (SQLException e) {
                log.error("SQLException in ConnectionPool::rollbackAndClose - can't close Connection", e);
            }
        }
    }

}

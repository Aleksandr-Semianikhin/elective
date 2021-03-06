package ua.nure.semianikhin.elective.dao;


import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *DB Manager works with
 * Only the required DAO methods are defined!
 */
@Log4j
public class ConnectionPool {

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

    public void close(AutoCloseable autoCloseable){
        if (autoCloseable != null){
            try{
                autoCloseable.close();
            } catch (Exception e) {
                log.error("Exception in ConnectionPool::close - can't close AutoCloseable object");
            }
        }
    }

}

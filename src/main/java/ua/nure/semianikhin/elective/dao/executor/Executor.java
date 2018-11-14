package ua.nure.semianikhin.elective.dao.executor;

import lombok.extern.log4j.Log4j;
import ua.nure.semianikhin.elective.dao.ConnectionPool;

import java.sql.*;

@Log4j
public class Executor {
    private static Executor instance;
    private static ConnectionPool connectionPool;

    public static synchronized Executor getInstance() {
        if (instance == null){
            log.debug("Executor::getInstance - create new instance");
            instance = new Executor();
            connectionPool = ConnectionPool.getInstance();
        }
        return instance;
    }

    public void executeUpdate(final String update, Object... args) throws SQLIntegrityConstraintViolationException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = connectionPool.getConnection();
            ps = con.prepareStatement(update);
            for (int i = 0; i < args.length;) {
                if (args[i].getClass() == Integer.class) {
                    ps.setInt(++i, (Integer) args[i - 1]);
                } else if (args[i].getClass() == String.class) {
                    ps.setString(++i, (String) args[i - 1]);
                } else if(args[i].getClass() == Double.class) {
                    ps.setDouble(++i, (Double) args[i]);
                } else if(args[i].getClass() == Boolean.class) {
                    ps.setBoolean(++i, (Boolean) args[i]);
                } else {
                    ps.setDate(++i, (Date)args[i-1]);
                }
            }
            ps.executeUpdate();
        } catch(SQLIntegrityConstraintViolationException e) {
            log.error("SQLIntegrityConstraintViolationException in Executor::executeUpdate" +
             " - Inserting duplicate key", e);
            connectionPool.rollbackAndClose(con);
            throw new SQLIntegrityConstraintViolationException();
        } catch (SQLException e){
            log.error("SqlException in Executor::executeUpdate - can't execute query: " + update);
            connectionPool.rollbackAndClose(con);
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            connectionPool.close(ps);
            connectionPool.commitAndClose(con);
        }

    }

    public <T> T executeQuery(final String query, final EntityMapper<T> mapper, Object... args){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;
        T entity = null;
        try{
            con = connectionPool.getConnection();
            ps = con.prepareStatement(query);
            for (int i = 0; i < args.length;) {
                if (args[i].getClass() == Integer.class) {
                    ps.setInt(++i, (Integer) args[i-1]);
                } else {
                    ps.setString(++i, (String) args[i-1]);
                }
            }
            rs = ps.executeQuery();
            entity = mapper.mapRow(rs);
        } catch (SQLException e){
            log.error("SqlException in Executor::executeQuery - can't execute query: " + query);
            connectionPool.rollbackAndClose(con);
        } finally {
            connectionPool.close(ps);
            connectionPool.close(ps);
            connectionPool.commitAndClose(con);
        }
        return entity;
    }
}

package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlAccessDao implements AccessDao {
    private static final Logger LOGGER =
            Logger.getLogger(MySqlAccessDao.class.getName());
    private Connection connection;
    private PreparedStatement psRead, psUpdate, psInsert, psDelete;
    private final static String sql = "select access_ID, access_login, access_password "
            + "from users_access where access_login=?";
    private final static String sql2 = "update users_access set access_login=?, access_password=? where access_ID=? ";
    private final static String sql3 = "insert into users_access (access_ID, access_login, access_password) values (?, ?, ?)";
    private final static String sql4 = "delete from users_access where access_ID=?";

    MySqlAccessDao() throws DAOException {
        Properties prop = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(inputStream);
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(prop.getProperty("Url"), prop.getProperty("User"),
                    prop.getProperty("Password"));
            psRead = connection.prepareStatement(sql);
            psUpdate = connection.prepareStatement(sql2);
            psInsert = connection.prepareStatement(sql3);
            psDelete = connection.prepareStatement(sql4);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error in constractor with SQL", ex);
            throw new DAOException("Error in constractor with SQL", ex);
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "File for config not found", ex);
            throw new DAOException("File for config not found", ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error in thread", ex);
            throw new DAOException("Error in thread", ex);
        } catch (ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error in constraction - FileNotFound", ex);
            throw new DAOException("Error in constraction - FileNotFound", ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Error in constraction while closing stream", ex);
                    throw new DAOException("Error in constraction while closin stream", ex);
                }
            }
        }
    }

    @Override
    public Access read(String login) throws DAOException {

        ResultSet resultSet = null;
        Access access = new Access();
        try {
            psRead.setString(1, login);
            resultSet = psRead.executeQuery();
            while (resultSet.next()) {
                access.setID(resultSet.getInt("access_ID"));
                access.setLogin(resultSet.getString("access_login"));
                access.setPassword(resultSet.getString("access_password"));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQL Error in readMethod", ex);
            throw new DAOException("SQL Error in readMethod", ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE,
                        "Error while closing result, read method", ex);
                throw new DAOException("Error while closing result, read method", ex);
            }
            return access;
        }
    }

    @Override
    public void update(Access access) throws DAOException {
        try {
            psUpdate.setString(1, access.getLogin());
            psUpdate.setString(2, access.getPassword());
            psUpdate.setInt(3, access.getID());
            psUpdate.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, update method", ex);
            throw new DAOException("Error in logic block, update method", ex);
        }
    }

    @Override
    public void insert(Access access) throws DAOException {
        try {
            psInsert.setInt(1, access.getID());
            psInsert.setString(2, access.getLogin());
            psInsert.setString(3, access.getPassword());
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, insert method", ex);
            throw new DAOException("Error in logic block, insert method", ex);
        }
    }

    @Override
    public void delete(Access access) throws DAOException {
        try {
            psDelete.setInt(1, access.getID());
            psDelete.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, delete method", ex);
            throw new DAOException("Error in logic block, delete method", ex);
        }
    }

    public void close() throws DAOException {
        Throwable t = null;
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Access", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Access", ex);
            t = ex;
        }
        try {
            psRead.close();
            } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Access", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Access", ex);
            t = ex;
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing connection Access", ex);
            t=ex;
        }
        if (t != null ) {
            System.out.println("Error");
            throw new DAOException("Error in closing Access", t);
        }
    }
}




    
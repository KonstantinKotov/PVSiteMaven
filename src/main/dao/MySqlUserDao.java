package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlUserDao implements UserDao {
    private static final Logger LOGGER =
            Logger.getLogger(MySqlUserDao.class.getName());
    private Connection connection;
    private PreparedStatement psRead, psUpdate, psInsert, psDelete;
    private final static String sql = "select user_ID, user_first_Name, user_second_Name, user_mid_Name, " +
            "user_sex, user_birth_Date, user_work_From_Date, user_position, user_department," +
            " user_material_ID, user_login "
            + "from users where user_login=?";
    private final static String sql2 ="update users set user_first_Name=?, user_second_Name=?, user_mid_Name=?, " +
            "user_sex=?, user_birth_Date=?, user_work_From_Date=?, user_position=?, user_department=?," +
            " user_material_ID=?, user_login=? where user_ID=?";
    private final static String sql3="insert into users (user_ID, user_first_Name, user_second_Name, user_mid_Name, " +
            "user_sex, user_birth_Date, user_work_From_Date, user_position, user_department," +
            " user_material_ID, user_login) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String sql4 ="delete from users where user_ID=?";

    MySqlUserDao() throws DAOException {
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
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Error in constraction while making connection", ex);
                    throw new DAOException("Error in constraction while making connection", ex);
                }
            }
        }
    }

    @Override
    public User read(Access access) throws DAOException {
        ResultSet resultSet = null;
        User user = new User();

        try {
            psRead.setInt(1, access.getID());
            resultSet = psRead.executeQuery();
            while (resultSet.next()) {
                user.setUserID(resultSet.getInt("user_ID"));
                user.setFirstName(resultSet.getString("user_first_Name"));
                user.setSecondName(resultSet.getString("user_second_Name"));
                user.setMidName(resultSet.getString("user_mid_Name"));
                user.setSex(resultSet.getString("user_sex"));
                user.setBirthDate(resultSet.getDate("user_birth_Date"));
                user.setWorkFromDate(resultSet.getDate("user_work_From_Date"));
                user.setPosition(resultSet.getString("user_position"));
                user.setDepartment(resultSet.getString("user_department"));
                user.setMaterialID(resultSet.getInt("user_material_ID"));
                user.setLogin(resultSet.getString("user_login"));

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
                        "Error while closing result, read method User", ex);
                throw new DAOException("Error while closing result, read method User", ex);
            }
        }
        return user;
    }

    @Override
    public void update(User user) throws DAOException{
        try {
            psUpdate.setString(1, user.getFirstName());
            psUpdate.setString(2, user.getSecondName());
            psUpdate.setString(3, user.getMidName());
            psUpdate.setString(4, user.getSex());
            psUpdate.setDate(5, (Date) user.getBirthDate());
            psUpdate.setDate(6, (Date) user.getWorkFromDate());
            psUpdate.setString(7, user.getPosition());
            psUpdate.setString(8, user.getDepartment());
            psUpdate.setInt(9, user.getMaterialID());
            psUpdate.setString(10, user.getLogin());
            psUpdate.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, update method", ex);
            throw new DAOException("Error in logic block, update method", ex);
        }
    }

    @Override
    public void insert(User user) throws DAOException{
        try {
            psInsert.setInt(1, user.getUserID());
            psInsert.setString(2, user.getFirstName());
            psInsert.setString(3, user.getSecondName());
            psInsert.setString(4, user.getMidName());
            psInsert.setString(5, user.getSex());
            psInsert.setDate(6, (Date) user.getBirthDate());
            psInsert.setDate(7, (Date) user.getWorkFromDate());
            psInsert.setString(8, user.getPosition());
            psInsert.setString(9, user.getDepartment());
            psInsert.setInt(10, user.getMaterialID());
            psInsert.setString(11, user.getLogin());
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, insert method", ex);
            throw new DAOException("Error in logic block, insert method", ex);
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        try {
            psDelete.setInt(1, user.getUserID());
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
            LOGGER.log(Level.SEVERE, "Error while closing statement  User", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  User", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  User", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  User", ex);
            t = ex;
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing connection User", ex);
            t=ex;
        }
        if (t != null ) {
            throw new DAOException("Error in closing User", t);
        }
    }
}

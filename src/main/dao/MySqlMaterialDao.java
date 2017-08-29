package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlMaterialDao implements MaterialDao {
    private static final Logger LOGGER =
            Logger.getLogger(MySqlMaterialDao.class.getName());
    private Connection connection;
    private PreparedStatement psRead, psUpdate, psInsert, psDelete;

    private final static String sql = "select material_ID, material_name, material_description "
            + "from materials where material_ID=?";
    private final static String sql2 = "update materials set material_name=?, material_description=? where material_ID=? ";
    private final static String sql3 = "insert into materials (material_ID, material_name, material_description) values (?, ?, ?)";
    private final static String sql4 = "delete from materials where material_ID=?";

    MySqlMaterialDao() throws DAOException {
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
    public InfoMaterial read(User user) throws DAOException {

        ResultSet resultSet =null;
        InfoMaterial material = new InfoMaterial();
        try {
            psRead.setInt(1, user.getMaterialID());
            resultSet= psRead.executeQuery();
            while (resultSet.next()){
                material.setMaterialID(resultSet.getInt("material_ID"));
                material.setMaterilName(resultSet.getString("material_name"));
                material.setMaterialDescription(resultSet.getString("material_description"));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQL Error in readMethod", ex);
            throw new DAOException("SQL Error in readMethod", ex);
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE,
                        "Error while closing result, read method Material", ex);
                throw new DAOException("Error while closing result, read method Material", ex);
            }
        }
        return material;
    }

    @Override
    public void update(InfoMaterial material) throws DAOException{
        try {
            psUpdate.setString(1, material.getMaterilName());
            psUpdate.setString(2, material.getMaterialDescription());
            psUpdate.setInt(3, material.getMaterialID());
            psUpdate.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, update method", ex);
            throw new DAOException("Error in logic block, update method", ex);
        }
    }

    @Override
    public void insert(InfoMaterial material) throws DAOException {
        try {
            psInsert.setInt(1, material.getMaterialID());
            psInsert.setString(2, material.getMaterilName());
            psInsert.setString(3, material.getMaterialDescription());
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, insert method", ex);
            throw new DAOException("Error in logic block, insert method", ex);
        }
    }

    @Override
    public void delete(InfoMaterial material) throws DAOException{
        try {
            psDelete.setInt(1, material.getMaterialID());
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
            LOGGER.log(Level.SEVERE, "Error while closing statement  Material", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Material", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Material", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Material", ex);
            t = ex;
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing connection Material", ex);
            t=ex;
        }
        if (t != null ) {
            throw new DAOException("Error in closing Material", t);
        }
    }
}

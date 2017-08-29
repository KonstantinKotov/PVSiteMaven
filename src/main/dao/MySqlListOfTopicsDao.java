package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlListOfTopicsDao implements ListOfTopicsDao {
    private static final Logger LOGGER =
            Logger.getLogger(MySqlListOfTopicsDao.class.getName());
    private Connection connection;
    private PreparedStatement psRead, psUpdate, psInsert, psDelete;

    private final static String sql = "select l.list_ID, m.material_name, l.list_topic_ID, t.topic_name \n" +
            "from listoftopicsinmaterial l inner join \n" +
            "    materials m on l.list_material_ID = m.material_ID inner join\n" +
            "    topics t on l.list_topic_ID = t.topic_ID where m.material_ID=?";
    private final static String sql2 = "update listoftopicsinmaterial set list_material_ID=?, list_topic_ID=? where list_ID=? ";
    private final static String sql3 = "insert into listoftopicsinmaterial (list_ID, list_material_ID, list_topic_ID) " +
            "values (?, ?, ?)";
    private final static String sql4 = "delete from listoftopicsinmaterial where list_ID=?";

    MySqlListOfTopicsDao() throws DAOException {
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
                    LOGGER.log(Level.SEVERE, "Error in constraction while making connection", ex);
                    throw new DAOException("Error in constraction while making connection", ex);
                }
            }
        }
    }


    @Override
    public List<ListOfTopicsInInfoMaterial> read(InfoMaterial infoMaterial) throws DAOException {
        List<ListOfTopicsInInfoMaterial> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            psRead.setInt(1, infoMaterial.getMaterialID());
            resultSet = psRead.executeQuery();
            while (resultSet.next()) {
                ListOfTopicsInInfoMaterial listTop = new ListOfTopicsInInfoMaterial();
                listTop.setListID(resultSet.getInt("list_ID"));
                listTop.setMaterialName(resultSet.getString("material_name"));
                listTop.setTopicName(resultSet.getString("topic_name"));
                listTop.setListTopicID(resultSet.getInt("list_Topic_ID"));
                list.add(listTop);
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
                        "Error while closing result, read method List", ex);
                throw new DAOException("Error while closing result, read method List", ex);
            }
        }
        return list;
    }

    @Override
    public void update(ListOfTopicsInInfoMaterial listOfTopics) throws DAOException {
        try {
            psUpdate.setInt(1, listOfTopics.getListMaterialID());
            psUpdate.setInt(2, listOfTopics.getListTopicID());
            psUpdate.setInt(3, listOfTopics.getListID());
            psUpdate.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, update method", ex);
            throw new DAOException("Error in logic block, update method", ex);
        }
    }

    @Override
    public void insert(ListOfTopicsInInfoMaterial listOfTopics) throws DAOException {
        try {
            psInsert.setInt(1, listOfTopics.getListID());
            psInsert.setInt(2, listOfTopics.getListMaterialID());
            psInsert.setInt(3, listOfTopics.getListTopicID());
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, insert method", ex);
            throw new DAOException("Error in logic block, insert method", ex);
        }
    }

    @Override
    public void delete(ListOfTopicsInInfoMaterial listOfTopics) throws DAOException {
        try {
            psDelete.setInt(1, listOfTopics.getListID());
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
            LOGGER.log(Level.SEVERE, "Error while closing statement List", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  List", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  List", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  List", ex);
            t = ex;
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing connection List", ex);
            t = ex;
        }
        if (t != null) {
            throw new DAOException("Error in closing List", t);
        }
    }
}

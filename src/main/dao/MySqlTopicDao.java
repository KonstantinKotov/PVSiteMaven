package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlTopicDao implements TopicDao {
    private static final Logger LOGGER =
            Logger.getLogger(MySqlAccessDao.class.getName());
    private Connection connection;
    private PreparedStatement psRead, psUpdate, psInsert, psDelete;
    private final static String sql = "select topic_ID, topic_name, topic_description "
            + "from topics where topic_ID=?";
    private final static String sql2 = "update topics set topic_name=?, topic_description=? where topic_ID=? ";
    private final static String sql3 = "insert into topics (topic_ID, topic_name, topic_description) values (?, ?, ?)";
    private final static String sql4 = "delete from topics where topic_ID=?";

    MySqlTopicDao() throws DAOException {
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
    public Topic read(ListOfTopicsInInfoMaterial listTop) throws DAOException {

        ResultSet resultSet = null;
        Topic topic = new Topic();
        try {

            psRead.setInt(1, listTop.getListTopicID());
            resultSet = psRead.executeQuery();
            while (resultSet.next()) {
                topic.setTopicID(resultSet.getInt("topic_ID"));
                topic.setTopicName(resultSet.getString("topic_name"));
                topic.setTopicDescription(resultSet.getString("topic_description"));
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
                        "Error while closing result, read method Topic", ex);
                throw new DAOException("Error while closing result, read method Topic", ex);
            }
            return topic;
        }
    }

    @Override
    public void update(Topic topic) throws DAOException {
        try {
            psUpdate.setString(1, topic.getTopicName());
            psUpdate.setString(2, topic.getTopicDescription());
            psUpdate.setInt(3, topic.getTopicID());
            psUpdate.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, update method", ex);
            throw new DAOException("Error in logic block, update method", ex);
        }
    }

    @Override
    public void insert(Topic topic) throws DAOException{
        try {
            psInsert.setInt(1, topic.getTopicID());
            psInsert.setString(2, topic.getTopicName());
            psInsert.setString(3, topic.getTopicDescription());
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, insert method", ex);
            throw new DAOException("Error in logic block, insert method", ex);
        }
    }

    @Override
    public void delete(Topic topic) throws  DAOException {
        try {
            psDelete.setInt(1, topic.getTopicID());
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
            LOGGER.log(Level.SEVERE, "Error while closing statement  Topic", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Topic", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Topic", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Topic", ex);
            t = ex;
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing connection Topic", ex);
            t=ex;
        }
        if (t != null ) {
            throw new DAOException("Error in closing Topic", t);
        }
    }
}
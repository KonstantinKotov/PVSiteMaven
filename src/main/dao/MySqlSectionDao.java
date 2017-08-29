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

public class MySqlSectionDao implements SectionDao {
    private static final Logger LOGGER =
            Logger.getLogger(MySqlSectionDao.class.getName());
    private Connection connection;
    private PreparedStatement psRead, psUpdate, psInsert, psDelete;
    private final static String sql = "select t.topic_name, s.section_name, s.section_content \n" +
            "\tfrom sections s inner join\n" +
            "    topics t on s.section_topic_ID = t.topic_ID \n" +
            "    where t.topic_ID=?";
    private final static String sql2 = "update sections set section_name=?, section_content=?, section_topic_ID=? " +
            "where section_ID=? ";
    private final static String sql3 = "insert into sections (section_ID, section_name, section_content, section_topic_ID)" +
            " values (?, ?, ?,?)";
    private final static String sql4 = "delete from sections where section_ID=?";

    MySqlSectionDao() throws DAOException {
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
    public List<Section> read(Topic topic) throws DAOException {
        List <Section> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            psRead.setInt(1, topic.getTopicID());
            resultSet = psRead.executeQuery();
            while (resultSet.next()) {
                Section section = new Section();
               //section.setSectionID(resultSet.getInt("section_ID"));
                section.setSectionTopicName(resultSet.getString("topic_name"));
                section.setSectionName(resultSet.getString("section_name"));
                section.setSectionContext(resultSet.getString("section_content"));
                list.add(section);
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
                        "Error while closing result, read method Section", ex);
                throw new DAOException("Error while closing result, read method Section", ex);
            }

        }
        return list;
    }

    @Override
    public void update(Section section) throws DAOException {
        try {
            psUpdate.setString(1, section.getSectionName());
            psUpdate.setString(2, section.getSectionContext());
            psUpdate.setInt(3, section.getSectionTopicID());
            psUpdate.setInt(4, section.getSectionID());
            psUpdate.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, update method", ex);
            throw new DAOException("Error in logic block, update method", ex);
        }
    }

    @Override
    public void insert(Section section) throws DAOException{
        try {
            psInsert.setInt(1, section.getSectionID());
            psInsert.setString(2, section.getSectionName());
            psInsert.setString(3, section.getSectionContext());
            psInsert.setInt(4, section.getSectionTopicID());
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE,
                    "Error in logic block, insert method", ex);
            throw new DAOException("Error in logic block, insert method", ex);
        }
    }

    @Override
    public void delete(Section section) throws  DAOException {
        try {
            psDelete.setInt(1, section.getSectionID());
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
            LOGGER.log(Level.SEVERE, "Error while closing statement  Section", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Section", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Section", ex);
            t = ex;
        }
        try {
            psRead.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing statement  Section", ex);
            t = ex;
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while closing connection Section", ex);
            t=ex;
        }
        if (t != null ) {
            throw new DAOException("Error in closing Section", t);
        }
    }
}

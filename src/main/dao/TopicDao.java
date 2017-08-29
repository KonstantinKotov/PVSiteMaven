package dao;

public interface TopicDao {
    Topic read(ListOfTopicsInInfoMaterial list) throws DAOException;

    void update(Topic topic) throws DAOException;

    void insert(Topic topic) throws DAOException;

    void delete(Topic topic) throws DAOException;

    void close() throws DAOException;
}

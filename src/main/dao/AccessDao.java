package dao;

public interface AccessDao {
    Access read(String login) throws DAOException;

    void update(Access access) throws DAOException;

    void insert(Access access) throws DAOException;

    void delete(Access access) throws DAOException;

    void close() throws DAOException;
}

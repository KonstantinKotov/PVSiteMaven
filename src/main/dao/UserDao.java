package dao;

/**
 * Created by k.kotov on 17.07.2017.
 */
public interface UserDao {
    User read(Access access) throws DAOException;

    void update(User user)throws DAOException;

    void insert(User user) throws DAOException;

    void delete(User user) throws DAOException;

    void close() throws DAOException;
}

package dao;

import java.util.List;

public interface MaterialDao {
    InfoMaterial read(User user) throws DAOException;

    void update(InfoMaterial material) throws DAOException;

    void insert(InfoMaterial material) throws DAOException;

    void delete(InfoMaterial material) throws DAOException;

    void close() throws DAOException;
}

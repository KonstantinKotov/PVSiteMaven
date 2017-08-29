package dao;

import java.util.List;

public interface ListOfTopicsDao {
    List<ListOfTopicsInInfoMaterial> read(InfoMaterial infoMaterial) throws DAOException;

    void update(ListOfTopicsInInfoMaterial listOfTopics) throws DAOException;

    void insert(ListOfTopicsInInfoMaterial listOfTopics) throws DAOException;

    void delete(ListOfTopicsInInfoMaterial listOfTopics) throws DAOException;

    void close() throws DAOException;
}

package dao;

import java.util.List;

public interface SectionDao{
        List<Section> read(Topic topic) throws DAOException;

        void update(Section section) throws DAOException;

        void insert(Section section) throws DAOException;

        void delete(Section section) throws DAOException;

        void close() throws DAOException;
}

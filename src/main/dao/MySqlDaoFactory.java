package dao;

public class MySqlDaoFactory {
        public MySqlMaterialDao createMaterialDao() throws DAOException {
            return new MySqlMaterialDao();
        }

        public MySqlListOfTopicsDao createListOfTopicsDao() throws DAOException {
            return new MySqlListOfTopicsDao();
        }

        public MySqlSectionDao createSectionDao() throws DAOException{
            return new MySqlSectionDao();
        }

        public MySqlTopicDao createTopicDao() throws DAOException {
            return new MySqlTopicDao();
        }

        public MySqlUserDao createUserDao() throws DAOException {
            return new MySqlUserDao();
        }

        public MySqlAccessDao createAccessDao() throws DAOException {
            return new MySqlAccessDao();
        }
    }


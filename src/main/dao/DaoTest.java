package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoTest {
    public static void main(String []args) throws DAOException, SQLException{

        AccessDao accessDao = null;
        UserDao userDao = null;
        MaterialDao materialDao = null;
        ListOfTopicsDao listDao = null;
        TopicDao topicDao = null;
        SectionDao sectionDao = null;

        try {
            accessDao = new MySqlAccessDao();
            Access access = accessDao.read("ivanov");
            System.out.println(access);
            System.out.println(access.getID());
            System.out.println(access.getLogin());
            System.out.println(access.getPassword());

        userDao = new MySqlUserDao();
        User user = userDao.read(access);
        System.out.println(user.getUserID());
        System.out.println(user.getFirstName());
        System.out.println(user.getDepartment());
        System.out.println(user.getPosition());
        System.out.println(user.getMaterialID());
        System.out.println(user.getLogin());


        materialDao = new MySqlMaterialDao();
        InfoMaterial infoMaterial = materialDao.read(user);
        System.out.println(infoMaterial.getMaterialID());
        System.out.println(infoMaterial.getMaterilName());
        System.out.println(infoMaterial.getMaterialDescription());

        listDao = new MySqlListOfTopicsDao();
        List<ListOfTopicsInInfoMaterial> listOfTopics = listDao.read(infoMaterial);
        for(int i=0; i<listOfTopics.size(); i++){
        System.out.println(listOfTopics.get(i).getMaterialName());
        System.out.print(listOfTopics.get(i).getListTopicID()+ " ");
        System.out.println(listOfTopics.get(i).getTopicName());
        }

        topicDao = new MySqlTopicDao();
        Topic topic = topicDao.read(listOfTopics.get(0));
        System.out.println(topic.getTopicID());
        System.out.println(topic.getTopicName());
        System.out.println(topic.getTopicDescription());

        sectionDao = new MySqlSectionDao();
        List<Section> section = sectionDao.read(topic);
        for(int i=0; i<section.size(); i++){
        System.out.println(section.get(i).getSectionTopicName());
        System.out.println(section.get(i).getSectionName());
        System.out.println(section.get(i).getSectionContext());
        }
        } catch (DAOException ex) {
            System.out.println("Error in main");
            return;
        }finally {
            try {
                accessDao.close();
            }catch(DAOException ex){System.out.println("Error closing Access");
            }
            try {
                userDao.close();
            } catch (DAOException ex) {
                System.out.println("Error closing User");
            }
            try {
                materialDao.close();
            } catch (DAOException ex) {
                System.out.println("Error closing Material");
            }
            try {
                listDao.close();
            } catch (DAOException ex) {
                System.out.println("Error closing List");
            }
            try {
                topicDao.close();
            } catch (DAOException ex) {
                System.out.println("Error closing Topic");
            }
            try {
                sectionDao.close();
            } catch (DAOException ex) {
                System.out.println("Error closing Section");
            }
        }
    }
}






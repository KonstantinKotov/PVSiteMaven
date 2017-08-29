import dao.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Created by k.kotov on 27.08.2017.
 */
public class MySqlTopicDaoTest {
    static Topic expectedTopic = new Topic();
    static Topic actualTopic = null;
    static MySqlTopicDao topicDao = null;

    @BeforeClass
    public static void setUpClass(){
        expectedTopic.setTopicID(9);
        expectedTopic.setTopicName("Тестовая тема");
        expectedTopic.setTopicDescription("Описание тестовой темы");

        ListOfTopicsInInfoMaterial listOfTopic = new ListOfTopicsInInfoMaterial();
        listOfTopic.setListTopicID(9);

        MySqlDaoFactory daoFactory = new MySqlDaoFactory();
        try {
            topicDao = daoFactory.createTopicDao();
            actualTopic = topicDao.read(listOfTopic);
        } catch (DAOException ex) {
            Logger.getLogger(MySqlTopicDaoTest.class.getName()).log(Level.SEVERE,
                    "Error while reading user", ex);
        }
    }

    @Test
    public  void  testRead(){
        assertEquals(expectedTopic.getTopicID(), actualTopic.getTopicID());
        assertEquals(expectedTopic.getTopicName(), actualTopic.getTopicName());
        assertEquals(expectedTopic.getTopicDescription(), actualTopic.getTopicDescription());
    }

    @AfterClass
    public static void tearDownClass(){

        if(topicDao != null){
            try {
                topicDao.close();
            } catch (DAOException ex) {
                Logger.getLogger(MySqlTopicDaoTest.class.getName()).log(Level.SEVERE,
                        "Error while closing", ex);
            }
        }
    }
}

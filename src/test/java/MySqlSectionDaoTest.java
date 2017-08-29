import dao.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class MySqlSectionDaoTest {

        static List<Section> expectedList = new ArrayList<>();
        static List<Section> actualList;
        static Section expectedSection = new Section();
        static Section actualSection;
        static MySqlSectionDao sectionDao;

        @BeforeClass
        public static void setUpClass(){
            expectedSection.setSectionTopicName("Тестовая тема");
            expectedSection.setSectionName("Тестовый раздел");
            expectedSection.setSectionContext("Содержание Т8 Р1");

            expectedList.add(expectedSection);

            Topic topic = new Topic();
            topic.setTopicID(9);

            MySqlDaoFactory daoFactory = new MySqlDaoFactory();
            try {
                sectionDao = daoFactory.createSectionDao();
                actualList = sectionDao.read(topic);
            } catch (DAOException ex) {
                Logger.getLogger(MySqlListOfTopicsDaoTest.class.getName()).log(Level.SEVERE,
                        "Error while reading section", ex);
            }
        }

        @Test
        public void testRead(){
            assertEquals(expectedList.get(0).getSectionTopicName(), actualList.get(0).getSectionTopicName());
            assertEquals(expectedList.get(0).getSectionName(), actualList.get(0).getSectionName());
            assertEquals(expectedList.get(0).getSectionContext(), actualList.get(0).getSectionContext());
        }

        @AfterClass
        public static void tearDownClass(){
            if(sectionDao != null){
                try {
                    sectionDao.close();
                } catch (DAOException ex) {
                    Logger.getLogger(MySqlSectionDaoTest.class.getName()).log(Level.SEVERE,
                            "Error while closing", ex);
                }
            }
        }

}

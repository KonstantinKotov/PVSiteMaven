import dao.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;

/**
 * Created by k.kotov on 27.08.2017.
 */
public class MySqlListOfTopicsDaoTest {
    static List<ListOfTopicsInInfoMaterial> expectedList = new ArrayList<>();
    static List<ListOfTopicsInInfoMaterial> actualList;
    static ListOfTopicsInInfoMaterial expectedListTop = new ListOfTopicsInInfoMaterial();
    static ListOfTopicsInInfoMaterial actualListTop;
    static MySqlListOfTopicsDao listOfTopicsDao;

    @BeforeClass
    public static void setUpClass(){
        expectedListTop.setListID(10);
        expectedListTop.setListTopicID(9);
        expectedListTop.setMaterialName("Тестовый материал");

        expectedList.add(expectedListTop);

        InfoMaterial infoMaterial = new InfoMaterial();
        infoMaterial.setMaterialID(3);

        MySqlDaoFactory daoFactory = new MySqlDaoFactory();
        try {
            listOfTopicsDao = daoFactory.createListOfTopicsDao();
            actualList = listOfTopicsDao.read(infoMaterial);
        } catch (DAOException ex) {
            Logger.getLogger(MySqlListOfTopicsDaoTest.class.getName()).log(Level.SEVERE,
                    "Error while reading listoftop", ex);
        }
    }

    @Test
    public void testRead(){
        assertEquals(expectedList.get(0).getListID(), actualList.get(0).getListID());
        assertEquals(expectedList.get(0).getListTopicID(), actualList.get(0).getListTopicID());
        assertEquals(expectedList.get(0).getMaterialName(), actualList.get(0).getMaterialName());
    }

    @AfterClass
    public static void tearDownClass(){
        if(listOfTopicsDao != null){
            try {
                listOfTopicsDao.close();
            } catch (DAOException ex) {
                Logger.getLogger(MySqlListOfTopicsDaoTest.class.getName()).log(Level.SEVERE,
                        "Error while closing", ex);
            }
        }
    }
}

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
public class MySqlMaterialDaoTest {
    static InfoMaterial expected = new InfoMaterial();
    static InfoMaterial infoMaterial = null;
    static MySqlMaterialDao materialDao = null;

    @BeforeClass
    public static void setUpClass(){
        expected.setMaterialID(3);
        expected.setMaterilName("Тестовый материал");
        expected.setMaterialDescription("Описание тестового материала");

        User user = new User();
        user.setMaterialID(1);

        MySqlDaoFactory daoFactory = new MySqlDaoFactory();
        try {
            materialDao = daoFactory.createMaterialDao();
            infoMaterial = materialDao.read(user);
        } catch (DAOException ex) {
            Logger.getLogger(MySqlMaterialDaoTest.class.getName()).log(Level.SEVERE,
                    "Error while reading user", ex);
        }
    }

    @Test
    public  void  testRead(){
        assertEquals(expected.getMaterialID(), infoMaterial.getMaterialID());
        assertEquals(expected.getMaterilName(), infoMaterial.getMaterilName());
        assertEquals(expected.getMaterialDescription(), infoMaterial.getMaterialDescription());
    }

    @AfterClass
    public static void tearDownClass(){

        if(materialDao != null){
            try {
                materialDao.close();
            } catch (DAOException ex) {
                Logger.getLogger(MySqlMaterialDaoTest.class.getName()).log(Level.SEVERE,
                        "Error while closing", ex);
            }
        }
    }
}

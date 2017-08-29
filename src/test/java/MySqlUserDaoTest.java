import dao.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlUserDaoTest {
    static User userExpected = new User();
    static User user;
    static MySqlUserDao userDao;

    @BeforeClass
    public  static void setUpClass(){

        userExpected.setUserID(4);
        userExpected.setFirstName("testUserName1");
        userExpected.setSecondName("testUserName2");
        userExpected.setMidName("testUserName3");
        userExpected.setSex("муж");
        userExpected.setPosition("Менеджер");
        userExpected.setDepartment("Отдел продаж");
        userExpected.setMaterialID(1);

        Access access = new Access();
        access.setID(3);
        MySqlDaoFactory daoFactory = new MySqlDaoFactory();

        try {
            userDao = daoFactory.createUserDao();
            user = userDao.read(access);
        } catch (DAOException ex) {
            Logger.getLogger(MySqlUserDaoTest.class.getName()).log(Level.SEVERE,
                    "Error while reading user", ex);
        }
    }

    @Test
    public  void testRead(){
        assertEquals(userExpected.getUserID(), user.getUserID());
        assertEquals(userExpected.getFirstName(), user.getFirstName());
        assertEquals(userExpected.getSecondName(), user.getSecondName());
        assertEquals(userExpected.getMidName(), user.getMidName());
        assertEquals(userExpected.getSex(), user.getSex());
        //assertEquals(userExpected.getBirthDate(), user.getBirthDate());
        //assertEquals(userExpected.getWorkFromDate(), user.getWorkFromDate());
        assertEquals(userExpected.getPosition(), user.getPosition());
        assertEquals(userExpected.getDepartment(), user.getDepartment());
        assertEquals(userExpected.getMaterialID(), user.getMaterialID());
        //assertEquals(userExpected.getLogin(), user.getLogin());
    }

    @AfterClass
    public static void tearDownClass(){
        //user = null;
        //userExpected = null;
        if(userDao != null){
            try {
                userDao.close();
            } catch (DAOException ex) {
                Logger.getLogger(MySqlUserDaoTest.class.getName()).log(Level.SEVERE,
                        "Error while closing", ex);
            }
        }
    }
}

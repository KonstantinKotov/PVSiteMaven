
import dao.*;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class MySqlAccessDaoTest {

    @Test
    public void testRead() throws Exception{
        MySqlDaoFactory daoFactory = new MySqlDaoFactory();
        MySqlAccessDao accessDao = null;
        try {
            accessDao =daoFactory.createAccessDao();
            String login = "test";
            String expectedPassword = "test321";

            Access access = accessDao.read(login);
            String password = access.getPassword();
            assertEquals("Password problems", expectedPassword, password);

        } catch (DAOException e) {
            e.printStackTrace();
        }finally {
            if(accessDao != null) {
                try {
                    accessDao.close();
                }catch(DAOException ex){
                    Logger.getLogger(MySqlAccessDaoTest.class.getName()).log(Level.SEVERE,
                            "Error while closing", ex);
                }
            }
        }
    }
}

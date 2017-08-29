package TestServlet;

import dao.*;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ServletListner implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        MySqlDaoFactory daoFactory = new MySqlDaoFactory();
        HttpSession session = httpSessionEvent.getSession();

        try {
            MySqlAccessDao accessDao = daoFactory.createAccessDao();
            MySqlUserDao userDao = daoFactory.createUserDao();
            MySqlListOfTopicsDao listOfTopicsDao = daoFactory.createListOfTopicsDao();
            MySqlMaterialDao materialDao = daoFactory.createMaterialDao();
            MySqlTopicDao topicDao = daoFactory.createTopicDao();
            MySqlSectionDao sectionDao = daoFactory.createSectionDao();

            session.setAttribute("accessDao", accessDao);
            session.setAttribute("userDao", userDao);
            session.setAttribute("listOfTopicsDao", listOfTopicsDao);
            session.setAttribute("materialDao", materialDao);
            session.setAttribute("topicDao", topicDao);
            session.setAttribute("sectionDao", sectionDao);
        } catch (DAOException e) {
            DAOException t = new DAOException();
            Logger.getLogger(ServletListner.class.getName()).log(Level.SEVERE,
                    "Error while creating DAO", t);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Throwable t = null;
        HttpSession session = httpSessionEvent.getSession();
        TopicDao topicDao = (TopicDao) session.getAttribute("topicDao");
        SectionDao sectionDao = (SectionDao) session.getAttribute("sectionDao");
        MySqlAccessDao accessDao = (MySqlAccessDao) session.getAttribute("accessDao");
        MySqlUserDao userDao = (MySqlUserDao) session.getAttribute("userDao");
        MySqlListOfTopicsDao listOfTopicsDao = (MySqlListOfTopicsDao) session.getAttribute("listOfTopicsDao");
        MySqlMaterialDao materialDao = (MySqlMaterialDao) session.getAttribute("materialDao");

        try {
            accessDao.close();
        } catch (DAOException e) {
            t = new DAOException();
        }
        try {
            userDao.close();
        } catch (DAOException e) {
            t = new DAOException();
        }
        try {
            materialDao.close();
        } catch (DAOException e) {
            t = new DAOException();
        }
        try {
            listOfTopicsDao.close();
        } catch (DAOException e) {
            t = new DAOException();
        }
        try {
            topicDao.close();
        } catch (DAOException e) {
            t = new DAOException();
        }
        try {
            sectionDao.close();
        } catch (DAOException e) {
            t = new DAOException();
        }
        if (t != null){
            Logger.getLogger(ServletListner.class.getName()).log(Level.SEVERE,
                    "Error while closing resources", t);
        }
    }
}

package TestServlet;


import dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MethodServlet")
public class MethodServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequests(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequests(request, response);
    }

    public void pageTopics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = makeParamID(request, response, "topID");
        try {
        HttpSession session = request.getSession();
        TopicDao topicDao  = (MySqlTopicDao) session.getAttribute("topicDao");
        SectionDao sectionDao = (MySqlSectionDao) session.getAttribute("sectionDao");

            List<ListOfTopicsInInfoMaterial> listOfTopics =
                    (List<ListOfTopicsInInfoMaterial>) session.getAttribute("listOfTop");
            Topic topic = topicDao.read(listOfTopics.get(id));
            List<Section> listSections = sectionDao.read(topic);
            session.setAttribute("listOfSections", listSections);
            User user = (User) session.getAttribute("user");

            request.setAttribute("user", user.getFirstName() + " " + user.getSecondName());
            request.setAttribute("topic_name", topic.getTopicName());
            request.setAttribute("topic_description", topic.getTopicDescription());
            for (int i = 0; i < listSections.size(); i++) {
                request.setAttribute("section_name" + i, listSections.get(i).getSectionName());
            }
            getServletContext().getRequestDispatcher("/page3.jsp").forward(request, response);
        } catch (DAOException e) {
            request.setAttribute("error", "Sorry, we have a problem with DB and we are working on it.");
            getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Sorry, we have a problem and we are working on it.");
            getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    public void pageSections(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = makeParamID(request, response, "sectionID");
        HttpSession session = request.getSession();

        try {
            List<Section> listOfSections =
                    (List<Section>) session.getAttribute("listOfSections");
            Section section = listOfSections.get(id);
            User user = (User) session.getAttribute("user");
            request.setAttribute("user", user.getFirstName() + " " + user.getSecondName());
            request.setAttribute("section_name", section.getSectionName());
            request.setAttribute("section_content", section.getSectionContext());
            for (int i = 0; i < listOfSections.size(); i++) {
                request.setAttribute("section_name" + i, listOfSections.get(i).getSectionName());
            }
            getServletContext().getRequestDispatcher("/page4.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Sorry, we have a problem and we are working on it. Exception");
            getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<ListOfTopicsInInfoMaterial> listOfTopics =
                (List<ListOfTopicsInInfoMaterial>) session.getAttribute("listOfTop");
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user.getFirstName() + " " + user.getSecondName());
        for (int i = 0; i < listOfTopics.size(); i++) {
            request.setAttribute("topic" + i, listOfTopics.get(i).getTopicName());
        }
        getServletContext().getRequestDispatcher("/page2.jsp").forward(request, response);
    }

    public void signInPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        try {

            MySqlAccessDao accessDao = (MySqlAccessDao) session.getAttribute("accessDao");
            MySqlUserDao userDao = (MySqlUserDao) session.getAttribute("userDao");
            MySqlListOfTopicsDao listOfTopicsDao = (MySqlListOfTopicsDao) session.getAttribute("listOfTopicsDao");
            MySqlMaterialDao materialDao = (MySqlMaterialDao) session.getAttribute("materialDao");

            if (login.isEmpty()) {
                request.setAttribute("message", "Fill in login field");
            } else {
                Access access = accessDao.read(login);
                if (login.equals(access.getLogin()) && password.equals(access.getPassword())) {
                    User user = userDao.read(access);

                    session.setAttribute("user", user);
                    InfoMaterial material = materialDao.read(user);
                    List<ListOfTopicsInInfoMaterial> listOfTopicsInInfoMaterial = listOfTopicsDao.read(material);
                    session.setAttribute("listOfTop", listOfTopicsInInfoMaterial);

                    request.setAttribute("user", user.getFirstName() + " " + user.getSecondName());
                    for (int i = 0; i < listOfTopicsInInfoMaterial.size(); i++) {
                        request.setAttribute("topic" + i, listOfTopicsInInfoMaterial.get(i).getTopicName());
                    }
                    getServletContext().getRequestDispatcher("/page2.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Incorrect login or password");
                }
            }
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (DAOException e) {
            request.setAttribute("error", "Sorry, we have a problem and we are working on it");
            getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        } catch (Throwable e) {
            request.setAttribute("error", "Sorry, we have a problem and we are working on it. ExceptionT");
            request.setAttribute("error1", e.getStackTrace());
            request.setAttribute("errorClass", e.getClass());
            getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    public void handleRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("pageManagerID");

        try {

            switch (page) {
                case "signIn":
                    signInPage(request, response);
                    break;
                case "topics":
                    pageTopics(request, response);
                    break;
                case "sections":
                    pageSections(request, response);
                    break;
                case "home":
                    home(request, response);
                    break;
                case "signOut":
                    signOut(request, response);
                    break;
                case "profile":
                    profile(request, response);
                    break;
            }
        } catch (Throwable t) {
            request.setAttribute("error", "Sorry, we have a problem and we are working on it.");
            getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    public int makeParamID(HttpServletRequest request, HttpServletResponse response, String param) throws ServletException, IOException {
        String req = request.getParameter(param);
        Integer page = Integer.parseInt(req);
        return page;
    }

    public  void signOut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Throwable t = null;
        HttpSession session = request.getSession();
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
            request.setAttribute("error", "Problem with resources closing");
        }
        try {
            userDao.close();
        } catch (DAOException e) {
            t = new DAOException();
            request.setAttribute("error", "Problem with resources closing");
        }
        try {
            materialDao.close();
        } catch (DAOException e) {
            t = new DAOException();
            request.setAttribute("error", "Problem with resources closing");
        }
        try {
            listOfTopicsDao.close();
        } catch (DAOException e) {
            t = new DAOException();
            request.setAttribute("error", "Problem with resources closing");
        }
        try {
           topicDao.close();
        } catch (DAOException e) {
            request.setAttribute("error", "Problem with resources closing");
            t = new DAOException();
        }
        try {
            sectionDao.close();
        } catch (DAOException e) {
            request.setAttribute("error", "Problem with resources closing");
            t = new DAOException();
        }
        if (t != null){
            getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
        session.invalidate();
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public  void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<ListOfTopicsInInfoMaterial> listOfTopicsInInfoMaterial =
                (List<ListOfTopicsInInfoMaterial>) session.getAttribute("listOfTop");

        request.setAttribute("userName", user.getFirstName() + " " + user.getSecondName());
        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("secondName", user.getSecondName());
        request.setAttribute("midName", user.getMidName());
        request.setAttribute("sex", user.getSex());
        request.setAttribute("birthDate", user.getBirthDate());
        request.setAttribute("workStartDate", user.getWorkFromDate());
        request.setAttribute("department", user.getDepartment());
        request.setAttribute("position", user.getPosition());

        for (int i = 0; i < listOfTopicsInInfoMaterial.size(); i++) {
            request.setAttribute("topic" + i, listOfTopicsInInfoMaterial.get(i).getTopicName());
        }

        getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}


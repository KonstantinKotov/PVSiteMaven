<%--
  Created by IntelliJ IDEA.
  User: k.kotov
  Date: 05.08.2017
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="dao.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>page3</title>
    <link href="style1.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<div id="wrapper">
    <div id="header">Корпоративная система обучения компании Х
        <div id="userinheader">Пользователь: <%= request.getAttribute("user")
        %></div>
    </div>
    <div id="content">
        <div id="topmenu">
            <ul>
                <li><a href="/method?pageManagerID=home">Home</a></li>
                <li>Статистика</li>
                <li><a href="/method?pageManagerID=profile">Профиль</a></li>
                <li><a href="/method?pageManagerID=signOut">Выйти</a></li>
            </ul>
        </div>
        <div id="leftmenu"><ul>
            Доступные разделы:
            <p></p>

            <li><%if (request.getAttribute("section_name0") == null){%>
                <% }else {%>
                <a href="/method?sectionID=0&pageManagerID=sections"><%= request.getAttribute("section_name0")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("section_name1") == null){%>
                <% }else {%>
                <a href="/method?sectionID=1&pageManagerID=sections"><%= request.getAttribute("section_name1")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("section_name2") == null){%>
                <% }else {%>
                <a href="/method?sectionID=2&pageManagerID=sections"><%= request.getAttribute("section_name2")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("section_name3") == null){%>
                <% }else {%>
                <a href="/method?sectionID=3&pageManagerID=sections"><%= request.getAttribute("section_name3")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("section_name4") == null){%>
                <% }else {%>
                <a href="/method?sectionID=4&pageManagerID=sections"><%= request.getAttribute("section_name4")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("section_name5") == null){%>
                <% }else {%>
                <a href="/method?sectionID=5&pageManagerID=sections"><%= request.getAttribute("section_name5")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("section_name6") == null){%>
                <% }else {%>
                <a href="/method?sectionID=6&pageManagerID=sections"><%= request.getAttribute("section_name6")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("section_name7") == null){%>
                <% }else {%>
                <a href="/method?sectionID=7&pageManagerID=sections"><%= request.getAttribute("section_name7")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("section_name8") == null){%>
                <% }else {%>
                <a href="/method?sectionID=8&pageManagerID=sections"><%= request.getAttribute("section_name8")%>
                    <%}%></a></li>
        </ul></div>
        <div id="section">
            <div id="heading"> <%= request.getAttribute("topic_name")%></div>
            <div id="sectioncontent"><%= request.getAttribute("topic_description")%></div>
        </div>
    </div>
</div>
</body>
</html>

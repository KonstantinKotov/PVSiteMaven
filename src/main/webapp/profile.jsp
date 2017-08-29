<%--
  Created by IntelliJ IDEA.
  User: k.kotov
  Date: 05.08.2017
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>page2</title>
    <link href="style1.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<div id="wrapper">
    <div id="header">Корпоративная система обучения компании Х
        <div id="userinheader">Пользователь: <%= request.getAttribute("userName")
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
            Доступные темы:
            <p></p>

            <li><%if (request.getAttribute("topic0") == null){%>
                <% }else {%>
                <a href="/method?topID=0&pageManagerID=topics"><%= request.getAttribute("topic0")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("topic1") == null){%>
                <% }else {%>
                <a href="/method?topID=1&pageManagerID=topics"><%= request.getAttribute("topic1")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("topic2") == null){%>
                <% }else {%>
                <a href="/method?topID=2&pageManagerID=topics"><%= request.getAttribute("topic2")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("topic3") == null){%>
                <% }else {%>
                <a href="/method?topID=3&pageManagerID=topics"><%= request.getAttribute("topic3")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("topic4") == null){%>
                <% }else {%>
                <a href="/method?topID=4&pageManagerID=topics"><%= request.getAttribute("topic4")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("topic5") == null){%>
                <% }else {%>
                <a href="/method?topID=5&pageManagerID=topics"><%= request.getAttribute("topic5")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("topic6") == null){%>
                <% }else {%>
                <a href="/method?topID=6&pageManagerID=topics"><%= request.getAttribute("topic6")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("topic7") == null){%>
                <% }else {%>
                <a href="/method?topID=7&pageManagerID=topics"><%= request.getAttribute("topic7")%>
                    <%}%></a></li>
            <li><%if (request.getAttribute("topic8") == null){%>
                <% }else {%>
                <a href="/method?topID=8&pageManagerID=topics"><%= request.getAttribute("topic8")%>
                    <%}%></a></li>
        </ul></div>
        <div id="section">
            <div id="heading">Данные пользователя: <%= request.getAttribute("userName")%></div>
            <div id="sectioncontent">
                <ul>
                    <li>Фамилия: <%= request.getAttribute("firstName")%></li>
                    <li>Имя: <%= request.getAttribute("secondName")%></li>
                    <li>Отчество: <%= request.getAttribute("midName")%></li>
                    <li>Пол: <%= request.getAttribute("sex")%></li>
                    <li>Дата рождения: <%= request.getAttribute("birthDate")%></li>
                    <li>Дата трудоустройства: <%= request.getAttribute("workStartDate")%></li>
                    <li>Отдел: <%= request.getAttribute("department")%></li>
                    <li>Должность: <%= request.getAttribute("position")%> </li>
                </ul>

            </div>
        </div>
    </div>
</div>
</body>
</html>

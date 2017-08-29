<%--
  Created by IntelliJ IDEA.
  User: k.kotov
  Date: 03.08.2017
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<p> Some problem happens</p>
<%= request.getAttribute("error")%><%= request.getAttribute("error1")%>
<%= request.getAttribute("errorClass")%>
<li><%if (request.getAttribute("error0") == null){%>
    error 0 - not detected
    <% }else {%>
    <%= request.getAttribute("error0")%>
        <%}%></li>
<li><%if (request.getAttribute("error1") == null){%>
    error 1 - not detected
    <% }else {%>
    <%= request.getAttribute("error1")%>
        <%}%></li>
<li><%if (request.getAttribute("error2") == null){%>
    error 2 - not detected
    <% }else {%>
    <%= request.getAttribute("error2")%>
        <%}%></li>
<li><%if (request.getAttribute("error3") == null){%>
    error 3 - not detected
    <% }else {%>
    <%= request.getAttribute("error3")%>
        <%}%></li>
<li><%if (request.getAttribute("error4") == null){%>
    error 4 - not detected
    <% }else {%>
    <%= request.getAttribute("error4")%>
        <%}%></li>
<li><%if (request.getAttribute("error5") == null){%>
    error 5 - not detected
    <% }else {%>
    <%= request.getAttribute("error5")%>
        <%}%></li>

</body>
</html>

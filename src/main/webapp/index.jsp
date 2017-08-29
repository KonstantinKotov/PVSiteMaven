<%@ page import="dao.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>index</title>
  <link href="style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
  <div id="header">
    <p style="text-align: center; font-size: 24px;">Корпоративная система обучения компании Х</p>
  </div>
  <div id="content">
    <div id="text">Вход в систему</div>
    <div id="form_log">
      <form id="log" action="/method" method="post">
        <p>Login</p>
        <input  type="text" name="login">
        <p>Password</p>
        <input type="password" name="password">
        <input type="hidden" name="pageManagerID" value="signIn">
        <p><input type="submit" value="Sign In"></p>
      </form>
      <div id="reject">
        <p><%if (request.getAttribute("message") == null){%>
          Enter login and password
          <% }else {%>
          <%= request.getAttribute("message")%>
          <%}%></p>
      </div>
    </div>
  </div>

</div>
</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: Rashgild
  Date: 18.05.2017
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page language="java"%>
<%@ page import="org.apache.log4j.Logger" %>
<% Logger logger=Logger.getLogger("simple");

%>
<html>
  <head>
    <title>Сервис работы с ЭЛН - SingAndCrypt</title>
    <style>

    </style>

  </head>
  <body>
  <%logger.info("Logger is start!");%>

  <header>
    <img src="/pic/logo-75x50.jpg" width="75" height="50" alt="logo" />
    <h1>Super Bad</h1>
    <nav><a>First Link</a><a>Second Link</a><a>Third Link</a></nav>
  </header>


  <form method="get" action="sShowConf">
    <input type="submit" value="КОНФ"  >
  </form>

  <form method="get" action="GetLnNumber">
    <input type="submit" value="Отправить документ"  >
  </form>

  <form method="get" action="sPrparsefilelnlpu">
    <input type="submit" value="ParseLn"  >
  </form>


  <form method="get" action="sNewLnNumRange">
    <input type="submit" value="Получить цифиры"  >
  </form>
  </body>
</html>

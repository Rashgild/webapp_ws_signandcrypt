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
  </head>
  <body>
  <%logger.info("Logger is work!");%>




  <form method="get" action="GetLnNumber">
    <input type="submit" value="Отправить документ"  >
  </form>

  <form method="get" action="doSomeOperation">
    <input type="submit" value="OK2"  >
  </form>


  </body>
</html>

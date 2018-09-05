
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

  <script src="res/js/jquery-3.2.1.min.js"></script>
  <script src="res/js/forward.js"></script>

</head>
<body>
<%logger.info("Logger is start!");%>

<header>
  <img src="res/pic/medosLogo-200x200.png" width="75" height="50" alt="" />
  <img src="res/pic/FSSlogo-208x191.gif" width="75" height="50" alt="" />
  <h1>Сервис работы с ЭЛН</h1>
</header>
<div class="content">
  <input id="config" class="button" name="submit" value="Конфигурация" role="button" type="submit">
  <%--<input id="test" class="button" name="submit" value="re-Конфигурация" role="button" type="submit">--%>
</div>

<div class="version">

</div>
</body>

<script>

    $(document).ready(function() {
        $.ajax({
            type:"GET",
            url: "CurrentVersion",
            success: function (response){
                $(".version").html(response);
            }
        });
        //forwardbyClick("#config.button","/WEB-INF/configurator.jsp");
    });

    forwardbyClick("#config.button","/WEB-INF/configurator.jsp");
</script>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page language="java"%>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="ru.rashgild.utils.GlobalVariables" %>
<% Logger logger=Logger.getLogger("simple");

%>
<html>
<head>
    <title>Сервис работы с ЭЛН - SingAndCrypt</title>
    <style>

    </style>

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/forward.js"></script>

</head>
<body>
<%logger.info("Logger is start!");%>

<header>
    <img src="${pageContext.request.contextPath}/resources/image/medosLogo-200x200.png" width="75" height="50" alt="" />
    <img src="${pageContext.request.contextPath}/resources/image/FSSlogo-208x191.gif" width="75" height="50" alt="" />
    <img src="${pageContext.request.contextPath}/resources/image/logo-75x50.jpg" width="75" height="50" alt="" />
    <h1>Сервис работы с ЭЛН</h1>
</header>
<div class="content">
    <input id="config" class="button" name="submit" value="Конфигурация" role="button" type="submit">
    <%--<input id="test" class="button" name="submit" value="re-Конфигурация" role="button" type="submit">--%>
</div>

<div class="version">
    <%=GlobalVariables.innerApi%>
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

<%@ page import="ru.my.utils.GlobalVariables" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>SignAndcrypt</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style> .ex {
        border: 1px dashed #634f36;
        background: #ebff95;
        font-family: "Courier New", Courier, monospace;
        padding: 7px;
        margin: 0 0 1em;
        white-space: pre-wrap;
    }

    .ex2 {
        border: 1px dashed #634f36;
        background: #ffcbd5;
        font-family: "Courier New", Courier, monospace;
        padding: 7px;
        margin: 0 0 1em;
        white-space: pre-wrap;
    }
    </style>
</head>
<body>
<header>
    <img src="<%=GlobalVariables.innerApi%>res/pic/FSSlogo-208x191.gif" width="75" height="50" alt=""/>
    <img src="<%=GlobalVariables.innerApi%>res/pic/medosLogo-200x200.png" width="75" height="50" alt=""/>
    <h1>Результаты отправки ЛН:</h1>
</header>
<c:choose>
    <c:when test="${row!=null}">

        <c:if test="${row.getSTATUS()==0}">
            <p class="ex2">
        <span style="font-size: medium; color: #2d2d2b; ">
Id:${id} <br>
#ЭЛН:${row.getLNCODE()} <br>
Ответ: ${result.getMESS()} <br>
        <c:forEach items="${errors}" var="errors">
            Ошибка: ${errors.getERRMESS()} <br>
        </c:forEach>
        </span>
            </p>
        </c:if>
        <c:if test="${row.getSTATUS()==1}">
            <p class="ex">
        <span style="font-size: medium; color: #2d2d2b; ">
Id:${id} <br>
#ЭЛН:${row.getLNCODE()} <br>
Ответ: ЛН успешно выгружен
        </span>
            </p>
        </c:if>
    </c:when>
    <c:when test="${row==null && snils==true}">
        <h1>Непредвиденная ошибка. Попробуйте позже!</h1>
    </c:when>
    <c:when test="${row==null && snils==false}">
        <h1>У пациента отсутствует СНИЛС!</h1>
    </c:when>
</c:choose>
</body>
</html>

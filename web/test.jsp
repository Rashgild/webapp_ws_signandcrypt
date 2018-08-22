<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><%if (request.getAttribute("test") != null) {
    out.println("<p>" + request.getAttribute("test").toString() + "</p>");
}%></p>

<p><%if (request.getAttribute("data") != null) {
    out.println("<p>" + request.getAttribute("data").toString() + "</p>");
}%></p>

<input id="runbtn" onclick="f1()" value="<%=request.getAttribute("test")%>" type="button">
<input id="textbx" onclick="f1()" value="<%=request.getAttribute("test")%>" type="text">

<textarea id="SignThis" style="font-size:9pt;height:600px;width:100%;resize:none;border:0;">
    <%=request.getAttribute("test")%>
</textarea>
</body>

<script>
    function f1() {
      var d1=   document.getElementById("SignThis");

        alert(d1.value);
        alert(d1.innerHTML);
    }
</script>
</html>

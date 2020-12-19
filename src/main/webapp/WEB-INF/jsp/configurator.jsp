<%@ page import="ru.rashgild.utils.GlobalVariables" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Конфигурация</title>
    <style>
        input{
            width: 70%;
        }
    </style>
</head>
<body>
<H2>Database config</H2>
<label>Хост: <input name="title" value="<%=GlobalVariables.dbhost%>" class="input" type="text"></label><br>
<label>Driver: <input name="title" value="<%=GlobalVariables.dbdriver%>" class="input" type="text"></label><br>
<label>Login: <input name="title" value="" class="<%=GlobalVariables.dblogin%>" type="text"></label><br>
<label>Password: <input name="title" value="" class="<%=GlobalVariables.dbpassword%>" type="text"></label><br>

<H2>CryptoPro config</H2>

<label>Путь к хранилищу HDImageStore: <input name="title" value="<%=GlobalVariables.HDImageStorePath%>" class="input"  type="text"></label><br>
<label>CertStorage password: <input name="title" value="<%=GlobalVariables.passwordCertStor%>" class="input"  type="text"></label><br>
<label>CertAlias: <input name="title" value="<%=GlobalVariables.aliasCert%>" class="input"  type="text"></label><br>
<label>Путь к CertStorage: <input name="title" value="<%=GlobalVariables.pathToCert%>" class="input"  type="text"></label><br>

<label>ВК alias: <input name="title" value="<%=GlobalVariables.vkAlias%>" class="input"  type="text"></label><br>
<label>ВК password: <input name="title" value="<%=GlobalVariables.vkPass%>" class="input"  type="text"></label><br>
<label>Врач alias: <input name="title" value="<%=GlobalVariables.docAlias%>" class="input"  type="text"></label><br>
<label>Врач password: <input name="title" value="<%=GlobalVariables.docPass%>" class="input"  type="text"></label><br>
<label>MO alias: <input name="title" value="<%=GlobalVariables.moAlias%>" class="input"  type="text"></label><br>
<label>MO password: <input name="title" value="<%=GlobalVariables.moPass%>" class="input"  type="text"></label><br>

<h2>Other</h2>
<label>Logs save path: <input name="title" value="<%=GlobalVariables.pathtosave%>" class="input"  type="text"></label><br>
<label>sign XMLFileName: <input name="title" value="<%=GlobalVariables.signXMLFileName%>" class="input"  type="text"></label><br>
<label>crypt XMLFileName: <input name="title" value="<%=GlobalVariables.cryptXMLFileName%>" class="input"  type="text"></label><br>
<label>pathandnameSSL: <input name="title" value="<%=GlobalVariables.pathandnameSSL%>" class="input"  type="text"></label><br>
<label>passwordSSL: <input name="title" value="<%=GlobalVariables.passwordSSL%>" class="input"  type="text"></label><br>
<label>ОГРН МО: <input name="title" value="<%=GlobalVariables.ogrnMo%>" class="input"  type="text"></label><br>


<label>ОГРН МО: <input name="title" value="<%=GlobalVariables.country%>" class="input"  type="text"></label><br>

<label>DEF_LPU: <input name="title" value="<%=GlobalVariables.DefaultLPU%>" class="input"  type="text"></label><br>
<label>URL_API: <input name="title" value="<%=GlobalVariables.urlApi%>" class="input"  type="text"></label><br>
<label>INNER_API МО: <input name="title" value="<%=GlobalVariables.innerApi%>" class="input"  type="text"></label><br>

</body>

<javascript>
</javascript>
</html>

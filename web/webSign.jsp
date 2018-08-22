<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script language="javascript">window.allow_firefox_cadesplugin_async=1</script>
    <script language="javascript" src="cadesplugin_api.js"></script>
    <script type="text/javascript" src="res/js/nmcades_plugin_api.js"></script>
    <script language="javascript" src="res/js/cadesplugin_api.js"></script>

    <script type="text/javascript">

        var async_code_included = 0;

        function getXmlHttp(){
            var xmlhttp;
            try {
                xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (E) {
                    xmlhttp = false;
                }
            }
            if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
                xmlhttp = new XMLHttpRequest();
            }
            return xmlhttp;
        }

        function Common_CheckForPlugIn() {
            cadesplugin.set_log_level(cadesplugin.LOG_LEVEL_DEBUG);
            var canAsync = !!cadesplugin.CreateObjectAsync;
            if(canAsync)
            {
                include_async_code().then(function(){
                    return CheckForPlugIn_Async();
                });
            }else
            {
                return CheckForPlugIn_NPAPI();
            }
        }

        function isEdge() {
            var retVal = navigator.userAgent.match(/Edge\/./i);
            return retVal;
        }

        function include_async_code()
        {
            if(async_code_included)
            {
                return async_Promise;
            }
            FillCertList_Async('CertListBox');
            async_Promise = new Promise(function(resolve, reject){
                async_resolve = resolve;
            });
            async_code_included = 1;
            return async_Promise;
        }

        function FillCertList_Async(lstId) {
            cadesplugin.async_spawn(function *() {
                try {
                    var oStore = yield cadesplugin.CreateObjectAsync("CAdESCOM.Store");
                    if (!oStore) {
                        alert("store failed");
                        return;
                    }

                    yield oStore.Open();
                }
                catch (ex) {
                    alert("Certificate not found");
                    return;
                }

                var lst = document.getElementById(lstId);
                if(!lst)
                {
                    return;
                }
                lst.onchange = onCertificateSelected;
                lst.boxId = lstId;

                var certCnt;
                var certs;

                try {
                    certs = yield oStore.Certificates;
                    certCnt = yield certs.Count;
                }
                catch (ex) {
                    var errormes = document.getElementById("boxdiv").style.display = '';
                    return;
                }

                if(certCnt == 0)
                {
                    var errormes = document.getElementById("boxdiv").style.display = '';
                    return;
                }

                for (var i = 1; i <= certCnt; i++) {
                    var cert;
                    try {
                        cert = yield certs.Item(i);
                    }
                    catch (ex) {
                        alert("Ошибка при перечислении сертификатов: " + cadesplugin.getLastError(ex));
                        return;
                    }

                    var oOpt = document.createElement("OPTION");
                    var dateObj = new Date();
                    try {
                        var ValidFromDate = new Date((yield cert.ValidFromDate));
                        oOpt.text = new CertificateAdjuster().GetCertInfoString(yield cert.SubjectName, ValidFromDate);
                    }
                    catch (ex) {
                        alert("Ошибка при получении свойства SubjectName: " + cadesplugin.getLastError(ex));
                    }
                    try {
                        oOpt.value = yield cert.Thumbprint;
                    }
                    catch (ex) {
                        alert("Ошибка при получении свойства Thumbprint: " + cadesplugin.getLastError(ex));
                    }

                    lst.options.add(oOpt);
                }

                yield oStore.Close();
            });//cadesplugin.async_spawn
        }

        function CertificateAdjuster()
        {
        }

        CertificateAdjuster.prototype.extract = function(from, what)
        {
            certName = "";

            var begin = from.indexOf(what);

            if(begin>=0)
            {
                var end = from.indexOf(', ', begin);
                certName = (end<0) ? from.substr(begin) : from.substr(begin, end - begin);
            }

            return certName;
        }

        CertificateAdjuster.prototype.Print2Digit = function(digit)
        {
            return (digit<10) ? "0"+digit : digit;
        }

        CertificateAdjuster.prototype.GetCertDate = function(paramDate)
        {
            var certDate = new Date(paramDate);
            return this.Print2Digit(certDate.getUTCDate())+"."+this.Print2Digit(certDate.getMonth()+1)+"."+certDate.getFullYear() + " " +
                this.Print2Digit(certDate.getUTCHours()) + ":" + this.Print2Digit(certDate.getUTCMinutes()) + ":" + this.Print2Digit(certDate.getUTCSeconds());
        }

        CertificateAdjuster.prototype.GetCertName = function(certSubjectName)
        {
            return this.extract(certSubjectName, 'CN=');
        }

        CertificateAdjuster.prototype.GetIssuer = function(certIssuerName)
        {
            return this.extract(certIssuerName, 'CN=');
        }

        CertificateAdjuster.prototype.GetCertInfoString = function(certSubjectName, certFromDate)
        {
            return this.extract(certSubjectName,'CN=') + "; Выдан: " + this.GetCertDate(certFromDate);
        }



        function onCertificateSelected(event) {
            cadesplugin.async_spawn(function *(args) {
                var selectedCertID = args[0].selectedIndex;
                var thumbprint = args[0].options[selectedCertID].value.split(" ").reverse().join("").replace(/\s/g, "").toUpperCase();
                try {
                    var oStore = yield cadesplugin.CreateObjectAsync("CAdESCOM.Store");
                    yield oStore.Open();
                } catch (err) {
                    alert('Certificate not found');
                    return;
                }

                var all_certs = yield oStore.Certificates;
                var oCerts = yield all_certs.Find(cadesplugin.CAPICOM_CERTIFICATE_FIND_SHA1_HASH, thumbprint);

                if ((yield oCerts.Count) == 0) {
                    alert("Certificate not found");
                    return;
                }
                var certificate = yield oCerts.Item(1);
                FillCertInfo_Async(certificate, event.target.boxId);
            }, event.target);//cadesplugin.async_spawn
        }


        function FillCertInfo_Async(certificate, certBoxId)
        {
            var BoxId;
            var field_prefix;
            if(typeof(certBoxId) == 'undefined' || certBoxId == "CertListBox")
            {
                BoxId = 'cert_info';
                field_prefix = '';
            }else if (certBoxId == "CertListBox1") {
                BoxId = 'cert_info1';
                field_prefix = 'cert_info1';
            } else if (certBoxId == "CertListBox2") {
                BoxId = 'cert_info2';
                field_prefix = 'cert_info2';
            } else {
                BoxId = certBoxId;
                field_prefix = certBoxId;
            }
            cadesplugin.async_spawn (function*(args) {
                var Adjust = new CertificateAdjuster();

                var ValidToDate = new Date((yield args[0].ValidToDate));
                var ValidFromDate = new Date((yield args[0].ValidFromDate));
                var Validator = yield args[0].IsValid();
                var IsValid = yield Validator.Result;
                var hasPrivateKey = yield args[0].HasPrivateKey();
                var Now = new Date();

                alert(args[0].value)
                document.getElementById(args[1]).style.display = '';
                document.getElementById(args[2] + "subject").innerHTML = "Владелец: <b>" + Adjust.GetCertName(yield args[0].SubjectName) + "<b>";
                document.getElementById(args[2] + "issuer").innerHTML = "Издатель: <b>" + Adjust.GetIssuer(yield args[0].IssuerName) + "<b>";
                document.getElementById(args[2] + "from").innerHTML = "Выдан: <b>" + Adjust.GetCertDate(ValidFromDate) + "<b>";
                document.getElementById(args[2] + "till").innerHTML = "Действителен до: <b>" + Adjust.GetCertDate(ValidToDate) + "<b>";
                var pubKey = yield args[0].PublicKey();
                var algo = yield pubKey.Algorithm;
                var fAlgoName = yield algo.FriendlyName;
                document.getElementById(args[2] + "algorithm").innerHTML = "Алгоритм ключа: <b>" + fAlgoName + "<b>";
                if( hasPrivateKey ) {
                    var oPrivateKey = yield args[0].PrivateKey;
                    var sProviderName = yield oPrivateKey.ProviderName;
                    document.getElementById(args[2] + "provname").innerHTML = "Криптопровайдер: <b>" + sProviderName + "<b>";
                }
                if(Now < ValidFromDate) {
                    document.getElementById(args[2] + "status").innerHTML = "Статус: <span style=\"color:red; font-weight:bold; font-size:16px\"><b>Срок действия не наступил</b></span>";
                } else if( Now > ValidToDate){
                    document.getElementById(args[2] + "status").innerHTML = "Статус: <span style=\"color:red; font-weight:bold; font-size:16px\"><b>Срок действия истек</b></span>";
                } else if( !hasPrivateKey ){
                    document.getElementById(args[2] + "status").innerHTML = "Статус: <span style=\"color:red; font-weight:bold; font-size:16px\"><b>Нет привязки к закрытому ключу</b></span>";
                } else if( !IsValid ){
                    document.getElementById(args[2] + "status").innerHTML = "Статус: <span style=\"color:red; font-weight:bold; font-size:16px\"><b>Ошибка при проверке цепочки сертификатов</b></span>";
                } else {
                    document.getElementById(args[2] + "status").innerHTML = "Статус: <b> Действителен<b>";
                }
            }, certificate, BoxId, field_prefix);//cadesplugin.async_spawn
        }

        function CheckForPlugInUEC_Async()
        {
            var isUECCSPInstalled = false;

            cadesplugin.async_spawn(function *() {
                var oAbout = yield cadesplugin.CreateObjectAsync("CAdESCOM.About");

                var UECCSPVersion;
                var CurrentPluginVersion = yield oAbout.PluginVersion;
                if( typeof(CurrentPluginVersion) == "undefined")
                    CurrentPluginVersion = yield oAbout.Version;

                var PluginBaseVersion = "1.5.1633";
                var arr = PluginBaseVersion.split('.');

                var isActualVersion = true;

                if((yield CurrentPluginVersion.MajorVersion) == parseInt(arr[0]))
                {
                    if((yield CurrentPluginVersion.MinorVersion) == parseInt(arr[1]))
                    {
                        if((yield CurrentPluginVersion.BuildVersion) == parseInt(arr[2]))
                        {
                            isActualVersion = true;
                        }
                        else if((yield CurrentPluginVersion.BuildVersion) < parseInt(arr[2]))
                        {
                            isActualVersion = false;
                        }
                    }else if((yield CurrentPluginVersion.MinorVersion) < parseInt(arr[1]))
                    {
                        isActualVersion = false;
                    }
                }else if((yield CurrentPluginVersion.MajorVersion) < parseInt(arr[0]))
                {
                    isActualVersion = false;
                }

                if(!isActualVersion)
                {

                    document.getElementById('PluginEnabledImg').setAttribute("src", "Img/yellow_dot.png");
                    document.getElementById('PlugInEnabledTxt').innerHTML = "Плагин загружен, но он не поддерживает УЭК.";
                }
                else
                {
                    document.getElementById('PluginEnabledImg').setAttribute("src", "Img/green_dot.png");
                    document.getElementById('PlugInEnabledTxt').innerHTML = "Плагин загружен.";

                    try
                    {
                        var oUECard = yield cadesplugin.CreateObjectAsync("CAdESCOM.UECard");
                        UECCSPVersion = yield oUECard.ProviderVersion;
                        isUECCSPInstalled = true;
                    }
                    catch (err)
                    {
                        UECCSPVersion = "Нет информации";
                    }

                    if(!isUECCSPInstalled)
                    {
                        document.getElementById('PluginEnabledImg').setAttribute("src", "Img/yellow_dot.png");
                        document.getElementById('PlugInEnabledTxt').innerHTML = "Плагин загружен. Не установлен УЭК CSP.";
                    }
                }
                document.getElementById('PlugInVersionTxt').innerHTML = "Версия плагина: " + (yield CurrentPluginVersion.toString());
                document.getElementById('CSPVersionTxt').innerHTML = "Версия УЭК CSP: " + (yield UECCSPVersion.toString());
            }); //cadesplugin.async_spawn
        }
    </script>
</head>


<body>
<p id="info_msg" name="CertificateTitle">Сертификат:</p>

<div id="item_border" name="CertListBoxToHide">

    <select size="4" name="CertListBox" id="CertListBox" style="width:100%;resize:none;border:0;">
    </select>

    <div id="PluginEnabledImg"></div>
    <div id="PlugInEnabledTxt"></div>
    <div id="PlugInVersionTxt"></div>
    <div id="CSPVersionTxt"></div>

    <div id="cert_info">
        <div id="subject"></div>
        <div id="issuer"></div>
        <div id="from"></div>
        <div id="till"></div>
        <div id="algorithm"></div>
        <div id="provname"></div>
        <div id="status"></div>

       <%-- <div id="DataToSignTxtBox"></div>--%>

        <textarea id="DataToSignTxtBox" name="DataToSignTxtBox" style="height:200px;width:100%;resize:none;border:0;"></textarea>


        <p id="info_msg" name="SignatureTitle">Подпись:</p>
        <textarea id="SignatureTxtBox" name="DataToSignTxtBox" style="height:200px;width:100%;resize:none;border:0;"></textarea>
    </div>

    <div class="layout">
        <input id="SignBtn" value="Подписать" name="SignData" onclick="Common_SignCadesXML('CertListBox');" type="button">
    </div>
</div>
</body>




<script language="javascript">
    var txtDataToSign =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<!-- \n" +
        " Original XML doc file for sign example. \n" +
        "--> \n" +
        "<Envelope xmlns=\"urn:envelope\">\n" +
        "  <Data>\n" +
        "	Hello, World!\n" +
        "  </Data>\n" +
        "  <Node xml:id=\"nodeID\">\n" +
        "	Hello, Node!\n" +
        "  </Node>\n" +
        "</Envelope>";


    var canPromise = !!window.Promise;
    if(isEdge()) {
        ShowEdgeNotSupported();
    } else {
        if(canPromise) {
            cadesplugin.then(function () {
                    Common_CheckForPlugIn();
                },
                function(error) {
                    // document.getElementById('PluginEnabledImg').setAttribute("src", "Img/red_dot.png");
                    // document.getElementById('PlugInEnabledTxt').innerHTML = error;
                }
            );
        } else {
            window.addEventListener("message", function (event){
                    if (event.data == "cadesplugin_loaded") {
                        CheckForPlugIn_NPAPI('isPlugInEnabled');
                    } else if(event.data == "cadesplugin_load_error") {
                        // document.getElementById('PluginEnabledImg').setAttribute("src", "Img/red_dot.png");
                        // document.getElementById('PlugInEnabledTxt').innerHTML = "Плагин не загружен";
                    }
                },
                false);
            window.postMessage("cadesplugin_echo_request", "*");
        }
    }


    function Common_SignCadesXML(certListBoxId)
    {
        cadesplugin.async_spawn(function*(arg) {
            var e = document.getElementById(arg[0]);
            var selectedCertID = e.selectedIndex;
            if (selectedCertID == -1) {
                alert("Select certificate!!!");
                return;
            }

            var thumbprint = e.options[selectedCertID].value.split(" ").reverse().join("").replace(/\s/g, "").toUpperCase();

            try {
                var oStore = yield cadesplugin.CreateObjectAsync("CAdESCOM.Store");
                yield oStore.Open();
            } catch (err) {
                alert('Certificate not found');
                return;
            }

            var CAPICOM_CERTIFICATE_FIND_SHA1_HASH = 0;
            var all_certs = yield oStore.Certificates;
            var oCerts = yield all_certs.Find(CAPICOM_CERTIFICATE_FIND_SHA1_HASH, thumbprint);



            if ((yield oCerts.Count) == 0) {
                alert("Certificate not found");
                return;
            }
            var certificate = yield oCerts.Item(1);

            var dataToSign =document.getElementById("DataToSignTxtBox").value;
            //document.getElementById("DataToSignTxtBox").value;
            //var SignatureFieldTitle = document.getElementsByName("SignatureTitle");
            var Signature;
            try
            {
                //FillCertInfo_Async(certificate);
                var errormes = "";
                try {
                    var oSigner = yield cadesplugin.CreateObjectAsync("CAdESCOM.CPSigner");
                } catch (err) {
                    errormes = "Failed to create CAdESCOM.CPSigner: " + err.number;
                    throw errormes;
                }
                if (oSigner) {
                    yield oSigner.propset_Certificate(certificate);
                }
                else {
                    errormes = "Failed to create CAdESCOM.CPSigner";
                    throw errormes;
                }

                var oSignedXML = yield cadesplugin.CreateObjectAsync("CAdESCOM.SignedXML");

                var signMethod = "";
                var digestMethod = "";

                var pubKey = yield certificate.PublicKey();
                var algo = yield pubKey.Algorithm;
                var algoOid = yield algo.Value;
                if (algoOid == "1.2.643.7.1.1.1.1") {   // алгоритм подписи ГОСТ Р 34.10-2012 с ключом 256 бит
                    signMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102012-gostr34112012-256";
                    digestMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34112012-256";
                }
                else if (algoOid == "1.2.643.7.1.1.1.2") {   // алгоритм подписи ГОСТ Р 34.10-2012 с ключом 512 бит
                    signMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102012-gostr34112012-512";
                    digestMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34112012-512";
                }
                /*else if (algoOid == "1.2.643.2.2.19") {  // алгоритм ГОСТ Р 34.10-2001
                    signMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102001-gostr3411";
                    digestMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr3411";
                }*/
                else if (algoOid == "1.2.643.2.2.19") {  // алгоритм ГОСТ Р 34.10-2001
                   // signMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102001-gostr3411";
                    signMethod = "http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411";
                   // digestMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr3411";
                    digestMethod = "http://www.w3.org/2001/04/xmldsig-more#gostr3411";
            }
                else {
                    errormes = "Данная демо страница поддерживает XML подпись сертификатами с алгоритмом ГОСТ Р 34.10-2012, ГОСТ Р 34.10-2001";
                    throw errormes;
                }

                var CADESCOM_XML_SIGNATURE_TYPE_ENVELOPED = 0;

                if (dataToSign) {
                    // Данные на подпись ввели
                    yield oSignedXML.propset_Content(dataToSign);
                    yield oSignedXML.propset_SignatureType(CADESCOM_XML_SIGNATURE_TYPE_TEMPLATE);
                    //yield oSignedXML.propset_SignatureType(CADESCOM_XML_SIGNATURE_TYPE_ENVELOPED);
                    yield oSignedXML.propset_SignatureMethod(signMethod);
                    yield oSignedXML.propset_DigestMethod(digestMethod);

                    try {
                        Signature = yield oSignedXML.Sign(oSigner);
                    }
                    catch (err) {
                        errormes = "Не удалось создать подпись из-за ошибки: " + cadesplugin.getLastError(err);
                        throw errormes;
                    }
                }
                alert(Signature)
                document.getElementById("SignatureTxtBox").innerHTML = Signature;
                //SignatureFieldTitle[0].innerHTML = "Подпись сформирована успешно:";
            }
            catch(err)
            {
                //SignatureFieldTitle[0].innerHTML = "Возникла ошибка:";
                document.getElementById("SignatureTxtBox").innerHTML = err;
            }
        }, certListBoxId); //cadesplugin.async_spawn
    }
</script>
</html>

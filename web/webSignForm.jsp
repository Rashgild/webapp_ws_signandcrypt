<%@ page import="ru.my.utils.GlobalVariables" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="<%=GlobalVariables.innerApi%>res/js/jquery-3.2.1.min.js"></script>
    <meta charset="UTF-8">
    <title>SignAndCrypt</title>
    <script language="javascript">window.allow_firefox_cadesplugin_async = 1</script>
    <script language="javascript" src="<%=GlobalVariables.innerApi%>res/js/cadesplugin_api.js"></script>
    <script type="text/javascript" src="<%=GlobalVariables.innerApi%>res/js/nmcades_plugin_api.js"></script>
    <script type="text/javascript" src="<%=GlobalVariables.innerApi%>res/js/initcode.js"></script>
    <style>
        #item_border {
            background: #d2fdc9;
            border: 5px double #5a9251;
            padding: 10px;
            margin: 10px 0px 0px 0px;
            height: 350px;
        }

        #item_border2 {
            border: 2px solid gray;
            height: 350px;
        }

        option {
            font-size: 16px;
        }

        #CertListBox {
            background: #d2fdc9;
            height: 100%
        }

        #info_msg {
            margin: 0px;
            color: #7a7c88;
            padding: 10px 10px 10px 0px;
            font-weight: normal;
            font-size: 18px;
            line-height: 30px;
        }
    </style>
</head>
<body>
<p id="info_msg">Сертификат:</p>
<div id="item_border" name="CertListBoxToHide">
    <select size="4" name="CertListBox" id="CertListBox" style="width:100%;resize:none;border:0;">
    </select>
</div>
<%--<input value="жмяк" onclick="run();" type="button">--%>
<input id="runbtn" value="Подписать" type="button">
<br><br>
<p id="info_msg" name="SignatureTitle">Данные для подписи:</p>
<div id="item_border2" name="signThis">
    <textarea id="SignThis"
              style="font-size:9pt;height:600px;width:100%;resize:none;border:0;"><%=request.getAttribute("xml")%></textarea>
</div>
<p id="info_msg">Подписанные данные:</p>
<div id="item_border2" name="signed">
    <textarea id="SignatureTxtBox" readonly="readonly"
              style="font-size:9pt;height:600px;width:100%;resize:none;border:0;"></textarea>
</div>
<script type="text/javascript">
    var canPromise = !!window.Promise;
    if (isEdge()) {
        ShowEdgeNotSupported();
    } else {
        if (canPromise) {
            cadesplugin.then(function () {
                    Common_CheckForPlugIn();
                },
                function (error) {
                }
            );
        } else {
            window.addEventListener("message", function (event) {
                    if (event.data == "cadesplugin_loaded") {
                        CheckForPlugIn_NPAPI('isPlugInEnabled');
                    } else if (event.data == "cadesplugin_load_error") {

                    }
                },
                false);
            window.postMessage("cadesplugin_echo_request", "*");
        }
    }

    function SignCreate(cert, dataToSign) {
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
            var certificate = yield oCerts.Item(1);

            var Signature;
            try {
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


                var pubKey = yield certificate.PublicKey();
                var algo = yield pubKey.Algorithm;
                var algoOid = yield algo.Value;

                var signMethod = "";
                var digestMethod = "";

                if (algoOid == "1.2.643.7.1.1.1.1") {
                    signMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102012-gostr34112012-256";
                    digestMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34112012-256";

                    dataToSign = dataToSign.replace('SignatureMethod Algorithm="http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411"',
                        'SignatureMethod Algorithm="urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102012-gostr34112012-256"');
                    dataToSign = dataToSign.replace('DigestMethod Algorithm="http://www.w3.org/2001/04/xmldsig-more#gostr3411"',
                        'DigestMethod Algorithm="urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34112012-256"');
                }
                else if (algoOid == "1.2.643.7.1.1.1.2") {
                    signMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102012-gostr34112012-512";
                    digestMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34112012-512";

                    dataToSign = dataToSign.replace('SignatureMethod Algorithm="http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411"',
                        'SignatureMethod Algorithm="urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102012-gostr34112012-512"');
                    dataToSign = dataToSign.replace('DigestMethod Algorithm="http://www.w3.org/2001/04/xmldsig-more#gostr3411"',
                        'DigestMethod Algorithm="urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34112012-512"');
                }
                else if (algoOid == "1.2.643.2.2.19") {
                    signMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102001-gostr3411";
                    digestMethod = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr3411";
                }

                var CADESCOM_XML_SIGNATURE_TYPE_ENVELOPED = 2;

                if (dataToSign) {
                    // Данные на подпись ввели
                    yield oSignedXML.propset_Content(dataToSign);
                    yield oSignedXML.propset_SignatureType(CADESCOM_XML_SIGNATURE_TYPE_ENVELOPED);
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
                document.getElementById("SignatureTxtBox").innerHTML = Signature;
            }
            catch (err) {
                document.getElementById("SignatureTxtBox").innerHTML = err;
            }

            let temp = document.getElementById("SignatureTxtBox").value;
            $.ajax({
                type: "POST",
                url: "<%=GlobalVariables.innerApi%>api/sign/getXML",
                crossDomain: true,
                dataType: "json",
                data: temp,
                scriptCharset: "utf-8",
                success:(response) => {
                    alert("Сохранено!");
                }
            });
        }, cert, dataToSign);
    }

    function run() {
        let sContent = "";
        let dataToSign = document.getElementById("SignThis").value;
        if (dataToSign == "") {
            SignCreate('CertListBox', sContent);
        } else {
            SignCreate('CertListBox', dataToSign);
        }
    }

    $(document).ready(function () {
        $("#runbtn").click(function () {
            run();
        });
    });
</script>
</body>
</html>
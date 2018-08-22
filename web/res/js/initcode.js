/**
 * Created by rkurbanov on 23.04.2018.
 */

var async_code_included = 0;

/*    function getXmlHttp(){
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
 }*/

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

        //alert(args[0].value)
        //document.getElementById(args[1]).style.display = '';
        //document.getElementById(args[2] + "subject").innerHTML = "Владелец: <b>" + Adjust.GetCertName(yield args[0].SubjectName) + "<b>";
        //document.getElementById(args[2] + "issuer").innerHTML = "Издатель: <b>" + Adjust.GetIssuer(yield args[0].IssuerName) + "<b>";
        //document.getElementById(args[2] + "from").innerHTML = "Выдан: <b>" + Adjust.GetCertDate(ValidFromDate) + "<b>";
        //document.getElementById(args[2] + "till").innerHTML = "Действителен до: <b>" + Adjust.GetCertDate(ValidToDate) + "<b>";
        var pubKey = yield args[0].PublicKey();
        var algo = yield pubKey.Algorithm;
        var fAlgoName = yield algo.FriendlyName;
        //document.getElementById(args[2] + "algorithm").innerHTML = "Алгоритм ключа: <b>" + fAlgoName + "<b>";
        if( hasPrivateKey ) {
            var oPrivateKey = yield args[0].PrivateKey;
            var sProviderName = yield oPrivateKey.ProviderName;
            // document.getElementById(args[2] + "provname").innerHTML = "Криптопровайдер: <b>" + sProviderName + "<b>";
        }
        if(Now < ValidFromDate) {
            // document.getElementById(args[2] + "status").innerHTML = "Статус: <span style=\"color:red; font-weight:bold; font-size:16px\"><b>Срок действия не наступил</b></span>";
        } else if( Now > ValidToDate){
            // document.getElementById(args[2] + "status").innerHTML = "Статус: <span style=\"color:red; font-weight:bold; font-size:16px\"><b>Срок действия истек</b></span>";
        } else if( !hasPrivateKey ){
            // document.getElementById(args[2] + "status").innerHTML = "Статус: <span style=\"color:red; font-weight:bold; font-size:16px\"><b>Нет привязки к закрытому ключу</b></span>";
        } else if( !IsValid ){
            // document.getElementById(args[2] + "status").innerHTML = "Статус: <span style=\"color:red; font-weight:bold; font-size:16px\"><b>Ошибка при проверке цепочки сертификатов</b></span>";
        } else {
            // document.getElementById(args[2] + "status").innerHTML = "Статус: <b> Действителен<b>";
        }
    }, certificate, BoxId, field_prefix);//cadesplugin.async_spawn
}

/*        function CheckForPlugInUEC_Async()
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
 });
 }*/
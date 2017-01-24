package ru.my.service_operations.xmlFileLnLpu;

import org.apache.log4j.Logger;
import ru.my.entities.PrParseFileLnLpu;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.signAndCrypt.Encrypt;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import static ru.my.helpers_operations.GlobalVariables.cryptXMLFileName;
import static ru.my.helpers_operations.GlobalVariables.signXMLFileName;
import static ru.my.helpers_operations.StoredQuery.PrParse_Query1;
import static ru.my.helpers_operations.StoredQuery.PrParse_Query2;
import static ru.my.helpers_operations.WorkWithXML.SaveSOAPToXML;
import static ru.my.helpers_operations.WorkWithXML.SoapMessageToString;

/**
 * Created by rkurbanov on 19.05.2017.
 */

public class PrParseFileLnLpu_start {


   // private static final Logger log = Logger.getLogger(PrParseFileLnLpu_start.class);
   public static void Start(Integer disabilityId) throws Exception {

       Logger logger=Logger.getLogger("simple");


       logger.info("DisabilityDocument_id="+disabilityId);

       //GlobalVariables.Configure();
       GlobalVariables.setUp();

       logger.info("1)Formation skeleton");
       PrParseFileLnLpu prParseFileLnLpu =
               Skeleton.Create(PrParse_Query1(disabilityId.toString()),PrParse_Query2(disabilityId.toString()));

       GlobalVariables.prparse = prParseFileLnLpu;


       logger.info("2)Create message");
       SOAPMessage message = CreateSOAPMessage.Create(prParseFileLnLpu);

       //System.out.println("3)Save Soap-message");
       //WorkWithXML.SaveSOAPToXML("SoapMessage.xml",message);

       logger.info("3)Singing");
       //Signation.test(prParseFileLnLpu,message);
       message = Signation.Start(prParseFileLnLpu,message);

       System.out.println("3.5) Prepatre request");
       GlobalVariables.Request = SoapMessageToString(message);

       System.out.println("4) Crypting");
       //message = Encr.Encryption(message);

       MessageFactory mf = MessageFactory.newInstance();
       SOAPMessage NewMessg = mf.createMessage();
       NewMessg= Encrypt.CreateXMLAndEncrypt(NewMessg, signXMLFileName);
       SaveSOAPToXML(cryptXMLFileName,NewMessg);
       message = NewMessg;
    }
}

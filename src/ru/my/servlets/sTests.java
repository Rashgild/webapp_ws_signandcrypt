package ru.my.servlets;

import org.apache.log4j.Logger;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.SQL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rkurbanov on 03.07.2017.
 */
@WebServlet("/sTests")
public class sTests  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        GlobalVariables.setUp();
       /* Logger logger=Logger.getLogger("simple");
        response.setContentType("text/html ;charset=UTF-8");
        PrintWriter out = response.getWriter();



        String id = request.getParameter("id =");

        ResultSet resultSet = SQL.select("select * from patient where id= "+id);

        out.println("<html>");
        out.println("<head>" +
                " <script language=\"JavaScript\" type=\"text/javascript\" src=\"test.js\"></script> "+
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");

        try {
            while (resultSet.next()) {
                out.println("<H1>"+resultSet.getString("firstname")+"</H1>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");*/

        String xml = "<?xml version=\"1.0\"?>" +
                "<fil:TREAT_PERIOD wsu:Id=\"ELN_306742020070_3_doc\">" +
                "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">" +
                "<SignedInfo>" +
                "<CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>" +
                "<SignatureMethod Algorithm=\"urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102001-gostr3411\"/>" +
                "<Reference URI=\"\">" +
                "<Transforms>" +
                "<Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>" +
                "<Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>" +
                "</Transforms>" +
                "<DigestMethod Algorithm=\"urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr3411\"/>" +
                "<DigestValue>iAGGDHWtLTL+Z/X+1uEsru814DflJFM0cpkMW84dUp0=</DigestValue>" +
                "</Reference>" +
                "</SignedInfo>" +
                "<SignatureValue>cGYL65p2aHIOOKCkwPTHSVh0P6s0ZBtPfie/nY7aomqVuEyomLWX7LQRtJh/vfED\n" +
                "4kAGOgx9O2vnTLeVQQkodg==</SignatureValue>" +
                "<KeyInfo>" +
                "<X509Data>" +
                "<X509Certificate>CERT" +
                "</X509Certificate>" +
                "</X509Data>" +
                "</KeyInfo>" +
                "</Signature>" +
                "</fil:TREAT_PERIOD>";


        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));


        Logger logger=Logger.getLogger("simple");
        final Transforms transforms = new Transforms(document);
        transforms.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
       /* final Transforms transforms = new Transforms(document);
        transforms.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);*/

        Node root = document.getDocumentElement();
        System.out.println(root.getAttributes().getNamedItem("wsu:Id"));

        NodeList signature = root.getChildNodes();
        Node SignedInfo = signature.item(0);

        NodeList sig = SignedInfo.getChildNodes();
        Node signedInfo = sig.item(0);
        NodeList signedInfoList = signedInfo.getChildNodes();
        Node refrence = signedInfoList.item(2);
        NodeList refrenceList = refrence.getChildNodes();

        Node digestValue = refrenceList.item(2);
        Node signatureValue = sig.item(1);

        Node keyInfo = sig.item(2);
        NodeList keyInfoList = keyInfo.getChildNodes();
        Node x509data = keyInfoList.item(0);
        NodeList x509dataList = x509data.getChildNodes();
        Node x509cert = x509dataList.item(0);

        System.out.println(x509cert.getTextContent());
        System.out.println(digestValue.getTextContent());
        System.out.println(signatureValue.getTextContent());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}

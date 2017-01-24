package ru.my.signAndCrypt;

import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Rashgild on 10.11.16.
 */

public class Certificate {

    /**
     * Get certificate form CertStorage.
     * @param AliasCert ;
     * @param PasswordCertStor ;
     * @param PathToCert ;
     */
    public  static X509Certificate ExtractCertFromCertStore (String PasswordCertStor, String AliasCert, String PathToCert)
            throws Exception {
        KeyStore allCertStore = null;

        System.out.println("P:"+PathToCert);
        System.out.println("A:"+AliasCert+" P:"+PasswordCertStor);
        String storepass = PasswordCertStor;
        String alias = AliasCert;
        allCertStore = KeyStore.getInstance("CertStore");
        File f = new File(PathToCert);
        allCertStore.load(new FileInputStream(f), storepass.toCharArray());
        X509Certificate UserCert = (X509Certificate) allCertStore.getCertificate(alias);


        return UserCert;
    }

    /**
     * Get PrivatKey from storage by alias.
     * @param Alias alias in storage.
     * @param Password Password for storage.
     */
    public  static PrivateKey GetPrivateKey (String Password, String Alias) throws Exception {

        JCPXMLDSigInit.init();

        KeyStore keystore = KeyStore.getInstance("HDImageStore");
        keystore.load(null, null);
        PrivateKey privateKey = (PrivateKey)keystore.getKey(Alias, Password.toCharArray());
        //System.out.println(UserCert);

        return privateKey;
    }


    /**
     * Get cetificate from storage by alias.
     * @param Alias String with alias in storage.
     */
    public  static X509Certificate GetCertificateFromStorage(String Alias)
            throws NoSuchProviderException, KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {

        JCPXMLDSigInit.init();
        KeyStore ks = KeyStore.getInstance("HDImageStore");
        ks.load(null, null);
        X509Certificate certificate = (X509Certificate)ks.getCertificate(Alias);

        return certificate;
    }

    /**
     * Show certificate in X509 in format base64 in string.
     * @param certificate
     * @return String in base64
     */
    public static String certToBase64(X509Certificate certificate) throws CertificateEncodingException {
        StringWriter sw = new StringWriter();
        //sw.write("-----BEGIN CERTIFICATE-----\n");
        sw.write(DatatypeConverter.printBase64Binary(certificate.getEncoded()).replaceAll("(.{64})", "$1\n"));
        //sw.write("\n-----END CERTIFICATE-----\n");
        System.out.println(sw.toString());
        return sw.toString();
    }
}
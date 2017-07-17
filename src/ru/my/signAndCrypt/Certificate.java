package ru.my.signAndCrypt;

import org.apache.log4j.Logger;
import ru.CryptoPro.JCP.KeyStore.HDImage.HDImageStore;
import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;
import ru.my.helpers_operations.GlobalVariables;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

//Created by rashgild on 19.05.2017.

public class Certificate {

    /**
     * Get certificate form CertStorage.
     *
     * @param AliasCert        ;
     * @param PasswordCertStor ;
     * @param PathToCert       ;
     */
    protected static X509Certificate ExtractCertFromCertStore(String PasswordCertStor, String AliasCert, String PathToCert)
            throws Exception {

        KeyStore allCertStore;
        String storepass = PasswordCertStor;
        String alias = AliasCert;
        HDImageStore.setDir(GlobalVariables.HDImageStorePath);

        allCertStore = KeyStore.getInstance("CertStore");
        File f = new File(PathToCert);
        allCertStore.load(new FileInputStream(f), storepass.toCharArray());
        X509Certificate UserCert = (X509Certificate) allCertStore.getCertificate(alias);

        return UserCert;
    }

    /**
     * Get PrivatKey from storage by alias.
     *
     * @param Alias    alias in storage.
     * @param Password Password for storage.
     */
    public static PrivateKey GetPrivateKey(String Password, String Alias) throws Exception {

        Logger logger=Logger.getLogger("");
        logger.info(GlobalVariables.HDImageStorePath);
        JCPXMLDSigInit.init();
        HDImageStore.setDir(GlobalVariables.HDImageStorePath);
        KeyStore keystore = KeyStore.getInstance("HDImageStore");
        keystore.load(null, null);
        PrivateKey privateKey = (PrivateKey) keystore.getKey(Alias, Password.toCharArray());


        return privateKey;
    }


    /**
     * Get cetificate from storage by alias.
     *
     * @param Alias String with alias in storage.
     */
    public static X509Certificate GetCertificateFromStorage(String Alias)
            throws NoSuchProviderException, KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {

        JCPXMLDSigInit.init();
        HDImageStore.setDir(GlobalVariables.HDImageStorePath);
        KeyStore ks = KeyStore.getInstance("HDImageStore");
        ks.load(null, null);
        X509Certificate certificate = (X509Certificate) ks.getCertificate(Alias);

        return certificate;
    }

    /**
     * Show certificate in X509 in format base64 in string.
     *
     * @param certificate Certificate in X59
     * @return String in base64
     */
    protected static String certToBase64(X509Certificate certificate) throws CertificateEncodingException {
        StringWriter sw = new StringWriter();
        sw.write(DatatypeConverter.printBase64Binary(certificate.getEncoded()).replaceAll("(.{64})", "$1\n"));

        return sw.toString();
    }
}
package main.java.ru.rashgild.utils;

import org.apache.log4j.Logger;
import ru.CryptoPro.JCP.KeyStore.HDImage.HDImageStore;
import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class CertificateUtils {

    /**
     * Get certificate form CertStorage.
     *
     * @param alias    Alias of certificate
     * @param password password of cert storage
     * @param path     path to certificate
     */
    public static X509Certificate extractCertificateFromCertStore(String password,
                                                                  String alias,
                                                                  String path) throws Exception {
        KeyStore allCertStore;
        HDImageStore.setDir(GlobalVariables.HDImageStorePath);

        allCertStore = KeyStore.getInstance("CertStore");
        File f = new File(path);
        allCertStore.load(new FileInputStream(f), password.toCharArray());
        return (X509Certificate) allCertStore.getCertificate(alias);
    }

    /**
     * Get PrivateKey from storage by alias.
     *
     * @param alias    alias in storage
     * @param password Password for storage
     */
    public static PrivateKey getPrivateKey(String password, String alias) throws Exception {
        Logger logger = Logger.getLogger("");
        logger.info(GlobalVariables.HDImageStorePath);
        JCPXMLDSigInit.init();
        HDImageStore.setDir(GlobalVariables.HDImageStorePath);
        KeyStore keystore = KeyStore.getInstance("HDImageStore");
        keystore.load(null, null);
        return (PrivateKey) keystore.getKey(alias, password.toCharArray());
    }

    /**
     * Get certificate from storage by alias.
     *
     * @param alias alias in storage
     */
    public static X509Certificate getCertificateFromKeyStorage(String alias)
            throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {

        JCPXMLDSigInit.init();
        HDImageStore.setDir(GlobalVariables.HDImageStorePath);
        KeyStore ks = KeyStore.getInstance("HDImageStore");
        ks.load(null, null);
        return (X509Certificate) ks.getCertificate(alias);
    }

    /**
     * Convert X509Certificate to base64.
     *
     * @param certificate X509Certificate
     */
    public static String certToBase64(X509Certificate certificate) throws CertificateEncodingException {
        StringWriter sw = new StringWriter();
        sw.write(DatatypeConverter.printBase64Binary(certificate.getEncoded()).replaceAll("(.{64})", "$1\n"));

        return sw.toString();
    }
}
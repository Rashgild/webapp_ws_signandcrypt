package ru.rashgild.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.CryptoPro.JCP.KeyStore.HDImage.HDImageStore;
import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;
import ru.rashgild.utils.GlobalVariables;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Override
    public X509Certificate extractCertificateFromCertStore(String password,
                                                           String alias,
                                                           String path) throws Exception {
        KeyStore allCertStore;
        HDImageStore.setDir(GlobalVariables.HDImageStorePath);

        allCertStore = KeyStore.getInstance("CertStore");
        File f = new File(path);
        allCertStore.load(new FileInputStream(f), password.toCharArray());
        return (X509Certificate) allCertStore.getCertificate(alias);
    }

    @Value("${hd.image.store.path}")
    private String hdImageStorePath;

    @PostConstruct
    public void init() {
       // JCPXMLDSigInit.init();
        System.out.println("hdImageStorePath >>>>>>>>>>>>>>>>>>>>>>>" + hdImageStorePath);
        HDImageStore.setDir(hdImageStorePath);
    }

    @Override
    public PrivateKey getPrivateKey(String password, String alias) throws Exception {
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
    @Override
    public X509Certificate getCertificateFromKeyStorage(String alias) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        JCPXMLDSigInit.init();
        HDImageStore.setDir(GlobalVariables.HDImageStorePath);
        KeyStore ks = KeyStore.getInstance("HDImageStore");
        ks.load(null, null);
        return (X509Certificate) ks.getCertificate(alias);
    }

}

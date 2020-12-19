package ru.rashgild.service;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public interface CertificateService {
    X509Certificate extractCertificateFromCertStore(String password,
                                                    String alias,
                                                    String path) throws Exception;

    PrivateKey getPrivateKey(String password, String alias) throws Exception;

    X509Certificate getCertificateFromKeyStorage(String alias) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException;
}

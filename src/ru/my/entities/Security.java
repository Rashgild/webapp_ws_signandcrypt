package ru.my.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Security")
@XmlAccessorType(XmlAccessType.FIELD)
public class Security {

    @XmlElement(name = "binarySecurityToken", defaultValue = "123123123")
    private BinarySecurityToken binarySecurityToken;


    public BinarySecurityToken getBinarySecurityToken() {
        return binarySecurityToken;
    }

    public void setBinarySecurityToken(BinarySecurityToken binarySecurityToken) {
        this.binarySecurityToken = binarySecurityToken;
    }

    @XmlRootElement(name = "BinarySecurityToken")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BinarySecurityToken {

        @XmlAttribute(name = "wse")
        private String wse;
        @XmlAttribute(name = "EncodingType")
        private String encodingType;
        @XmlAttribute(name = "ValueType")
        private String valueType;

        @XmlAttribute(name = "Id", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        private String id;

        public String getWse() {
            return wse;
        }

        public void setWse(String wse) {
            this.wse = wse;
        }

        public String getEncodingType() {
            return encodingType;
        }

        public void setEncodingType(String encodingType) {
            this.encodingType = encodingType;
        }

        public String getValueType() {
            return valueType;
        }

        public void setValueType(String valueType) {
            this.valueType = valueType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    @XmlAttribute(name = "wsse", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
    private String wsse;
    @XmlAttribute(name = "actor")
    private String actor;
    @XmlElement(name = "Signature")
    private Signature signature;

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getWsse() {
        return wsse;
    }

    public void setWsse(String wsse) {
        this.wsse = wsse;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    @XmlRootElement(name = "Signature")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Signature {

        @XmlElement(name = "SignedInfo")
        private SignedInfo signedInfo;

        @XmlElement(name = "SignatureValue")
        private String signatureValue;

        @XmlElement(name = "KeyInfo")
        private KeyInfo keyInfo;

        public SignedInfo getSignedInfo() {
            return signedInfo;
        }

        public void setSignedInfo(SignedInfo signedInfo) {
            this.signedInfo = signedInfo;
        }

        public String getSignatureValue() {
            return signatureValue;
        }

        public void setSignatureValue(String signatureValue) {
            this.signatureValue = signatureValue;
        }

        public KeyInfo getKeyInfo() {
            return keyInfo;
        }

        public void setKeyInfo(KeyInfo keyInfo) {
            this.keyInfo = keyInfo;
        }

        @XmlRootElement(name = "KeyInfo")
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class KeyInfo {

            @XmlElement(name = "SecurityTokenReference")
            private SecurityTokenReference securityTokenReference;

            public SecurityTokenReference getSecurityTokenReference() {
                return securityTokenReference;
            }

            public void setSecurityTokenReference(SecurityTokenReference securityTokenReference) {
                this.securityTokenReference = securityTokenReference;
            }

            public KeyInfo(String ogrn, String eln) {
                securityTokenReference = new SecurityTokenReference(ogrn, eln);
            }

            public KeyInfo() {
            }

            @XmlRootElement(name = "SecurityTokenReference", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class SecurityTokenReference {

                private Reference reference;

                public Reference getReference() {
                    return reference;
                }

                public void setReference(Reference reference) {
                    this.reference = reference;
                }

                public SecurityTokenReference(String ogrn, String eln) {
                    reference = new Reference(ogrn, eln);
                }

                public SecurityTokenReference() {
                }

                @XmlRootElement(name = "Reference", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "Reference", propOrder = {
                        "URI", "ValueType"
                })
                public static class Reference {
                    @XmlAttribute(name = "URI")
                    private String URI;
                    @XmlAttribute(name = "ValueType")
                    private String ValueType;

                    public String getURI() {
                        return URI;
                    }

                    public void setURI(String URI) {
                        this.URI = URI;
                    }

                    public String getValueType() {
                        return ValueType;
                    }

                    public void setValueType(String valueType) {
                        ValueType = valueType;
                    }

                    public Reference() {
                        ValueType = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3";
                    }

                    public Reference(String ogrn, String eln) {
                        URI = "#http://eln.fss.ru/actor/mo/" + ogrn + "/ELN_" + eln;
                        ValueType = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3";
                    }
                }
            }
        }

        @XmlRootElement(name = "SignedInfo")
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class SignedInfo {

            @XmlElement(name = "CanonicalizationMethod")
            private CanonicalizationMethod canonicalizationMethods;
            @XmlElement(name = "SignatureMethod")
            private SignatureMethod signatureMethod;
            @XmlElement(name = "Reference")
            private Reference reference;

            public CanonicalizationMethod getCanonicalizationMethods() {
                return canonicalizationMethods;
            }

            public void setCanonicalizationMethods(CanonicalizationMethod canonicalizationMethods) {
                this.canonicalizationMethods = canonicalizationMethods;
            }

            public SignatureMethod getSignatureMethod() {
                return signatureMethod;
            }

            public void setSignatureMethod(SignatureMethod signatureMethod) {
                this.signatureMethod = signatureMethod;
            }

            public Reference getReference() {
                return reference;
            }

            public void setReference(Reference reference) {
                this.reference = reference;
            }

            public SignedInfo() {
                signatureMethod = new SignatureMethod();
                canonicalizationMethods = new CanonicalizationMethod();
            }

            @XmlRootElement(name = "CanonicalizationMethod")
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class CanonicalizationMethod {
                @XmlAttribute(name = "Algorithm")
                private String Algorithm;

                public String getAlgorithm() {
                    return Algorithm;
                }

                public void setAlgorithm(String algorithm) {
                    Algorithm = algorithm;
                }

                public CanonicalizationMethod() {
                    Algorithm = "http://www.w3.org/2001/10/xml-exc-c14n#";
                }
            }

            @XmlRootElement(name = "Reference")
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class Reference {
                @XmlAttribute(name = "URI")
                private String URI;

                @XmlElement(name = "DigestMethod")
                private DigestMethod digestMethod;
                @XmlElement(name = "DigestValue")
                private String digestValue;
                @XmlElement(name = "Transforms")
                private Transforms transforms;

                public String getURI() {
                    return URI;
                }

                public void setURI(String URI) {
                    this.URI = URI;
                }

                public DigestMethod getDigestMethod() {
                    return digestMethod;
                }

                public void setDigestMethod(DigestMethod digestMethod) {
                    this.digestMethod = digestMethod;
                }

                public String getDigestValue() {
                    return digestValue;
                }

                public void setDigestValue(String digestValue) {
                    this.digestValue = digestValue;
                }

                public Transforms getTransforms() {
                    return transforms;
                }

                public void setTransforms(Transforms transforms) {
                    this.transforms = transforms;
                }

                public Reference() {
                    digestMethod = new DigestMethod();
                    transforms = new Transforms();
                }

                @XmlRootElement(name = "DigestMethod")
                @XmlAccessorType(XmlAccessType.FIELD)
                public static class DigestMethod {
                    @XmlAttribute(name = "Algorithm")
                    private String Algorithm;

                    public DigestMethod() {
                        Algorithm = "http://www.w3.org/2001/04/xmldsig-more#gostr3411";
                    }

                    public String getAlgorithm() {
                        return Algorithm;
                    }

                    public void setAlgorithm(String algorithm) {
                        Algorithm = algorithm;
                    }
                }

                @XmlRootElement(name = "Transforms")
                @XmlAccessorType(XmlAccessType.FIELD)
                public static class Transforms {
                    private Transform transform;

                    public Transform getTransform() {
                        return transform;
                    }

                    public void setTransform(Transform transform) {
                        this.transform = transform;
                    }

                    public Transforms() {
                        transform = new Transform();
                    }

                    @XmlRootElement(name = "Transform")
                    @XmlAccessorType(XmlAccessType.FIELD)
                    public static class Transform {
                        @XmlAttribute(name = "Algorithm")
                        private String Algorithm;

                        public Transform() {
                            Algorithm = "http://www.w3.org/2001/10/xml-exc-c14n#";
                        }

                        public String getAlgorithm() {
                            return Algorithm;
                        }

                        public void setAlgorithm(String algorithm) {
                            Algorithm = algorithm;
                        }
                    }
                }
            }

            @XmlRootElement(name = "SignatureMethod")
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class SignatureMethod {
                @XmlAttribute(name = "Algorithm")
                private String Algorithm;

                public String getAlgorithm() {
                    return Algorithm;
                }

                public void setAlgorithm(String algorithm) {
                    Algorithm = algorithm;
                }

                public SignatureMethod() {
                    Algorithm = "http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411";
                }
            }
        }
    }
}

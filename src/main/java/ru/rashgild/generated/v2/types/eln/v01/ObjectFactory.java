package ru.rashgild.generated.v2.types.eln.v01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ru.rashgild.generated.v2.types.commom.v01.Period;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.v2.fss.integration.types.eln.v01 package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Info_QNAME = new QName("http://www.fss.ru/integration/types/eln/v01", "Info");
    private final static QName _Hospital_QNAME = new QName("http://www.fss.ru/integration/types/eln/v01", "hospital");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.v2.fss.integration.types.eln.v01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TreatFullPeriodMo }
     * 
     */
    public TreatFullPeriodMo createTreatFullPeriodMo() {
        return new TreatFullPeriodMo();
    }

    /**
     * Create an instance of {@link Info }
     * 
     */
    public Info createInfo() {
        return new Info();
    }

    /**
     * Create an instance of {@link Info.InfoRowset }
     * 
     */
    public Info.InfoRowset createInfoInfoRowset() {
        return new Info.InfoRowset();
    }

    /**
     * Create an instance of {@link Info.InfoRowset.InfoRow }
     * 
     */
    public Info.InfoRowset.InfoRow createInfoInfoRowsetInfoRow() {
        return new Info.InfoRowset.InfoRow();
    }

    /**
     * Create an instance of {@link Info.InfoRowset.InfoRow.Errors }
     * 
     */
    public Info.InfoRowset.InfoRow.Errors createInfoInfoRowsetInfoRowErrors() {
        return new Info.InfoRowset.InfoRow.Errors();
    }

    /**
     * Create an instance of {@link WSResult }
     * 
     */
    public WSResult createWSResult() {
        return new WSResult();
    }

    /**
     * Create an instance of {@link LnCodeList }
     * 
     */
    public LnCodeList createLnCodeList() {
        return new LnCodeList();
    }

    /**
     * Create an instance of {@link HospitalBreach }
     * 
     */
    public HospitalBreach createHospitalBreach() {
        return new HospitalBreach();
    }

    /**
     * Create an instance of {@link HospitalBreachInfo }
     * 
     */
    public HospitalBreachInfo createHospitalBreachInfo() {
        return new HospitalBreachInfo();
    }

    /**
     * Create an instance of {@link ServFullData }
     * 
     */
    public ServFullData createServFullData() {
        return new ServFullData();
    }

    /**
     * Create an instance of {@link TreatFullPeriod }
     * 
     */
    public TreatFullPeriod createTreatFullPeriod() {
        return new TreatFullPeriod();
    }

    /**
     * Create an instance of {@link Serv }
     * 
     */
    public Serv createServ() {
        return new Serv();
    }

    /**
     * Create an instance of {@link LnResult }
     * 
     */
    public LnResult createLnResult() {
        return new LnResult();
    }

    /**
     * Create an instance of {@link TreatPeriod }
     * 
     */
    public TreatPeriod createTreatPeriod() {
        return new TreatPeriod();
    }

    /**
     * Create an instance of {@link TreatFullPeriodMo.TreatPeriod }
     * 
     */
    public TreatFullPeriodMo.TreatPeriod createTreatFullPeriodMoTreatPeriod() {
        return new TreatFullPeriodMo.TreatPeriod();
    }

    /**
     * Create an instance of {@link Info.InfoRowset.InfoRow.Errors.Error }
     * 
     */
    public Info.InfoRowset.InfoRow.Errors.Error createInfoInfoRowsetInfoRowErrorsError() {
        return new Info.InfoRowset.InfoRow.Errors.Error();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Info }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/v01", name = "Info")
    public JAXBElement<Info> createInfo(Info value) {
        return new JAXBElement<Info>(_Info_QNAME, Info.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/v01", name = "hospital")
    public JAXBElement<Period> createHospital(Period value) {
        return new JAXBElement<Period>(_Hospital_QNAME, Period.class, null, value);
    }

}

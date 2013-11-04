
package com.rentit.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rentit.soap.client package. 
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

    private final static QName _PoStatusUpdateResponse_QNAME = new QName("http://web.soap.ut.ee/", "poStatusUpdateResponse");
    private final static QName _PoStatusUpdateRequest_QNAME = new QName("http://web.soap.ut.ee/", "poStatusUpdateRequest");
    private final static QName _SetPoStatusResponse_QNAME = new QName("http://web.soap.ut.ee/", "setPoStatusResponse");
    private final static QName _SetPoStatus_QNAME = new QName("http://web.soap.ut.ee/", "setPoStatus");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rentit.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SetPoStatusResponse }
     * 
     */
    public SetPoStatusResponse createSetPoStatusResponse() {
        return new SetPoStatusResponse();
    }

    /**
     * Create an instance of {@link PoStatusUpdateRequest }
     * 
     */
    public PoStatusUpdateRequest createPoStatusUpdateRequest() {
        return new PoStatusUpdateRequest();
    }

    /**
     * Create an instance of {@link PoStatusUpdateResponse }
     * 
     */
    public PoStatusUpdateResponse createPoStatusUpdateResponse() {
        return new PoStatusUpdateResponse();
    }

    /**
     * Create an instance of {@link SetPoStatus }
     * 
     */
    public SetPoStatus createSetPoStatus() {
        return new SetPoStatus();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PoStatusUpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.soap.ut.ee/", name = "poStatusUpdateResponse")
    public JAXBElement<PoStatusUpdateResponse> createPoStatusUpdateResponse(PoStatusUpdateResponse value) {
        return new JAXBElement<PoStatusUpdateResponse>(_PoStatusUpdateResponse_QNAME, PoStatusUpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PoStatusUpdateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.soap.ut.ee/", name = "poStatusUpdateRequest")
    public JAXBElement<PoStatusUpdateRequest> createPoStatusUpdateRequest(PoStatusUpdateRequest value) {
        return new JAXBElement<PoStatusUpdateRequest>(_PoStatusUpdateRequest_QNAME, PoStatusUpdateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetPoStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.soap.ut.ee/", name = "setPoStatusResponse")
    public JAXBElement<SetPoStatusResponse> createSetPoStatusResponse(SetPoStatusResponse value) {
        return new JAXBElement<SetPoStatusResponse>(_SetPoStatusResponse_QNAME, SetPoStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetPoStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.soap.ut.ee/", name = "setPoStatus")
    public JAXBElement<SetPoStatus> createSetPoStatus(SetPoStatus value) {
        return new JAXBElement<SetPoStatus>(_SetPoStatus_QNAME, SetPoStatus.class, null, value);
    }

}

//
// Generated By:JAX-WS RI IBM 2.2.1-11/25/2013 11:48 AM(foreman)- (JAXB RI IBM 2.2.3-11/25/2013 12:35 PM(foreman)-)
//


package com.ojk.poc4.submit;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ojk.poc4.submit package. 
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

    private final static QName _GetResultResponse_QNAME = new QName("http://ojk.com/poc4/submit", "getResultResponse");
    private final static QName _SOAPFaultThrower_QNAME = new QName("http://ojk.com/poc4/submit", "SOAPFaultThrower");
    private final static QName _Submit_QNAME = new QName("http://ojk.com/poc4/submit", "submit");
    private final static QName _Poc4_QNAME = new QName("http://ojk.com/poc4/submit", "poc4");
    private final static QName _SubmitAsyncResponse_QNAME = new QName("http://ojk.com/poc4/submit", "submitAsyncResponse");
    private final static QName _SubmitAsync_QNAME = new QName("http://ojk.com/poc4/submit", "submitAsync");
    private final static QName _Echo_QNAME = new QName("http://ojk.com/poc4/submit", "echo");
    private final static QName _EchoResponse_QNAME = new QName("http://ojk.com/poc4/submit", "echoResponse");
    private final static QName _GetResult_QNAME = new QName("http://ojk.com/poc4/submit", "getResult");
    private final static QName _SubmitResponse_QNAME = new QName("http://ojk.com/poc4/submit", "submitResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ojk.poc4.submit
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubmitAsync }
     * 
     */
    public SubmitAsync createSubmitAsync() {
        return new SubmitAsync();
    }

    /**
     * Create an instance of {@link SubmitAsyncResponse }
     * 
     */
    public SubmitAsyncResponse createSubmitAsyncResponse() {
        return new SubmitAsyncResponse();
    }

    /**
     * Create an instance of {@link Poc4 }
     * 
     */
    public Poc4 createPoc4() {
        return new Poc4();
    }

    /**
     * Create an instance of {@link Submit }
     * 
     */
    public Submit createSubmit() {
        return new Submit();
    }

    /**
     * Create an instance of {@link FaultInfoDetail }
     * 
     */
    public FaultInfoDetail createFaultInfoDetail() {
        return new FaultInfoDetail();
    }

    /**
     * Create an instance of {@link GetResultResponse }
     * 
     */
    public GetResultResponse createGetResultResponse() {
        return new GetResultResponse();
    }

    /**
     * Create an instance of {@link SubmitResponse }
     * 
     */
    public SubmitResponse createSubmitResponse() {
        return new SubmitResponse();
    }

    /**
     * Create an instance of {@link GetResult }
     * 
     */
    public GetResult createGetResult() {
        return new GetResult();
    }

    /**
     * Create an instance of {@link EchoResponse }
     * 
     */
    public EchoResponse createEchoResponse() {
        return new EchoResponse();
    }

    /**
     * Create an instance of {@link Echo }
     * 
     */
    public Echo createEcho() {
        return new Echo();
    }

    /**
     * Create an instance of {@link Poc4Result }
     * 
     */
    public Poc4Result createPoc4Result() {
        return new Poc4Result();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResultResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "getResultResponse")
    public JAXBElement<GetResultResponse> createGetResultResponse(GetResultResponse value) {
        return new JAXBElement<GetResultResponse>(_GetResultResponse_QNAME, GetResultResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultInfoDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "SOAPFaultThrower")
    public JAXBElement<FaultInfoDetail> createSOAPFaultThrower(FaultInfoDetail value) {
        return new JAXBElement<FaultInfoDetail>(_SOAPFaultThrower_QNAME, FaultInfoDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Submit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "submit")
    public JAXBElement<Submit> createSubmit(Submit value) {
        return new JAXBElement<Submit>(_Submit_QNAME, Submit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Poc4 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "poc4")
    public JAXBElement<Poc4> createPoc4(Poc4 value) {
        return new JAXBElement<Poc4>(_Poc4_QNAME, Poc4 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitAsyncResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "submitAsyncResponse")
    public JAXBElement<SubmitAsyncResponse> createSubmitAsyncResponse(SubmitAsyncResponse value) {
        return new JAXBElement<SubmitAsyncResponse>(_SubmitAsyncResponse_QNAME, SubmitAsyncResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitAsync }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "submitAsync")
    public JAXBElement<SubmitAsync> createSubmitAsync(SubmitAsync value) {
        return new JAXBElement<SubmitAsync>(_SubmitAsync_QNAME, SubmitAsync.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Echo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "echo")
    public JAXBElement<Echo> createEcho(Echo value) {
        return new JAXBElement<Echo>(_Echo_QNAME, Echo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EchoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "echoResponse")
    public JAXBElement<EchoResponse> createEchoResponse(EchoResponse value) {
        return new JAXBElement<EchoResponse>(_EchoResponse_QNAME, EchoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "getResult")
    public JAXBElement<GetResult> createGetResult(GetResult value) {
        return new JAXBElement<GetResult>(_GetResult_QNAME, GetResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ojk.com/poc4/submit", name = "submitResponse")
    public JAXBElement<SubmitResponse> createSubmitResponse(SubmitResponse value) {
        return new JAXBElement<SubmitResponse>(_SubmitResponse_QNAME, SubmitResponse.class, null, value);
    }

}

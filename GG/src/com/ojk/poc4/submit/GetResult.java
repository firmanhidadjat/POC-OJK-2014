//
// Generated By:JAX-WS RI IBM 2.2.1-11/25/2013 11:48 AM(foreman)- (JAXB RI IBM 2.2.3-11/25/2013 12:35 PM(foreman)-)
//


package com.ojk.poc4.submit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="POC4" type="{http://ojk.com/poc4/submit}poc4" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getResult", propOrder = {
    "poc4"
})
public class GetResult {

    @XmlElement(name = "POC4")
    protected Poc4 poc4;

    /**
     * Gets the value of the poc4 property.
     * 
     * @return
     *     possible object is
     *     {@link Poc4 }
     *     
     */
    public Poc4 getPOC4() {
        return poc4;
    }

    /**
     * Sets the value of the poc4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Poc4 }
     *     
     */
    public void setPOC4(Poc4 value) {
        this.poc4 = value;
    }

}

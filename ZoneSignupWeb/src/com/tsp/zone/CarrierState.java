
package com.tsp.zone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for carrierState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="carrierState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carrier_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="carrier_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="good_for_fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carrierState", propOrder = {
    "carrierId",
    "carrierName",
    "goodForFax"
})
public class CarrierState {

    @XmlElement(name = "carrier_id")
    protected String carrierId;
    @XmlElement(name = "carrier_name")
    protected String carrierName;
    @XmlElement(name = "good_for_fax")
    protected String goodForFax;

    /**
     * Gets the value of the carrierId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierId() {
        return carrierId;
    }

    /**
     * Sets the value of the carrierId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierId(String value) {
        this.carrierId = value;
    }

    /**
     * Gets the value of the carrierName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierName() {
        return carrierName;
    }

    /**
     * Sets the value of the carrierName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierName(String value) {
        this.carrierName = value;
    }

    /**
     * Gets the value of the goodForFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodForFax() {
        return goodForFax;
    }

    /**
     * Sets the value of the goodForFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodForFax(String value) {
        this.goodForFax = value;
    }

}

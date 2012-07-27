
package com.tsp.zone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accountState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accountState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="account_num" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="plan_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountState", propOrder = {
    "accountNum",
    "planCode"
})
public class AccountState {

    @XmlElement(name = "account_num")
    protected int accountNum;
    @XmlElement(name = "plan_code")
    protected String planCode;

    /**
     * Gets the value of the accountNum property.
     * 
     */
    public int getAccountNum() {
        return accountNum;
    }

    /**
     * Sets the value of the accountNum property.
     * 
     */
    public void setAccountNum(int value) {
        this.accountNum = value;
    }

    /**
     * Gets the value of the planCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanCode() {
        return planCode;
    }

    /**
     * Sets the value of the planCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanCode(String value) {
        this.planCode = value;
    }

}

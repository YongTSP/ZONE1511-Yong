
package com.tsp.zone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for billingState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="billingState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payment_mode_ind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="credit_card_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="credit_card_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="credit_card_member_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cvv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="date_of_expiration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bank_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bank_account_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="account_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "billingState", propOrder = {
    "paymentModeInd",
    "creditCardType",
    "creditCardNum",
    "creditCardMemberName",
    "cvv",
    "dateOfExpiration",
    "bankName",
    "bankAccountNum",
    "accountName"
})
public class BillingState {

    @XmlElement(name = "payment_mode_ind")
    protected String paymentModeInd;
    @XmlElement(name = "credit_card_type")
    protected String creditCardType;
    @XmlElement(name = "credit_card_num")
    protected String creditCardNum;
    @XmlElement(name = "credit_card_member_name")
    protected String creditCardMemberName;
    protected String cvv;
    @XmlElement(name = "date_of_expiration")
    protected String dateOfExpiration;
    @XmlElement(name = "bank_name")
    protected String bankName;
    @XmlElement(name = "bank_account_num")
    protected String bankAccountNum;
    @XmlElement(name = "account_name")
    protected String accountName;

    /**
     * Gets the value of the paymentModeInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentModeInd() {
        return paymentModeInd;
    }

    /**
     * Sets the value of the paymentModeInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentModeInd(String value) {
        this.paymentModeInd = value;
    }

    /**
     * Gets the value of the creditCardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardType() {
        return creditCardType;
    }

    /**
     * Sets the value of the creditCardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardType(String value) {
        this.creditCardType = value;
    }

    /**
     * Gets the value of the creditCardNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardNum() {
        return creditCardNum;
    }

    /**
     * Sets the value of the creditCardNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardNum(String value) {
        this.creditCardNum = value;
    }

    /**
     * Gets the value of the creditCardMemberName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardMemberName() {
        return creditCardMemberName;
    }

    /**
     * Sets the value of the creditCardMemberName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardMemberName(String value) {
        this.creditCardMemberName = value;
    }

    /**
     * Gets the value of the cvv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Sets the value of the cvv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvv(String value) {
        this.cvv = value;
    }

    /**
     * Gets the value of the dateOfExpiration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfExpiration() {
        return dateOfExpiration;
    }

    /**
     * Sets the value of the dateOfExpiration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfExpiration(String value) {
        this.dateOfExpiration = value;
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
    }

    /**
     * Gets the value of the bankAccountNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccountNum() {
        return bankAccountNum;
    }

    /**
     * Sets the value of the bankAccountNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccountNum(String value) {
        this.bankAccountNum = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

}

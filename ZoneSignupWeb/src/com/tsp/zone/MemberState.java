
package com.tsp.zone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for memberState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="memberState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="member_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="member_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="first_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sur_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="date_of_birth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passport_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postal_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailing_address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email_address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email_correspondence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contact_phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "memberState", propOrder = {
    "memberNum",
    "memberType",
    "salute",
    "firstName",
    "surName",
    "gender",
    "dateOfBirth",
    "passportNum",
    "postalCode",
    "mailingAddress",
    "emailAddress",
    "emailCorrespondence",
    "contactPhone",
    "fax"
})
public class MemberState {

    @XmlElement(name = "member_num")
    protected String memberNum;
    @XmlElement(name = "member_type")
    protected String memberType;
    protected String salute;
    @XmlElement(name = "first_name")
    protected String firstName;
    @XmlElement(name = "sur_name")
    protected String surName;
    protected String gender;
    @XmlElement(name = "date_of_birth")
    protected String dateOfBirth;
    @XmlElement(name = "passport_num")
    protected String passportNum;
    @XmlElement(name = "postal_code")
    protected String postalCode;
    @XmlElement(name = "mailing_address")
    protected String mailingAddress;
    @XmlElement(name = "email_address")
    protected String emailAddress;
    @XmlElement(name = "email_correspondence")
    protected String emailCorrespondence;
    @XmlElement(name = "contact_phone")
    protected String contactPhone;
    protected String fax;

    /**
     * Gets the value of the memberNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberNum() {
        return memberNum;
    }

    /**
     * Sets the value of the memberNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberNum(String value) {
        this.memberNum = value;
    }

    /**
     * Gets the value of the memberType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberType() {
        return memberType;
    }

    /**
     * Sets the value of the memberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberType(String value) {
        this.memberType = value;
    }

    /**
     * Gets the value of the salute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalute() {
        return salute;
    }

    /**
     * Sets the value of the salute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalute(String value) {
        this.salute = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the surName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the value of the surName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurName(String value) {
        this.surName = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the passportNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassportNum() {
        return passportNum;
    }

    /**
     * Sets the value of the passportNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassportNum(String value) {
        this.passportNum = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the mailingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingAddress() {
        return mailingAddress;
    }

    /**
     * Sets the value of the mailingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingAddress(String value) {
        this.mailingAddress = value;
    }

    /**
     * Gets the value of the emailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    /**
     * Gets the value of the emailCorrespondence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailCorrespondence() {
        return emailCorrespondence;
    }

    /**
     * Sets the value of the emailCorrespondence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailCorrespondence(String value) {
        this.emailCorrespondence = value;
    }

    /**
     * Gets the value of the contactPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets the value of the contactPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPhone(String value) {
        this.contactPhone = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

}

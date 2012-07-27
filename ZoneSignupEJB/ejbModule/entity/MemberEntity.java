package entity;

import javax.persistence.*;
import java.util.*;


@Entity(name="member_profile")
public class MemberEntity {
	
	//Primary Key
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(precision=8, scale=0)
	private int member_num;
	
	//Attributes
	private char member_type;
	private String salute;
	private String first_name;
	private String sur_name;
	private char gender;
	
	@Temporal(TemporalType.DATE)
	private Date date_of_birth;
	
	private String passport_num;
	private String postal_code;
	private String mailing_address;
	private String email_address;
	private String email_correspondence;
	private String contact_phone;
	private String fax;
	private String password;
	private String password_reminder_question;
	private String password_reminder_answer;
	
	//Relationship fields
	@OneToMany(mappedBy="member")
	private Collection<BillingEntity> billings;
	
	@OneToMany(mappedBy="member")
	private Collection<AccountEntity> accounts;
	
	
	//Getter, Setters
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public char getMember_type() {
		return member_type;
	}
	public void setMember_type(char member_type) {
		this.member_type = member_type;
	}
	public String getSalute() {
		return salute;
	}
	public void setSalute(String salute) {
		this.salute = salute;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getSur_name() {
		return sur_name;
	}
	public void setSur_name(String sur_name) {
		this.sur_name = sur_name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getPassport_num() {
		return passport_num;
	}
	public void setPassport_num(String passport_num) {
		this.passport_num = passport_num;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getMailing_address() {
		return mailing_address;
	}
	public void setMailing_address(String mailing_address) {
		this.mailing_address = mailing_address;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getEmail_correspondence() {
		return email_correspondence;
	}
	public void setEmail_correspondence(String email_correspondence) {
		this.email_correspondence = email_correspondence;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword_reminder_question() {
		return password_reminder_question;
	}
	public void setPassword_reminder_question(String password_reminder_question) {
		this.password_reminder_question = password_reminder_question;
	}
	public String getPassword_reminder_answer() {
		return password_reminder_answer;
	}
	public Collection<BillingEntity> getBillings() {
		return billings;
	}
	public void setBilling(Collection<BillingEntity> billings) {
		this.billings = billings;
	}
	public void setPassword_reminder_answer(String password_reminder_answer) {
		this.password_reminder_answer = password_reminder_answer;
	}
	public Collection<AccountEntity> getAccounts() {
		return accounts;
	}
	public void setAccounts(Collection<AccountEntity> accounts) {
		this.accounts = accounts;
	}
	public void setBillings(Collection<BillingEntity> billings) {
		this.billings = billings;
	}
	
	
	

}

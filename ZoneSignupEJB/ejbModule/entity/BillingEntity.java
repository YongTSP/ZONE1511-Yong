package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity(name="billing_profile")
public class BillingEntity {
	
	//PK class
	@Embeddable
	public static class BillingPK implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private int billing_seq_num;
		private int member_num;
		
		public BillingPK(){};
		
		public BillingPK(int billing_seq_num, int member_num){
			this.billing_seq_num=billing_seq_num;
			this.member_num=member_num;
		}

		public int getBilling_seq_num() {
			return billing_seq_num;
		}

		public void setBilling_seq_num(int billing_seq_num) {
			this.billing_seq_num = billing_seq_num;
		}

		public int getMember_num() {
			return member_num;
		}

		public void setMember_num(int member_num) {
			this.member_num = member_num;
		}
	}
	
	//Primary key
	@EmbeddedId
	private BillingPK billingPK;
	
	//Relationship field
	@ManyToOne
	@MapsId("member_num")
	private MemberEntity member;
	
	//Credit card or GIRO or Cheque/Cash (C/G/Q)
	private char payment_mode_ind;
	
	//For credit card only
	private String credit_card_type;
	private String credit_card_num;
	private String credit_card_member_name;
	private String cvv;
	@Temporal(TemporalType.DATE)
	private Date date_of_expiration;
	
	
	//For GIRO only
	private String bank_name;
	private String bank_account_num;
	private String account_name;
	
	//Constructors
	
	public BillingEntity(){};

	public BillingEntity(MemberEntity member, int billing_seq_num, char payment_mode_ind){
		this.member=member;
		this.billingPK=new BillingPK(billing_seq_num, member.getMember_num());
		this.payment_mode_ind=payment_mode_ind;
	}

	//Getter and Setter
	public BillingPK getBillingPk() {
		return billingPK;
	}

	public void setPk(BillingPK billingPK) {
		this.billingPK = billingPK;
	}

	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}

	public char getPayment_mode_ind() {
		return payment_mode_ind;
	}

	public void setPayment_mode_ind(char payment_mode_ind) {
		this.payment_mode_ind = payment_mode_ind;
	}

	public String getCredit_card_type() {
		return credit_card_type;
	}

	public void setCredit_card_type(String credit_card_type) {
		this.credit_card_type = credit_card_type;
	}

	public String getCredit_card_num() {
		return credit_card_num;
	}

	public void setCredit_card_num(String credit_card_num) {
		this.credit_card_num = credit_card_num;
	}

	public String getCredit_card_member_name() {
		return credit_card_member_name;
	}

	public void setCredit_card_member_name(String credit_card_member_name) {
		this.credit_card_member_name = credit_card_member_name;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getDate_of_expiration() {
		return date_of_expiration;
	}

	public void setDate_of_expiration(Date date_of_expiration) {
		this.date_of_expiration = date_of_expiration;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_account_num() {
		return bank_account_num;
	}

	public void setBank_account_num(String bank_account_num) {
		this.bank_account_num = bank_account_num;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	

}

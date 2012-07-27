package state;

import java.io.Serializable;


public class BillingState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String payment_mode_ind;
	
	//For credit card only
	public String credit_card_type;
	public String credit_card_num;
	public String credit_card_member_name;
	public String cvv;
	public String date_of_expiration;
	
	
	//For GIRO only
	public String bank_name;
	public String bank_account_num;
	public String account_name;

}

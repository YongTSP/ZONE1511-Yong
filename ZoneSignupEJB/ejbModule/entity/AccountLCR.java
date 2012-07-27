package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="account_lcr")
public class AccountLCR {
	
	//PK class
	@Embeddable
	public static class AccountLCRKey implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int account_num;
		private String carrier_id;
		
		public AccountLCRKey(){}
		
		public AccountLCRKey(int account_num, String carrier_id){
			this.account_num = account_num;
			this.carrier_id = carrier_id;
		}

		public int getAccount_num() {
			return account_num;
		}

		public void setAccount_num(int account_num) {
			this.account_num = account_num;
		}

		public String getCarrier_id() {
			return carrier_id;
		}

		public void setCarrier_id(String carrier_id) {
			this.carrier_id = carrier_id;
		}
				
	}
	
	//PK
	@EmbeddedId
	private AccountLCRKey accountLCRKey;
	
	//Relationship fields
	@ManyToOne
	@MapsId("account_num")
	private AccountEntity account;
	
	@ManyToOne
	@MapsId("carrier_id")
	private CarrierEntity carrier;
	
	private int carrier_seq;
	
	public AccountLCR(){}
	
	public AccountLCR(AccountEntity account, CarrierEntity carrier, int carrier_seq){
		this.account = account;
		this.carrier = carrier;
		this.carrier_seq = carrier_seq;
		this.accountLCRKey = new AccountLCRKey(account.getAccount_num(), carrier.getCarrier_id());
	}

	public AccountLCRKey getAccountLCRKey() {
		return accountLCRKey;
	}

	public void setAccountLCRKey(AccountLCRKey accountLCRKey) {
		this.accountLCRKey = accountLCRKey;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public CarrierEntity getCarrier() {
		return carrier;
	}

	public void setCarrier(CarrierEntity carrier) {
		this.carrier = carrier;
	}

	public int getCarrier_seq() {
		return carrier_seq;
	}

	public void setCarrier_seq(int carrier_seq) {
		this.carrier_seq = carrier_seq;
	}
	

}

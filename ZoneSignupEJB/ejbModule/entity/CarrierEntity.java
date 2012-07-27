package entity;

import javax.persistence.*;
import java.util.*;

@Entity(name="carrier_names")
public class CarrierEntity {
	
	@Id
	private String carrier_id;
	private String carrier_name;
	private char good_for_fax;
	
	@OneToMany(mappedBy="carrier")
	private Collection<AccountLCR> accountLCRs;
	
	public CarrierEntity(){}
	
	public CarrierEntity(String carrier_id, String carrier_name, char good_for_fax){
		this.carrier_id = carrier_id;
		this.carrier_name = carrier_name;
		this.good_for_fax = good_for_fax;
	}

	public String getCarrier_id() {
		return carrier_id;
	}

	public void setCarrier_id(String carrier_id) {
		this.carrier_id = carrier_id;
	}

	public String getCarrier_name() {
		return carrier_name;
	}

	public void setCarrier_name(String carrier_name) {
		this.carrier_name = carrier_name;
	}

	public char getGood_for_fax() {
		return good_for_fax;
	}

	public void setGood_for_fax(char good_for_fax) {
		this.good_for_fax = good_for_fax;
	}

	public Collection<AccountLCR> getAccountLCRs() {
		return accountLCRs;
	}

	public void setAccountLCRs(Collection<AccountLCR> accountLCRs) {
		this.accountLCRs = accountLCRs;
	}
	
	
}

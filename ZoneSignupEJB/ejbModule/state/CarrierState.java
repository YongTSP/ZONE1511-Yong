package state;

import java.io.Serializable;

public class CarrierState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String carrier_id;
	public String carrier_name;
	public String good_for_fax;
	
	public CarrierState(String carrier_id, String carrier_name, String good_for_fax){
		this.carrier_id = carrier_id;
		this.carrier_name = carrier_name;
		this.good_for_fax = good_for_fax;
	}
}

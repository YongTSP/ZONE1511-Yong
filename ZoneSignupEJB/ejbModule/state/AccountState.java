package state;

import java.io.Serializable;

public class AccountState implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int account_num;
	public String plan_code;
	
	public AccountState(int account_num, String plan_code){
		this.account_num = account_num;
		this.plan_code = plan_code;
	}
}

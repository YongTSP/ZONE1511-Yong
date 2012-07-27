package ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import state.*;
@Remote
@WebService(name="ZoneManagerPortType", targetNamespace="http://zone.tsp.com")
public interface ZoneManagerRemote {
	
	public int doAddMember(List<String> aNames, List<Object> aValues);
	public boolean doCheckMember(String attribute, String value);
	public void doAddBilling(int member_num, List<String> aNames, List<Object> aValues);
	public boolean doCheckBilling(String attribute, String value);
	public void doAddClient(int member_num, List<String> clis, List<String> fax_clis, String plan_code);
	public List<String> doListMember();
	public MemberState doGetMemberProfile(int member_num);
	public BillingState doGetMemberBilling(int member_num);
	public List<String> doGetMemberAccount(int member_num);
	public int doLogin(String phone_number, String password);
	public void doAddCarrier(String carrier_id, String carrier_name, String good_for_fax);
	public List<AccountState> doListAccount(String member_num);
	public List<CarrierState> doListCarrier(String account_num);
	public boolean doAddAccountLCR(String account_num, String carrier_id, String carrier_seq_num);
	public void doDeleteAccountLCR(String account_num);
	public boolean doUpdateBilling(String member_num, BillingState billingState);
	public boolean doUpdatePassword(String member_num, String old_password, String new_password);
	public boolean doUpdateMember(MemberState memberState);
}

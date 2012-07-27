package ejb;

import javax.ejb.Stateless;
import entity.*;
import state.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.jws.WebService;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.Query;

@Stateless
@WebService(serviceName="ZoneManager",
			portName="ZoneManagerPort",
			endpointInterface="ejb.ZoneManagerRemote",
			targetNamespace="http://zone.tsp.com")
public class ZoneManagerBean implements ZoneManagerRemote {
	//Hibernate session factory
	private SessionFactory sessionFactory;
	
	//Instantiate the factory, this is when Hibernate reads cfg.xml and do the ORM
	protected void setUp() {
		try{
		AnnotationConfiguration configuration = new AnnotationConfiguration();
	    configuration.configure();        
	    sessionFactory = configuration.buildSessionFactory();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//close factory
	protected void tearDown() {
		try{
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

    public ZoneManagerBean() {
    	setUp();
    }
    
    /*	2 Lists, one for attribute names, one for values, the lists must be maintained such that name and 
     * corresponding value must be at the same index
     * 	This is the best way I can think of, as List seems to be the most complicated type supported by
     * 	JAX-WS (limitation of SOAP-XML?), tried Hashtable but impossible to transfer.
     */
    public int doAddMember(List<String> aNames, List<Object> aValues){
    	
    	try{
    	if(sessionFactory==null)
    		setUp();
    	MemberEntity member = new MemberEntity();
    	member.setMember_type(((String)aValues.get(aNames.indexOf("member_type"))).charAt(0));
    	member.setSalute((String)aValues.get(aNames.indexOf("salute")));
    	member.setFirst_name((String)aValues.get(aNames.indexOf("first_name")));
    	member.setSur_name((String)aValues.get(aNames.indexOf("sur_name")));
    	member.setGender(((String)aValues.get(aNames.indexOf("gender"))).charAt(0));
    	member.setPassport_num((String)aValues.get(aNames.indexOf("passport_num")));
    	member.setPostal_code((String)aValues.get(aNames.indexOf("postal_code")));
    	member.setMailing_address((String)aValues.get(aNames.indexOf("mailing_address")));
    	member.setEmail_address((String)aValues.get(aNames.indexOf("email_address")));
    	member.setEmail_correspondence((String)aValues.get(aNames.indexOf("email_correspondence")));
    	member.setContact_phone((String)aValues.get(aNames.indexOf("contact_phone")));
    	member.setFax((String)aValues.get(aNames.indexOf("fax")));
    	member.setPassword((String)aValues.get(aNames.indexOf("password")));
    	member.setPassword_reminder_question((String)aValues.get(aNames.indexOf("password_reminder_question")));
    	member.setPassword_reminder_answer((String)aValues.get(aNames.indexOf("password_reminder_answer")));
    	
    	String dateString = (String)aValues.get(aNames.indexOf("date_of_birth"));
    	Date date_of_birth = stringToDate(dateString, "dd/MM/yyyy");
    	member.setDate_of_birth(date_of_birth);
    	
    	//Create a session, get the transaction, persist the entity, commit, close the session
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.persist(member);
    	session.getTransaction().commit();
    	session.close();
    	return member.getMember_num();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return -1;
    	}
    }
    
    //Check if the value exist in the column attribute of the table
    public boolean doCheckMember(String attribute, String value){
    	if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	Criteria cri = session.createCriteria(MemberEntity.class);
    	cri.add(Restrictions.ilike(attribute, value));
    	if(cri.list().isEmpty()){
    		session.close();
    		return false; //not exist, can add
    	}
    	else{
    		session.close();
    		return true; //exist, cannot add
    	}
    }
    
    public void doAddBilling(int member_num, List<String> aNames, List<Object> aValues){
    	if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	MemberEntity member = (MemberEntity)session.get(MemberEntity.class, member_num);
    	
    	BillingEntity billing = new BillingEntity(member, 1, ((String)aValues.get(aNames.indexOf("payment_mode_ind"))).charAt(0));
    	switch(((String)aValues.get(aNames.indexOf("payment_mode_ind"))).charAt(0)){
    	case 'C':
    		billing.setCredit_card_type((String)aValues.get(aNames.indexOf("credit_card_type")));
    		billing.setCredit_card_member_name((String)aValues.get(aNames.indexOf("credit_card_member_name")));
    		billing.setCredit_card_num((String)aValues.get(aNames.indexOf("credit_card_num")));
    		billing.setCvv((String)aValues.get(aNames.indexOf("cvv")));
    		String dateString = (String) aValues.get(aNames.indexOf("date_of_expiration"));
    		Date date_of_expiration = stringToDate(dateString, "MM/yyyy");
    		billing.setDate_of_expiration(date_of_expiration);
    		break;
    	case 'G':
    		billing.setBank_name((String)aValues.get(aNames.indexOf("bank_name")));
    		billing.setBank_account_num((String)aValues.get(aNames.indexOf("bank_account_num")));
    		billing.setAccount_name((String)aValues.get(aNames.indexOf("account_name")));
    		break;
    	case 'Q':
    		//do nothing
    		break;
    	}
    	
    	session.persist(billing);
    	session.getTransaction().commit();
    	session.close();
    	
    }
    
    public boolean doCheckBilling(String attribute, String value){
    	if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	Criteria cri = session.createCriteria(BillingEntity.class);
    	cri.add(Restrictions.ilike(attribute, value));
    	if(cri.list().isEmpty()){
    		session.close();
    		return false; //not exist, can add
    	}
    	else{
    		session.close();
    		return true; //exist, cannot add
    	}
    }
    
    //Add both an account and its associative clients (phone numbers)
    public void doAddClient(int member_num, List<String> clis, List<String> fax_clis, String plan_code){
    	if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	MemberEntity member = (MemberEntity)session.get(MemberEntity.class, member_num);
    	AccountEntity account = new AccountEntity(member, plan_code);
    	session.persist(account);
    	session.flush();
    	
    	clis=removeDuplicate(clis);
    	fax_clis=removeDuplicate(fax_clis);
    	
    	for(String cli: clis){
    		ClientEntity client = new ClientEntity(cli, 'N', account);
    		session.persist(client);
    		session.flush();
    	}
    	
    	for(String fax_cli: fax_clis){
    		ClientEntity client = new ClientEntity(fax_cli, 'Y', account);
    		session.persist(client);
    		session.flush();
    	}
    	
    	session.getTransaction().commit();
    	session.close();
    	
    }
    
    private List<String> removeDuplicate(List<String> list){
    	
    	List<String> list2 = new ArrayList<String>();
    	for(String element: list){
    		if(list2.indexOf(element)==-1)
    			list2.add(element);
    	}
    	return list2;
    	
    }
    
    private Date stringToDate(String dateString, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
		Date date = sdf.parse(dateString);
		return date;
		}catch(Exception e){
			return null;
		}
	}
    
    private String dateToString(Date date, String format){
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	try{
    		String dateString = sdf.format(date);
    		return dateString;
    	}catch(Exception e){
    		return null;
    	}
    }
    
    //List of some properties of all members
    public List<String> doListMember(){
    	List<String> members = new ArrayList<String>();
    	if(sessionFactory==null)
    		setUp();
    	String hql = "select new map (" +
    			"m.first_name as firstName, m.sur_name as surName, " +
    			"m.member_num as id, m.passport_num as passport) " +
    			"from entity.MemberEntity m";
    	Session session = sessionFactory.openSession();
    	for(Object o: session.createQuery(hql).list()){
    		Map<String, Object> member  = (Map<String, Object>)o;
    		members.add(member.get("id")+"/"+member.get("firstName")+"/"+member.get("surName")+"/"+member.get("passport"));
    		
    	}
    	session.close();
    	return members;
    }
    
    // Return member profile
    public MemberState doGetMemberProfile(int member_num){
    	MemberState memberState = new MemberState();
    	
    	if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	MemberEntity member = (MemberEntity)session.get(MemberEntity.class, member_num);
    	if(member==null)
    		return null;
    	
    	memberState.member_num = String.valueOf(member.getMember_num());
    	memberState.member_type = member.getMember_type()+"";
    	memberState.salute = member.getSalute();
    	memberState.first_name = member.getFirst_name();
    	memberState.sur_name = member.getSur_name();
    	memberState.gender = ((member.getGender()=='M')?"Male":"Female");
    	
    	memberState.date_of_birth = dateToString(member.getDate_of_birth(), "yyyy-MM-dd");
    	
    	memberState.passport_num = member.getPassport_num();
    	memberState.postal_code = member.getPostal_code();
    	memberState.mailing_address = member.getMailing_address();
    	memberState.email_address = member.getEmail_address();
    	memberState.email_correspondence = member.getEmail_correspondence();
    	memberState.contact_phone = member.getContact_phone();
    	memberState.fax = member.getFax();
    	
    	return memberState;
    }
    
    //Properties of the first billing of a member only, for demo only
    public BillingState doGetMemberBilling(int member_num){
    	BillingState billingState = new BillingState();
    	
    	if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	
    	String hql = "from entity.BillingEntity b where b.billingPK.member_num = :member_num and b.billingPK.billing_seq_num = 1";
    	Query query = session.createQuery(hql);
    	query.setParameter("member_num", member_num);
    	
    	BillingEntity billing = (BillingEntity)query.list().get(0);
    	
    	if(billing==null)
    		return null;
    	
    	switch(billing.getPayment_mode_ind()){
    	case 'C':
    		billingState.payment_mode_ind = "C";
    		billingState.credit_card_member_name = billing.getCredit_card_member_name();
    		billingState.credit_card_num = billing.getCredit_card_num();
    		billingState.credit_card_type = billing.getCredit_card_type();
    		billingState.date_of_expiration = dateToString(billing.getDate_of_expiration(), "MM/yyyy");
    		break;
    	case 'G':
    		billingState.payment_mode_ind = "G";
    		billingState.bank_name = billing.getBank_name();
    		billingState.bank_account_num = billing.getBank_account_num();
    		billingState.account_name = billing.getAccount_name();
    		break;
    	case 'Q':
    		
    		billingState.payment_mode_ind = "Q";
    	}
    	
    	
    	return billingState;
	}
	
    //Similarly, first account of member only, and its associated phone number
	public List<String> doGetMemberAccount(int member_num){
		List<String> profile = new ArrayList<String>();
    	
    	if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	
    	String hql = "from entity.AccountEntity a where a.member.member_num = :member_num";
    	Query query = session.createQuery(hql);
    	query.setParameter("member_num", member_num);
    	
    	AccountEntity account = (AccountEntity)query.list().get(0);
    	List<ClientEntity> clients = new ArrayList<ClientEntity>();
    	for(ClientEntity client: account.getClients())
    		clients.add(client);
    	
    	profile.add("Plan: "+account.getPlan_code());
    	int i=1;
    	for(ClientEntity c: clients){
    		String s = i+". "+c.getClientPK().getCli();
    		if(c.getFax_cli()=='Y')
    			s = s+" (F)";
    		profile.add(s);
    		i++;
    	}
    	
    	return profile;
	}
	
	/* return -2: not existing phone number (CLI)
	 * 		-1: wrong password
	 *      member_num: login successfully
	 */
	public int doLogin(String phone_number, String password){
		if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
		
    	String hql = "from entity.ClientEntity c where c.clientPK.cli = :cli";
		Query query = session.createQuery(hql);
		query.setParameter("cli", phone_number);
		if(query.list().isEmpty())
			return -2;
		
		ClientEntity client = (ClientEntity)query.list().get(0);
		AccountEntity account = (AccountEntity)session.get(AccountEntity.class, client.getClientPK().getAccount_num());
		MemberEntity member = (MemberEntity)session.get(MemberEntity.class, account.getMember().getMember_num());
		
		if(member.getPassword().equals(password))
			return member.getMember_num();
		else
			return -1;
			
		
	}
	
	public void doAddCarrier(String carrier_id, String carrier_name, String good_for_fax){
		if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	
    	if((session.get(CarrierEntity.class, carrier_id))==null){
    		CarrierEntity carrier = new CarrierEntity(carrier_id, carrier_name, good_for_fax.charAt(0));
    		session.persist(carrier);
    		session.flush();
    	}
	}
	
	public List<AccountState> doListAccount(String member_num){
		if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
		List<AccountState> accounts = new ArrayList<AccountState>();
		
		String hql = "from entity.AccountEntity a where a.member.member_num = :member_num";
		Query query = session.createQuery(hql);
		query.setParameter("member_num", Integer.parseInt(member_num));
		
		for(Object o: query.list()){
			AccountEntity account = (AccountEntity)o;
			AccountState accountState = new AccountState(account.getAccount_num(), account.getPlan_code());
			accounts.add(accountState);
		}		
		return accounts;
	}
	
	public List<CarrierState> doListCarrier(String account_num){
		if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
		List<CarrierState> carriers = new ArrayList<CarrierState>();
		String hql;
		Query query;
		if(account_num.equals("-1")){
			hql = "from entity.CarrierEntity";
			query = session.createQuery(hql);
		}
		else{
			hql = "select c " +
					"from entity.CarrierEntity c, entity.AccountLCR a " +
					"where a.account.account_num = :account_num " +
					"and a.carrier.carrier_id = c.carrier_id";
			query = session.createQuery(hql);
			query.setParameter("account_num", Integer.parseInt(account_num));
		}
		
		for(Object o: query.list()){
			CarrierEntity carrier = (CarrierEntity)o;
			CarrierState carrierState = new CarrierState(carrier.getCarrier_id(), carrier.getCarrier_name(), carrier.getGood_for_fax()+"");
			carriers.add(carrierState);
		}
		
		return carriers;
	}
	
	public boolean doAddAccountLCR(String account_num, String carrier_id, String carrier_seq_num){
		if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	try{
    		AccountEntity account = (AccountEntity)session.get(AccountEntity.class, Integer.parseInt(account_num));
    		if(account==null)
    			return false;
    		CarrierEntity carrier = (CarrierEntity)session.get(CarrierEntity.class, carrier_id);
    		if(carrier==null)
    			return false;
    		
    		AccountLCR accountLCR = new AccountLCR(account, carrier, Integer.parseInt(carrier_seq_num));
    		session.persist(accountLCR);
    		session.flush();
    		
    	}catch(Exception e){
    		return false;
    	}

    	return true;
	}
	
	public void doDeleteAccountLCR(String account_num){
		if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	
    	String hql = "from entity.AccountLCR a where a.account.account_num = :account_num";
    	Query query = session.createQuery(hql);
    	query.setParameter("account_num", Integer.parseInt(account_num));
    	
    	for(Object o: query.list()){
    		AccountLCR accountLCR = (AccountLCR)o;
    		session.merge(accountLCR);
    		session.delete(accountLCR);
    	}
    	session.flush();
	}

	public boolean doUpdateMember(MemberState memberState){
		if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	
    	MemberEntity member = (MemberEntity)session.get(MemberEntity.class, Integer.parseInt(memberState.member_num));
    	
    	if(member==null)
    		return false;
    	member.setContact_phone(memberState.contact_phone);
    	member.setFax(memberState.fax);
    	member.setMailing_address(memberState.mailing_address);
    	member.setPostal_code(memberState.postal_code);
    	member.setEmail_address(memberState.email_address);
    	member.setEmail_correspondence(memberState.email_correspondence);
    	session.update(member);
    	session.flush();
    	session.close();
    	
		return true;
	}
	
	public boolean doUpdatePassword(String member_num, String old_password, String new_password){
		if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
    	
    	MemberEntity member = (MemberEntity)session.get(MemberEntity.class, Integer.parseInt(member_num));
    	
    	if(member==null)
    		return false;
    	
    	if(!member.getPassword().equals(old_password))
    		return false;
    	member.setPassword(new_password);
    	session.update(member);
    	session.flush();
    	session.close();
    	
    	return true;
	}
	
	public boolean doUpdateBilling(String member_num, BillingState billingState){
		if(sessionFactory==null)
    		setUp();
    	Session session = sessionFactory.openSession();
		
    	String hql = "from entity.BillingEntity b where b.member.member_num = :member_num and b.billingPK.billing_seq_num = 1";
    	Query query = session.createQuery(hql);
    	query.setParameter("member_num", Integer.parseInt(member_num));
    	
    	if(query.list().isEmpty())
    		return false;
    	
    	BillingEntity billing = (BillingEntity)query.list().get(0);
    	
    	if(billingState.payment_mode_ind.equalsIgnoreCase("C")){
    		billing.setCredit_card_num(billingState.credit_card_num);
    		billing.setCredit_card_member_name(billingState.credit_card_member_name);
    		billing.setCredit_card_type(billingState.credit_card_type);
    		billing.setCvv(billingState.cvv);
    		billing.setDate_of_expiration(stringToDate(billingState.date_of_expiration,"MM/yyyy"));
    		billing.setPayment_mode_ind('C');
    		billing.setAccount_name("");
    		billing.setBank_account_num("");
    		billing.setBank_name("");
    		session.update(billing);
    		session.flush();
    		session.close();
    		return true;
    	}
    	
    	if(billingState.payment_mode_ind.equalsIgnoreCase("G")){
    		billing.setCredit_card_num("");
    		billing.setCredit_card_member_name("");
    		billing.setCredit_card_type("");
    		billing.setCvv("");
    		billing.setDate_of_expiration(null);
    		billing.setPayment_mode_ind('G');
    		billing.setAccount_name(billingState.account_name);
    		billing.setBank_account_num(billingState.bank_account_num);
    		billing.setBank_name(billingState.bank_name);
    		session.update(billing);
    		session.flush();
    		session.close();
    		return true;
    	}
    	
    	return true;
	}
}





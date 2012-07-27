package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.text.SimpleDateFormat;
import com.tsp.zone.*;

public class ZoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//Stub files of the web service
	private ZoneManager zm = new ZoneManager();
	private ZoneManagerPortType zmpt = zm.getZoneManagerPort();
	
	public void init(){
		System.out.println("ZoneServlet: init()");
	}
	
    public ZoneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//dispatcher to redirect the flow
		RequestDispatcher dispatcher;
		ServletContext servletContext = getServletContext();
		//session to save progress
		HttpSession session;
		
		//get page where request is sent to, the next page in the flow
		String page = request.getPathInfo().substring(1);
		
		//depends on the page, prepare the data needed
		if(page.equals("SignupStep1")){
			session = request.getSession(true);
			session.setAttribute("inProgress", true);
			session.setMaxInactiveInterval(600);
			System.out.println("Signing up in progress: step 1");
			//redirect to SignupStep1
		}
		
		if(page.equals("SignupStep2")){
			session = request.getSession();
			if(session.getAttribute("inProgress")==null){
				request.setAttribute("error", "notInProgress");
				page="Error";
			}
			else{
				session.setAttribute("plan_code", request.getParameter("plan_code"));
				session.setAttribute("payment_mode_ind", request.getParameter("payment_mode_ind"));
				System.out.println("Signing up in progress: step 2");
			}
		}
		
		if(page.equals("SignupStep3")){
			session = request.getSession();
			if(session.getAttribute("inProgress")==null){
				request.setAttribute("error", "notInProgress");
				page="Error";
			}
			else{
				System.out.println("Signing up in progress: step 3");
				if(doRecordMember(request)==0)
					page="Error";
			}
			
		}
		
		if(page.equals("Result")){
			session = request.getSession();
			if(session.getAttribute("inProgress")==null){
				request.setAttribute("error", "notInProgress");
				page="Error";
			}
			else{
				System.out.println("Signing up in progress: final step");
				if(doRecordBilling(request)==1){
					if(doRecordClient(request)==0)
						page="Error";
				}
				else{
					page="Error";
				}
			}
			
			if(page.equals("Result")){
				int member_num = zmpt.doAddMember((List<String>)session.getAttribute("memberANames"), (List<Object>)session.getAttribute("memberAValues"));
				zmpt.doAddBilling(member_num, (List<String>)session.getAttribute("billingANames"), (List<Object>)session.getAttribute("billingAValues")); 
				zmpt.doAddClient(member_num, (List<String>)session.getAttribute("clis"), (List<String>)session.getAttribute("fax_clis"), (String)session.getAttribute("plan_code"));
				
			}
			
			
		}
		
		if(page.equals("Index")){}
		
		if(page.equals("Signin")){}
		
		if(page.equals("Home")){
			String member_num, signin_phone_num, signin_password;
			session = request.getSession();
			member_num = (String)session.getAttribute("member_num");
			signin_phone_num = request.getParameter("signin_phone_num");
			signin_password = request.getParameter("signin_password");
			
			if(member_num==null){
				if((signin_phone_num==null)&&(signin_password==null)){
					page="Error";
					request.setAttribute("error", "notSignedIn");
				}
				else{
					int temp = zmpt.doLogin(signin_phone_num, signin_password); 
					if(temp<0){
						page="Error";
						request.setAttribute("error", "invalidSigninInfo");
					}
					else{
						session.setMaxInactiveInterval(1800);
						session.setAttribute("member_num", String.valueOf(temp));
					}
				}
			}
		}
		
		if(page.equals("Signout")){
			session = request.getSession();
			session.invalidate();
			page="Signin";
		}
		
		if(page.equals("ProviderStep1")){
			if(!checkSignedIn(request)){
				page="Error";
			}
			else{
				String member_num = (String)request.getSession().getAttribute("member_num");
				List<AccountState> accounts = zmpt.doListAccount(member_num);
				request.setAttribute("accounts", accounts);
			}
		}
		
		if(page.equals("ProviderStep2")){
			if(!checkSignedIn(request)){
				page="Error";
			}
			else{
				if(request.getParameter("selected_account")==null){
					page="Error";
					request.setAttribute("error", "noProviderStep1");
				}
				else{
					session = request.getSession();
					session.setAttribute("selected_account", request.getParameter("selected_account"));
					List<CarrierState> allCarriers = zmpt.doListCarrier("-1");
					List<CarrierState> selectedCarriers = zmpt.doListCarrier(request.getParameter("selected_account"));
					request.setAttribute("allCarriers", allCarriers);
					request.setAttribute("selectedCarriers", selectedCarriers);
				}
			}
			
		}
		
		if(page.equals("ProviderStep3")){
			if(!checkSignedIn(request)){
				page="Error";
			}
			else{
				if(request.getSession().getAttribute("selected_account")==null){
					page="Error";
					request.setAttribute("error", "noProviderStep1");
				}
				else{
					if(doRecordProvider(request)==0)
						page="Error";
				}
			}
			
		}
		
		if(page.equals("Membership")){
			if(!checkSignedIn(request)){
				page="Error";
			}
			else{
				MemberState memberState = zmpt.doGetMemberProfile(Integer.parseInt((String)request.getSession().getAttribute("member_num")));
				BillingState billingState = zmpt.doGetMemberBilling(Integer.parseInt((String)request.getSession().getAttribute("member_num")));
				System.out.println(billingState.getPaymentModeInd());
				request.setAttribute("memberState", memberState);
				request.setAttribute("billingState", billingState);
			}
		}
		
		if(page.equals("UpdateMember")){
			if(!checkSignedIn(request)){
				page="Error";
			}
			else{
				if(doUpdateMember(request)==0)
					page="Error";
				else
					page="Home";
			}
		}
		
		if(page.equals("UpdatePassword")){
			if(!checkSignedIn(request)){
				page="Error";
			}
			else{
				if(doUpdatePassword(request)==0)
					page="Error";
				else
					page="Home";
			}
		}
		
		if(page.equals("UpdateBilling")){
			if(!checkSignedIn(request)){
				page="Error";
			}
			else{
				if(request.getParameter("payment_mode_ind")==null){
					page="Error";
					request.setAttribute("error", "noUpdateBilling");
				}
				else{
					request.getSession().setAttribute("selected_payment_mode_ind", request.getParameter("payment_mode_ind"));
				}
			}
		}
		
		if(page.equals("UpdateBilling2")){
			if(!checkSignedIn(request)){
				page="Error";
			}
			else{
				if(doUpdateBilling(request)==0){
					page="Error";
				}
				else{
					page="Home";
				}
			}
		}
		//Get the dispatcher based on the page name, a set in web.xml
		dispatcher = servletContext.getNamedDispatcher(page);
		//forwarding
		dispatcher.forward(request, response);
	}
	
	//Validate input, display error if found, else save data into session
	private int doRecordMember(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		List<String> aNames = new ArrayList<String>();
		List<Object> aValues = new ArrayList<Object>();
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("salute");
		names.add("first_name");
		names.add("sur_name");
		names.add("passport_num");
		names.add("postal_code");
		names.add("mailing_address");
		names.add("email_address");
		names.add("email_correspondence");
		names.add("contact_phone");
		names.add("fax");
		names.add("password_reminder_question");
		names.add("password_reminder_answer");	
		for(String name: names){
			boolean check = addToAttributesFromRequest(aNames, aValues, name, request);
			if(check==false){
				request.setAttribute("error", "emptyFields");
				System.out.println("Empty field(s)");
				return 0;
			}			
		}
		addToAttributes(aNames, aValues, "member_type", "I");
		addToAttributes(aNames, aValues, "gender", request.getParameter("gender"));
		
		//process date_of_birth
		String dateString = request.getParameter("date_of_birth");
		if((dateString==null)||(dateString.equals(""))){
			request.setAttribute("error", "emptyFields");
			System.out.println("Empty date");
			return 0;
		}
		Date date = stringToDate(dateString, "dd/MM/yyyy");
		if(date==null){
			System.out.println("Wrong date format");
			request.setAttribute("error", "wrongDateFormat");
			return 0;
		}
		else{
			addToAttributes(aNames, aValues, "date_of_birth", dateString);
		}
		
		//process password
		String password = request.getParameter("password");
		String repeat_password = request.getParameter("repeat_password");
		if((password==null)||(repeat_password==null)||(password.equals(""))||(repeat_password.equals(""))){
			request.setAttribute("error", "emptyFields");
			System.out.println("Empty password/repeat password");
			return 0;
		}
		if(password.compareTo(repeat_password)==0){
			addToAttributes(aNames, aValues, "password", password);
		}else{
			request.setAttribute("error", "unmatchedPasswords");
			System.out.println("Unmatched passwords");
			return 0;
		}
		//check if member exists: passport_num, email_address, email_correspondence, contact_phone
		if(zmpt.doCheckMember("passport_num", (String)aValues.get(aNames.indexOf("passport_num")))){
			request.setAttribute("error", "existPassport");
			return 0;
		}
		
		if(zmpt.doCheckMember("email_address", (String)aValues.get(aNames.indexOf("email_address")))){
			request.setAttribute("error", "existEmail");
			return 0;
		}
		
		
		session.setAttribute("memberANames", aNames);
		session.setAttribute("memberAValues", aValues);
		return 1;
	}
	
	//Utility function to add (and maintain) attributes to 2 Lists directly from HttpServletRequest
	private boolean addToAttributesFromRequest(List<String> aNames, List<Object> aValues, String name, HttpServletRequest request){
		if(request.getParameter(name)==null)
			return false;
		if(request.getParameter(name).equals(""))
			return false;
		else{
			addToAttributes(aNames, aValues, name, request.getParameter(name));
			return true;
		}
	}
	
	//Basic add and maintain attribute list
	private void addToAttributes(List<String> aNames, List<Object> aValues, String name, Object value){
		if(aNames.indexOf(name)!=-1){
			aValues.set(aNames.indexOf(name), value);
		}
		else{
			aNames.add(name);
			aValues.add(value);
		}
	}
	
	//Convert String to Date, more advanced like drop-down lists can be implemented later
	private Date stringToDate(String dateString, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
		Date date = sdf.parse(dateString);
		return date;
		}catch(Exception e){
			return null;
		}
	}
	
	//similarly, save Billing details into session
	private int doRecordBilling(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		List<String> aNames = new ArrayList<String>();
		List<Object> aValues = new ArrayList<Object>();
		ArrayList<String> names = new ArrayList<String>();
		
		addToAttributes(aNames, aValues, "payment_mode_ind", session.getAttribute("payment_mode_ind"));
		switch(((String)session.getAttribute("payment_mode_ind")).charAt(0)){
		case 'C':
			names.add("credit_card_type");
			names.add("cvv");
			names.add("credit_card_member_name");
			break;
		case 'G':
			names.add("bank_name");
			names.add("bank_account_num");
			names.add("account_name");
		}
		
		for(String name: names){
			boolean check = addToAttributesFromRequest(aNames, aValues, name, request);
			if(check==false){
				request.setAttribute("error", "emptyFields");
				System.out.println("Empty field(s)");
				return 0;
			}			
		}
		
		if(((String)session.getAttribute("payment_mode_ind")).charAt(0)=='C'){
			String field1 = request.getParameter("field1");
			String field2 = request.getParameter("field2");
			String field3 = request.getParameter("field3");
			String field4 = request.getParameter("field4");
			if((field1.length()<4)||(field2.length()<4)||(field3.length()<4)||(field4.length()<4)){
				request.setAttribute("error", "emptyFields");
				System.out.println("Empty field(s)");
				return 0;
			}
			else{
				String credit_card_num = field1+"-"+field2+"-"+field3+"-"+field4;
				addToAttributes(aNames, aValues, "credit_card_num", credit_card_num);
			}
			
			String dateString = request.getParameter("date_of_expiration");
			if((dateString==null)||(dateString.equals(""))){
				request.setAttribute("error", "emptyFields");
				System.out.println("Empty date");
				return 0;
			}
			Date date_of_expiration = stringToDate(dateString, "MM/yyyy");
			if(date_of_expiration==null){
				System.out.println("Wrong date format");
				request.setAttribute("error", "wrongDateFormat");
				return 0;
			}
			else{
				addToAttributes(aNames, aValues, "date_of_expiration", dateString);
			}
		}
		
		if(((String)aValues.get(aNames.indexOf("payment_mode_ind"))).charAt(0)=='C'){
			if(zmpt.doCheckBilling("credit_card_num", (String)aValues.get(aNames.indexOf("credit_card_num")))){
				request.setAttribute("error", "existCreditCard");
				return 0;
			}
		}
		
		if(((String)aValues.get(aNames.indexOf("payment_mode_ind"))).charAt(0)=='G'){
			if(zmpt.doCheckBilling("bank_account_num", (String)aValues.get(aNames.indexOf("bank_account_num")))){
				request.setAttribute("error", "existAccount");
				return 0;
			}
		}
		
		
		session.setAttribute("billingANames", aNames);
		session.setAttribute("billingAValues", aValues);
		return 1;
	}
	
	//Save Account and Client details
	private int doRecordClient(HttpServletRequest request){
		HttpSession session = request.getSession();
		List<String> clis = new ArrayList<String>();
		List<String> fax_clis = new ArrayList<String>();
		
		String[] cli = request.getParameterValues("cli[]");
		String[] fax = request.getParameterValues("fax[]");
		
		int check = 0;
		for(String c: cli){
			if(c.length()>0)
				check++;
		}
		if(check==0){
			request.setAttribute("error", "emptyClients");
			return 0;
		}
			
		
		for(String c: cli){
			if((c.length()>0)&&(c.length()<8)){
				request.setAttribute("error", "shortClients");
				return 0;
			}
		}
		
		if(fax!=null){
			for(int i=0; i<6; i++){
				if(cli[i].equals("")){
					//do nothing
				}
				else{
					boolean ifFax = false;
					for(String f: fax){
						if(String.valueOf(i).equals(f)){
							fax_clis.add(cli[i]);
							ifFax=true;
						}
					}
					if(!ifFax)
						clis.add(cli[i]);
				}
				
			}
		}
		else{
			for(String c: cli)
				if(!c.equals(""))
					clis.add(c);
		}
		
		session.setAttribute("clis", clis);
		session.setAttribute("fax_clis", fax_clis);
		return 1;
	}
	
	private boolean checkSignedIn(HttpServletRequest request){
		HttpSession session = request.getSession();
		String member_num = (String)session.getAttribute("member_num");
		if(member_num==null){
			request.setAttribute("error", "notSignedIn");
			return false;
		}
		else
			return true;
	}
	
	private int doRecordProvider(HttpServletRequest request){
		List<CarrierState> allCarriers = zmpt.doListCarrier("-1");
		List<String> choices = new ArrayList<String>();
		
		for(CarrierState carrier: allCarriers){
			String choice = request.getParameter(carrier.getCarrierId());
			choices.add(choice);
		}
		
		for(int i=0; i<choices.size(); i++){
			for(int j=i+1; j<choices.size(); j++){
				if((choices.get(i).equals(choices.get(j)))&&(!choices.get(i).equals("0"))){
					request.setAttribute("error", "duplicatedSelection");
					return 0;
				}
			}
		}
		
		zmpt.doDeleteAccountLCR((String)request.getSession().getAttribute("selected_account"));
		
		for(CarrierState carrier: allCarriers){
			String carrier_seq_num = request.getParameter(carrier.getCarrierId());
			if(!carrier_seq_num.equals("0")){
				System.out.println(carrier.getCarrierId()+" "+carrier.getCarrierName());
				zmpt.doAddAccountLCR((String)request.getSession().getAttribute("selected_account"), carrier.getCarrierId(), carrier_seq_num);
			}
		}
		
		return 1;
	}

	private int doUpdateMember(HttpServletRequest request){
		String contact_phone = request.getParameter("contact_phone");
		String fax = request.getParameter("fax");
		String mailing_address = request.getParameter("mailing_address");
		String postal_code = request.getParameter("postal_code");
		String email_address = request.getParameter("email_address");
		String email_correspondence = request.getParameter("email_correspondence");
		
		if((contact_phone.length()==0)||(mailing_address.length()==0)||(postal_code.length()==0)||(email_address.length()==0)||(email_correspondence.length()==0)){
			request.setAttribute("error", "emptyFields");
			return 0;
		}
		
		if(zmpt.doCheckMember("email_address", email_address)){
			request.setAttribute("error", "existEmail");
			return 0;
		}
		
		MemberState memberState = new MemberState();
		memberState.setMemberNum((String)request.getSession().getAttribute("member_num"));
		memberState.setContactPhone(contact_phone);
		memberState.setFax(fax);
		memberState.setMailingAddress(mailing_address);
		memberState.setPostalCode(postal_code);
		memberState.setEmailAddress(email_address);
		memberState.setEmailCorrespondence(email_correspondence);
		
		zmpt.doUpdateMember(memberState);
		return 1;
	}

	private int doUpdatePassword(HttpServletRequest request){
		String password = request.getParameter("password");
		String new_password = request.getParameter("new_password");
		String repeat_new_password = request.getParameter("repeat_new_password");
		
		if(password.length()*new_password.length()*repeat_new_password.length()==0){
			request.setAttribute("error", "emptyFields");
			return 0;
		}
		
		if(!new_password.equals(repeat_new_password)){
			request.setAttribute("error", "unmatchedPasswords");
			return 0;
		}
		
		if(!zmpt.doUpdatePassword((String)request.getSession().getAttribute("member_num"), password, new_password)){
			request.setAttribute("error", "invalidPassword");
			return 0;
		}
		
		return 1;
	}

	private int doUpdateBilling(HttpServletRequest request){
		Character payment_mode_ind = ((String)request.getSession().getAttribute("selected_payment_mode_ind")).charAt(0);
		
		if(payment_mode_ind=='C'){
			String credit_card_type = request.getParameter("credit_card_type");
			String cvv = request.getParameter("cvv");
			String credit_card_member_name = request.getParameter("credit_card_member_name");
			String date_of_expiration = request.getParameter("date_of_expiration");
			
			if(credit_card_type.length()*cvv.length()*credit_card_member_name.length()*date_of_expiration.length()==0){
				request.setAttribute("error", "emptyFields");
				return 0;
			}
			
			if(stringToDate(date_of_expiration, "MM/yyyy")==null){
				request.setAttribute("error", "wrongDateFormat");
				return 0;
			}
			
			String credit_card_num;
			
			String field1 = request.getParameter("field1");
			String field2 = request.getParameter("field2");
			String field3 = request.getParameter("field3");
			String field4 = request.getParameter("field4");
			if((field1.length()<4)||(field2.length()<4)||(field3.length()<4)||(field4.length()<4)){
				request.setAttribute("error", "emptyFields");
				System.out.println("Empty field(s)");
				return 0;
			}
			else{
				credit_card_num = field1+"-"+field2+"-"+field3+"-"+field4;
			}
			
			if(zmpt.doCheckBilling("credit_card_num", credit_card_num)){
				request.setAttribute("error", "existCreditCard");
				return 0;
			}
			
			BillingState billingState = new BillingState();
			billingState.setPaymentModeInd("C");
			billingState.setCreditCardType(credit_card_type);
			billingState.setCreditCardNum(credit_card_num);
			billingState.setCvv(cvv);
			billingState.setCreditCardMemberName(credit_card_member_name);
			billingState.setDateOfExpiration(date_of_expiration);
			
			zmpt.doUpdateBilling((String)request.getSession().getAttribute("member_num"), billingState);
			return 1;
		}
		
		if(payment_mode_ind=='G'){
			String bank_name = request.getParameter("bank_name");
			String account_name = request.getParameter("account_name");
			String bank_account_num = request.getParameter("bank_account_num");
			
			if(bank_name.length()*account_name.length()*bank_account_num.length()==0){
				request.setAttribute("error", "emptyFields");
				return 0;
			}
			
			BillingState billingState = new BillingState();
			billingState.setPaymentModeInd("G");
			billingState.setAccountName(account_name);
			billingState.setBankAccountNum(bank_account_num);
			billingState.setBankName(bank_name);
			
			if(zmpt.doCheckBilling("bank_account_num", bank_account_num)){
				request.setAttribute("error", "existAccount");
				return 0;
			}
			
			zmpt.doUpdateBilling((String)request.getSession().getAttribute("member_num"), billingState);
			return 1;
		}
		return 1;
	}
}










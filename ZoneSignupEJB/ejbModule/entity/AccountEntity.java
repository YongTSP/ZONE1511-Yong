package entity;

import javax.persistence.*;
import java.util.*;


@Entity(name="account_profile")
public class AccountEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(precision=12, scale=0)
	private int account_num;
	
	@ManyToOne
	private MemberEntity member;
	@OneToMany(mappedBy="account")
	private Collection<ClientEntity> clients;
	@OneToMany(mappedBy="account")
	private Collection<AccountLCR> accountLCRs;
	
	private String plan_code;
	
	public AccountEntity(){};
	
	public AccountEntity(MemberEntity member, String plan_code){
		this.member=member;
		this.plan_code=plan_code;
	}

	public int getAccount_num() {
		return account_num;
	}

	public void setAccount_num(int account_num) {
		this.account_num = account_num;
	}

	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}

	public String getPlan_code() {
		return plan_code;
	}

	public void setPlan_code(String plan_code) {
		this.plan_code = plan_code;
	}

	public Collection<ClientEntity> getClients() {
		return clients;
	}

	public void setClients(Collection<ClientEntity> clients) {
		this.clients = clients;
	}

	public Collection<AccountLCR> getAccountLCRs() {
		return accountLCRs;
	}

	public void setAccountLCRs(Collection<AccountLCR> accountLCRs) {
		this.accountLCRs = accountLCRs;
	}
	
	

}

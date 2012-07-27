package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="CLI")
public class ClientEntity {
	
	//PK class
	@Embeddable
	public static class ClientPK implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		private String cli;
		private int account_num;
		
		public ClientPK(){};
		
		public ClientPK(String cli, int account_num){
			this.cli=cli;
			this.account_num=account_num;
		}

		public String getCli() {
			return cli;
		}

		public void setCli(String cli) {
			this.cli = cli;
		}

		public int getAccount_num() {
			return account_num;
		}

		public void setAccount_num(int account_num) {
			this.account_num = account_num;
		}
	}
	
	//PK
	@EmbeddedId
	private ClientPK clientPK;
	
	//Relationship field
	@ManyToOne
	@MapsId("account_num")
	AccountEntity account;
	
	private char fax_cli;
	
	public ClientEntity(){};
	
	public ClientEntity(String cli, char fax_cli, AccountEntity account){
		this.clientPK = new ClientPK(cli, account.getAccount_num());
		this.fax_cli = fax_cli;
		this.account=account;
	}

	public ClientPK getClientPK() {
		return clientPK;
	}

	public void setClientPK(ClientPK clientPK) {
		this.clientPK = clientPK;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public char getFax_cli() {
		return fax_cli;
	}

	public void setFax_cli(char fax_cli) {
		this.fax_cli = fax_cli;
	}
	
	
}

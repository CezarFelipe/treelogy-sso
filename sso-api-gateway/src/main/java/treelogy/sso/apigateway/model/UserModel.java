package treelogy.sso.apigateway.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserModel {

	private String id;
	private String givenname;
	private String emailaddress;

	public UserModel(String id, String givenname, String emailaddress) {
		this.id = id;
		this.givenname = givenname;
		this.emailaddress = emailaddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

}
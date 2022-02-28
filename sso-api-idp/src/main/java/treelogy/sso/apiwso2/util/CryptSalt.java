package treelogy.sso.apiwso2.util;

import java.security.MessageDigest;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class CryptSalt {

	public String Encrypt(String password) throws Exception {


		MessageDigest dgst = MessageDigest.getInstance("SHA-256");
		byte[] byteValue = dgst.digest(password.getBytes());
		String encrypPassword =Base64.getEncoder().encodeToString(byteValue);
		System.out.println("password::" + encrypPassword);

		return encrypPassword;
	}

}

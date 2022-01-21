package treelogy.sso.apigateway.service;

import java.util.HashMap;
import java.util.Map;

import org.opensaml.saml2.core.Attribute;
import org.opensaml.xml.schema.XSString;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;

import treelogy.sso.apigateway.model.UserModel;

@Service
public class SamlUserService implements SAMLUserDetailsService {

	@Override
	public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
		Map<String, String> attributes = new HashMap<String, String>();
		String userID = credential.getNameID().getValue();
		for (Attribute element : credential.getAttributes()) {
			String name = element.getName().replace("http://wso2.org/claims/", "");
			XSString value = (XSString) element.getAttributeValues().get(0);
			attributes.put(name, value.getValue());
		}
		return new UserModel(userID, attributes.get("givenname"), attributes.get("emailaddress"));
	}
}
package treelogy.sso.apigateway.controller;

import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import treelogy.sso.apigateway.model.UserModel;



@Controller
@RequestMapping(path = "/", method = RequestMethod.GET)
public class LoginController {

	@GetMapping(path = "/")
	public ModelAndView index(ExpiringUsernameAuthenticationToken userToken) {
		ModelAndView mav = new ModelAndView("index");
		UserModel user = (UserModel) userToken.getPrincipal();
		mav.addObject("user", user);
		return mav;
	}
}

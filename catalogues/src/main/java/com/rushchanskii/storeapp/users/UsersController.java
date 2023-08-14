package com.rushchanskii.storeapp.users;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UsersController {
	
	private UsersRepository repoUsers;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public UsersController(UsersRepository user) {
		super();
		this.repoUsers = user;
	}

	
	
	@RequestMapping(value="/adduser",method = RequestMethod.GET)
	public String usersService(@RequestParam Map<String,String> model_in, ModelMap model) {
		Users user;
		String loginName ="rushchanskii";
		String encriptedPassword="12345";
		String password="12345";
		String firstName="Konstantin";
		String lastName="Rushchanskii";
		String address="Sesame Street";
		String phoneNumber="5555678";
		String eMail="rushchanskii@mailservice.com";

		
	    
	    user = new Users(10L, loginName, encriptedPassword, 
	    		firstName, lastName, address, phoneNumber, eMail, "MANAGER");
		model.addAttribute("user", user);
		return "userform";
	}
	
	@RequestMapping(value="/adduser",method = RequestMethod.POST)
	public String usersAddService(ModelMap model, Users user) {
		
        user.setUserRole("ROLE_CLIENT");
        user.setEncriptedPassword(encoder.encode(user.getEncriptedPassword()));
		repoUsers.save(user);
	    
		model.addAttribute("user", user);
		return "redirect:/";
	}

}

package com.rushchanskii.storeapp.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	@GetMapping("/login")
	String login() {
		return "login";
	}
}


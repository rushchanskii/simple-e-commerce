//package com.rushchanskii.storeapp.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class InMemoryAuthWebSecurityConfigurer {
//	
//	 @Bean
//	    public InMemoryUserDetailsManager userDetailsService() {
//	        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	        UserDetails user = User.withUsername("manager")
//	            .password(encoder.encode("manager"))
//	            .roles("MANAGER")
//	            .build();
//	        UserDetails client = User.withUsername("client")
//		            .password(encoder.encode("client"))
//		            .roles("CLIENT")
//		            .build();
//	        return new InMemoryUserDetailsManager(user, client);
//	    }
//
//}

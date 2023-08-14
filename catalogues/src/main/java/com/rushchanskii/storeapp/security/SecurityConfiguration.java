package com.rushchanskii.storeapp.security;




import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
				http
		  			.authorizeRequests((authorizeRequests) ->
		  				authorizeRequests
		  				.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasRole("MANAGER")
		  				.requestMatchers(new AntPathRequestMatcher("/client/**")).hasAnyRole("CLIENT", "MANAGER")
		  				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
		  				)
		  			.formLogin((formLogin) ->
                    formLogin
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .loginPage("/login")
                            .failureUrl("/login?error")
                            .loginProcessingUrl("/login")
                            .permitAll()
                   );
//	 			http.formLogin();
				http.csrf().disable();
     			http.headers().frameOptions().disable();
				
		  		return http.build();
    } 
	
	
	@Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select login_name , encripted_password, true "
                + "from users "
                + "where login_name = ?")
            .authoritiesByUsernameQuery("select login_name as username, user_role as role  "
                + "from users "
                + "where login_name = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	
	
	
	
//	@Bean 
//	public PasswordEncoder passwordEncoder() { 
//	    return new BCryptPasswordEncoder(); 
//	}
	
//       @Bean
//       public InMemoryUserDetailsManager userDetailsService() {
//    	   PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//               UserDetails manager = User.withDefaultPasswordEncoder()
//                       .username("manager")
//                       .password(encoder.encode("manager"))
//                       .roles("MANAGER")
//                       .build();
//               UserDetails client = User.withDefaultPasswordEncoder()
//                       .username("client")
//                       .password(encoder.encode("client"))
//                       .roles("CLIENT")
//                       .build();
//               
//               return new InMemoryUserDetailsManager(client, manager);
//       }

}
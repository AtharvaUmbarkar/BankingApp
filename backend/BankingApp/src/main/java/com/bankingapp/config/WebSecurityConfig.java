package com.bankingapp.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.bankingapp.service.AdminService;
import com.bankingapp.service.CustService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
//	@Autowired
//	private FilterChainExceptionHandler filterChainExceptionHandler;
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public FilterChainExceptionHandler filterChainExceptionHandler() {
		return new FilterChainExceptionHandler();
	}
	
	@Bean
	public CustomAuthenticationProvider authProvider() {
		CustomAuthenticationProvider  customAuthenticationProvider = new CustomAuthenticationProvider();
		return customAuthenticationProvider; 
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	@Qualifier("adminAuthenticationManager")
//	public AuthenticationManager adminAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//		return new ProviderManager(Collections.singletonList(adminAuthenticationProvider()));
//	}
//	
//	@Bean
//	@Qualifier("customerAuthenticationManager")
//	public AuthenticationManager customerAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//		return new ProviderManager(Collections.singletonList(customerAuthenticationProvider()));
//	}
//	
//	@Bean
//	public DaoAuthenticationProvider adminAuthenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(adminService);
//		provider.setPasswordEncoder(passwordEncoder());
//		return provider;
//	}
//	
//	@Bean
//	public DaoAuthenticationProvider customerAuthenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(customerService);
//		provider.setPasswordEncoder(passwordEncoder());
//		return provider;
//	}

//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		// Weauth don't need CSRF for this example
		httpSecurity.csrf((csrf) -> csrf.disable()).cors().and()
		// dont authenticate this particular request
		.authorizeHttpRequests((authz) -> authz
				.requestMatchers("/Login",
						"/netBankingRegistration",
						"/createFirstAccount",
						"/forgotPassword",
						"/forgotUserName",
						"/changePassword",
						"/admin/LoginAdmin",
						"admin/SignupAdmin"
						).permitAll()
				.requestMatchers("/admin/**", "/fetchUser", "/getCustomerAndAccountDetails/{id}").hasAnyRole("ADMIN")
		// all other requests need to be authenticated
				.anyRequest().authenticated())
		// make sure we use stateless session; session won't be used to
		// store user's state.
		.addFilterBefore(filterChainExceptionHandler(), LogoutFilter.class)
		.exceptionHandling((exp) -> exp.authenticationEntryPoint(jwtAuthenticationEntryPoint))
		.sessionManagement((mgmt) -> mgmt.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authProvider());

// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
}


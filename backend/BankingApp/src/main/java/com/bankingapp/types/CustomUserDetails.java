package com.bankingapp.types;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bankingapp.models.Customer;

public class CustomUserDetails implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;
	
	public CustomUserDetails(Customer cust) {
		this.customer= cust;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return customer.isUnLocked();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return customer.getLoginPassword();
	}

	@Override
	public String getUsername() {
		return customer.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}

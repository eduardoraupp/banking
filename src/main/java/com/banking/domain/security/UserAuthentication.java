package com.banking.domain.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.banking.domain.account.User;

public class UserAuthentication implements Serializable, UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Role role;
	private final long id;
	private final String username;
	private final String password;

	public UserAuthentication(final User user) {
		this.id = user.getId();
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.role = new Role("ROLE_USER");
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}

	public long getId() {
		return this.id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final Collection<Role> roles = new ArrayList<>();
		roles.add(this.role);
		return roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

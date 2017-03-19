package com.banking.domain.security;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String roleName;

	public Role(final String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		return this.roleName;
	}


}

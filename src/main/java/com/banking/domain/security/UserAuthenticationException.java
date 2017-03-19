package com.banking.domain.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found")
public class UserAuthenticationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public UserAuthenticationException(final String msg){
		super(msg);
	}

	public UserAuthenticationException(final String msg, final Throwable cause){
		super(msg, cause);
	}

}

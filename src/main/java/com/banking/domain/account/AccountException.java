package com.banking.domain.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.PRECONDITION_REQUIRED, reason="Account problem")
public class AccountException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AccountException(final String msg){
		super(msg);
	}

	public AccountException(final String msg, final Throwable cause){
		super(msg, cause);
	}

}

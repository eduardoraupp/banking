package com.banking.infrastructure;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommonPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(final CharSequence rawPassword) {
		final String rawpswd = (String) rawPassword;
		return BCrypt.hashpw(rawpswd, BCrypt.gensalt());
	}

	@Override
	public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
		final String rawpasswd = (String) rawPassword;
		final boolean status = BCrypt.checkpw(rawpasswd, encodedPassword);
		return status;
	}
}
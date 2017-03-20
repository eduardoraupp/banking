package com.banking.domain.security;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.banking.domain.account.User;
import com.banking.infrastructure.Log;
import com.banking.infrastructure.repositories.UserRepository;

@Service
public class UserAuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Log
	private Logger logger;

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		this.logger.info("Loading user by username(email) {}", email);
		final User user = this.userRepository.findByEmail(email);
		if(user == null || user.getId() == null) {
			throw new UsernameNotFoundException("User not found by the email.");
		}
		return new UserAuthentication(user);
	}

}

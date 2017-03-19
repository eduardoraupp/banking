package com.banking.domain.account;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.infrastructure.CommonPasswordEncoder;
import com.banking.infrastructure.Log;

@Component
public class BankingApplication {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommonPasswordEncoder passwordEncoder;
	@Log
	private Logger logger;

	@PostConstruct
	public void init() {
		this.createUserAccount();
	}

	private void createUserAccount() {
		this.logger.info("Create default user and account.");
		try {
			final User user = new User();
			user.setName("Banking");
			user.setSurname("Application");
			user.setEmail("banking@banking.com");
			user.setPassword(this.passwordEncoder.encode("123456"));
			this.userRepository.save(user);
			final Account account = new Account();
			account.setUser(user);
			this.accountRepository.save(account);
			this.logger.info("Account with user created with success.");
		} catch (final Exception e) {
			this.logger.error("Not possible to create the user and account. {}", e.getMessage());
			throw new IllegalStateException(e);
		}
	}

}

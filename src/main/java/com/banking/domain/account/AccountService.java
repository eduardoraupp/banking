package com.banking.domain.account;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.infrastructure.Log;
import com.banking.infrastructure.repositories.AccountRepository;
import com.google.common.base.Preconditions;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Log
	private Logger logger;

	public void makeDeposit(final Account account) {
		try {
			this.logger.info("User of id {} will start making the deposit EUR {}", account.getAmount());
			Preconditions.checkArgument(account.getAmount() > 0d, "You must to deposit an amount bigger than 0");
			final Account accountDeposit = this.accountRepository.findByUser(new User(account.getIdUser()));
			this.logger.info("User found {}", accountDeposit.getUser());
			this.logger.info("Account found {}", accountDeposit);
			accountDeposit.makeDeposit(account.getAmount());
			this.accountRepository.save(accountDeposit);
			this.logger.info("Success in depositing the amount {}", account.getAmount());
		} catch (final IllegalArgumentException e) {
			this.logger.info("Ocurred an error while depositing the value asked: {}", account.getAmount());
			throw new AccountException(e.getMessage());
		} catch (final Exception e) {
			this.logger.info("Ocurred an error. {}", e.getMessage());
			throw new IllegalStateException(e);
		}
	}

	public void makeWithdraw(final Account account) {
		try {
			this.logger.info("User of id {} will start making the withdraw EUR {}", account.getIdUser(), account.getAmount());
			Preconditions.checkArgument(account.getAmount() > 0d, "You must to withdraw an amount bigger than 0");
			final Account accountDeposit = this.accountRepository.findByUser(new User(account.getIdUser()));
			this.logger.info("User found {}", accountDeposit.getUser());
			this.logger.info("Account found {}", accountDeposit);
			accountDeposit.makeWithdraw(account.getAmount());
			this.accountRepository.save(accountDeposit);
			this.logger.info("Success in withdrawing the amount {}", account.getAmount());
		} catch (final IllegalArgumentException |  AccountException e) {
			this.logger.info("Ocurred an error while withdrawing the value asked: {}", account.getAmount());
			throw new AccountException(e.getMessage());
		} catch (final Exception e) {
			this.logger.info("Ocurred an error. {}", e.getMessage());
			throw new IllegalStateException(e);
		}
	}

	@Transactional(readOnly = true)
	public String getBalance(final long idUser) {
		try {
			this.logger.info("User of id {} requests the account balance", idUser);
			final User user = new User(idUser);
			final Account account = this.accountRepository.findByUser(user);
			this.logger.info("Account found {}", account);
			this.logger.info("Success in fetching the account balance");
			return account.getBalance().toString();
		} catch (final Exception e) {
			this.logger.info("Ocurred an error. {}", e.getMessage());
			throw new IllegalStateException(e);
		}
	}



}

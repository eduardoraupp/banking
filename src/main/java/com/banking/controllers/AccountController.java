package com.banking.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banking.domain.account.Account;
import com.banking.domain.account.AccountException;
import com.banking.domain.account.AccountService;
import com.banking.infrastructure.Log;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

	@Log
	private Logger logger;
	@Autowired
	private AccountService accountService;

	@RequestMapping(path="/deposit", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public void makeDeposit(@Valid @RequestBody final Account account) {
		try {
			this.logger.info("Make a deposit of EUR {}", account.getAmount());
			this.accountService.makeDeposit(account);
			this.logger.info("Success in making the deposit.");
		} catch (final AccountException e) {
			throw e;
		} catch (final Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@RequestMapping(path="/withdraw", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public void makeWithdraw(@Valid @RequestBody final Account account) {
		try {
			this.logger.info("Make a withdraw of EUR {}", account.getAmount());
			this.accountService.makeWithdraw(account);
			this.logger.info("Success in making the withdraw.");
		} catch (final AccountException e) {
			throw e;
		} catch (final Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@RequestMapping(path="/{idUser}", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
	@ResponseStatus(value = HttpStatus.OK)
	public String getBalance(@PathVariable final String idUser) {
		try {
			this.logger.info("Look the account balance. User id:", idUser);
			return this.accountService.getBalance(Long.valueOf(idUser));
		} catch (final AccountException e) {
			throw e;
		} catch (final Exception e) {
			throw new IllegalStateException(e);
		}
	}

}

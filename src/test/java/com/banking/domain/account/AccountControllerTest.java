package com.banking.domain.account;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.banking.domain.account.controller.AccountController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { StartupApplicationTest.class, TestConfiguration.class })
public class AccountControllerTest {

	@Autowired
	private AccountController accountController;
	@Test
	public void contexLoads() throws Exception {
		Assert.assertTrue(this.accountController != null);
	}

	@Test
	public void testValidDeposit() {
		try {
			final Account account = new Account();
			account.setAmount(300);
			account.setIdUser(1L);
			this.accountController.makeDeposit(account);
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testValidWithdrawRemainingBalance50() {
		try {
			Account account = null;
			final Double balance = Double.valueOf(this.accountController.getBalance("1"));
			if(balance > 0) {
				account = new Account();
				account.setAmount(balance);
				account.setIdUser(1L);
				this.accountController.makeWithdraw(account);
			}
			account = new Account();
			account.setAmount(300);
			account.setIdUser(1L);
			this.accountController.makeDeposit(account);
			account = new Account();
			account.setAmount(250);
			account.setIdUser(1L);
			this.accountController.makeWithdraw(account);
			Assert.assertEquals(new Double("50"), Double.valueOf(this.accountController.getBalance("1")));
		} catch (final Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testExceptionInsufficienteBalanceWithdraw() {
		try {
			Account account = null;
			final Double balance = Double.valueOf(this.accountController.getBalance("1"));
			if(balance > 0) {
				account = new Account();
				account.setAmount(balance);
				account.setIdUser(1L);
				this.accountController.makeWithdraw(account);
			}
			account = new Account();
			account.setAmount(300);
			account.setIdUser(1L);
			this.accountController.makeDeposit(account);
			account = new Account();
			account.setAmount(350);
			account.setIdUser(1L);
			this.accountController.makeWithdraw(account);
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertEquals("There is not enough amount to be withdrawn", e.getMessage());
		}
	}

	@Test
	public void testWithdraw0Euro() {
		try {
			final Account account = new Account();
			account.setAmount(0);
			account.setIdUser(1L);
			this.accountController.makeWithdraw(account);
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertEquals("You must to withdraw an amount bigger than 0", e.getMessage());
		}
	}

	@Test
	public void testWithdrawNegativeEuroValue() {
		try {
			final Account account = new Account();
			account.setAmount(0);
			account.setIdUser(1L);
			this.accountController.makeWithdraw(account);
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertEquals("You must to withdraw an amount bigger than 0", e.getMessage());
		}
	}

	@Test
	public void testDeposit0Euro() {
		try {
			final Account account = new Account();
			account.setAmount(-1d);
			account.setIdUser(1L);
			this.accountController.makeDeposit(account);
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertEquals("You must to deposit an amount bigger than 0", e.getMessage());
		}
	}

	@Test
	public void testDepositNegativeEuroValue() {
		try {
			final Account account = new Account();
			account.setAmount(-1d);
			account.setIdUser(1L);
			this.accountController.makeDeposit(account);
			Assert.fail();
		} catch (final Exception e) {
			Assert.assertEquals("You must to deposit an amount bigger than 0", e.getMessage());
		}
	}
}

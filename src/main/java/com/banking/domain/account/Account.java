package com.banking.domain.account;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@JsonIgnore
	private BigDecimal balance = new BigDecimal("0");
	@OneToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	@Transient
	@JsonProperty
	private double amount;
	@Transient
	@JsonProperty
	private long idUser;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}


	public long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(final long idUser) {
		this.idUser = idUser;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public void makeDeposit(final double amount) {
		this.balance = this.balance.add(new BigDecimal(amount));
	}

	public void makeWithdraw(final double amount) {
		final BigDecimal newBalance = this.balance.subtract(new BigDecimal(amount));
		if(newBalance.longValue() < 0) {
			throw new AccountException("There is not enough amount to be withdrawn");
		}
		this.balance = newBalance;
	}

	@Override
	public String toString() {
		return "Account [id=" + this.id + ", balance=" + this.balance.toString() + ", user=" + this.user + ", amount=" + this.amount
				+ ", idUser=" + this.idUser + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.id ^ this.id >>> 32);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Account other = (Account) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}

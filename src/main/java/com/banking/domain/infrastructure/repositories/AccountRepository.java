package com.banking.domain.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.domain.account.Account;
import com.banking.domain.account.User;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByUser(User user);

}

package com.banking.domain.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.domain.account.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

}

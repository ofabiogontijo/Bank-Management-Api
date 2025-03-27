package com.fabiogontijo.bank_management_api.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

interface AccountRepository extends JpaRepository<Account, Integer> {

	@Modifying
	@Query("UPDATE Account a SET a.balance = a.balance - :amount WHERE a.accountNumber = :accountNumber")
	Integer debitBalance(int accountNumber, BigDecimal amount);

}

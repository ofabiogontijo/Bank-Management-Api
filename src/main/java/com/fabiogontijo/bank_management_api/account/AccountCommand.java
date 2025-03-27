package com.fabiogontijo.bank_management_api.account;

import com.fabiogontijo.bank_management_api.core.BankManagementMessageSource;
import com.fabiogontijo.bank_management_api.core.exception.BusinessLogicException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AccountCommand {

	private final AccountRepository repository;

	private final AccountQuery query;

	private final BankManagementMessageSource messageSource;

	public Account create(Account account) {
		repository.findById(account.getAccountNumber()).ifPresent(existingAccount -> {
			throw new BusinessLogicException(
					messageSource.getMessage("account.already.exists", account.getAccountNumber()));
		});
		return repository.save(Account.of(account));
	}

	public void debitBalance(Integer accountNumber, BigDecimal amount) {
		if (!canDebit(accountNumber, amount)) {
			throw new BusinessLogicException(
					messageSource.getMessage("transaction.insufficient.balance", accountNumber));
		}
		repository.debitBalance(accountNumber, amount);
	}

	private boolean canDebit(Integer accountNumber, BigDecimal amount) {
		Account account = query.findById(accountNumber);
		return account.getBalance().compareTo(amount) >= 0;
	}

}

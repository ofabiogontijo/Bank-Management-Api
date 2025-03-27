package com.fabiogontijo.bank_management_api.transaction;

import com.fabiogontijo.bank_management_api.account.Account;
import com.fabiogontijo.bank_management_api.account.AccountCommand;
import com.fabiogontijo.bank_management_api.account.AccountQuery;
import com.fabiogontijo.bank_management_api.transaction.web.TransactionInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TransactionCommand {

	private final TransactionRepository repository;

	private final AccountQuery accountQuery;

	private final AccountCommand accountCommand;

	private final TransactionFeeCalculatorService calculatorService;

	public Transaction create(TransactionInput input) {
		BigDecimal fee = calculatorService.calculateFee(input.getAmount(), input.getPaymentMethod());
		accountCommand.debitBalance(input.getAccount(), fee);
		Account account = accountQuery.findById(input.getAccount());
		return repository.save(Transaction.of(input, account));
	}

}

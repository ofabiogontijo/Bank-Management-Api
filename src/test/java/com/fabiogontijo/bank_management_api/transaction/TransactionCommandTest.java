package com.fabiogontijo.bank_management_api.transaction;

import br.com.six2six.fixturefactory.Fixture;
import com.fabiogontijo.bank_management_api.account.Account;
import com.fabiogontijo.bank_management_api.account.AccountCommand;
import com.fabiogontijo.bank_management_api.account.AccountQuery;
import com.fabiogontijo.bank_management_api.support.FixtureTemplates;
import com.fabiogontijo.bank_management_api.support.TestSupport;
import com.fabiogontijo.bank_management_api.transaction.web.TransactionInput;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class TransactionCommandTest extends TestSupport {

	private TransactionCommand command;

	@Mock
	private TransactionRepository repository;

	@Mock
	private AccountQuery accountQuery;

	@Mock
	private AccountCommand accountCommand;

	@Mock
	private TransactionFeeCalculatorService calculatorService;

	@Override
	public void init() {
		this.command = new TransactionCommand(repository, accountQuery, accountCommand, calculatorService);
	}

	@Test
	void should_create_transaction_successfully() {
		TransactionInput input = Fixture.from(TransactionInput.class).gimme(FixtureTemplates.VALID.name());
		Transaction expectedTransaction = Fixture.from(Transaction.class).gimme(FixtureTemplates.VALID.name());
		Account account = Fixture.from(Account.class).gimme(FixtureTemplates.VALID.name());

		BigDecimal fee = new BigDecimal("50.00");
		when(calculatorService.calculateFee(input.getAmount(), input.getPaymentMethod())).thenReturn(fee);
		when(accountQuery.findById(input.getAccount())).thenReturn(account);
		when(repository.save(any())).thenReturn(expectedTransaction);

		Transaction result = command.create(input);

		assertEquals(expectedTransaction.getAccount().getAccountNumber(), result.getAccount().getAccountNumber());
		assertEquals(expectedTransaction.getAmount(), result.getAmount());
		assertEquals(expectedTransaction.getPaymentMethod(), result.getPaymentMethod());

		InOrder inOrder = this.inOrder(calculatorService, accountQuery, accountCommand, repository);
		inOrder.verify(calculatorService).calculateFee(input.getAmount(), input.getPaymentMethod());
		inOrder.verify(accountQuery).findById(input.getAccount());
		inOrder.verify(repository).save(any());
		inOrder.verifyNoMoreInteractions();
	}

}
package com.fabiogontijo.bank_management_api.account;

import br.com.six2six.fixturefactory.Fixture;
import com.fabiogontijo.bank_management_api.core.BankManagementMessageSource;
import com.fabiogontijo.bank_management_api.core.exception.BusinessLogicException;
import com.fabiogontijo.bank_management_api.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static com.fabiogontijo.bank_management_api.support.FixtureTemplates.VALID;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class AccountCommandTest extends TestSupport {

	private AccountCommand command;

	@Mock
	private AccountRepository repository;

	@Mock
	private AccountQuery query;

	@Mock
	private BankManagementMessageSource messageSource;

	@Override
	public void init() {
		this.command = new AccountCommand(repository, query, messageSource);
	}

	@Test
	void should_create_account_successful() {
		Account account = Fixture.from(Account.class).gimme(VALID.name());

		when(repository.findById(any())).thenReturn(Optional.empty());
		when(repository.save(any())).thenReturn(account);

		assertEquals(account, command.create(account));

		InOrder inOrder = this.inOrder(repository);
		inOrder.verify(repository).findById(any());
		inOrder.verify(repository).save(any());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_throw_exception_when_account_already_exists() {
		Account account = Fixture.from(Account.class).gimme(VALID.name());

		when(repository.findById(account.getAccountNumber())).thenReturn(Optional.ofNullable(account));

		assertThrows(BusinessLogicException.class, () -> command.create(account));

		InOrder inOrder = this.inOrder(repository);
		inOrder.verify(repository).findById(account.getAccountNumber());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_throw_exception_when_insufficient_balance() {
		BigDecimal amount = new BigDecimal("500.00");
		Account account = Fixture.from(Account.class).gimme(VALID.name());

		when(query.findById(account.getAccountNumber())).thenReturn(account);

		assertThrows(BusinessLogicException.class, () -> command.debitBalance(account.getAccountNumber(), amount));

		InOrder inOrder = this.inOrder(query);
		inOrder.verify(query).findById(account.getAccountNumber());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_debit_balance_successfully() {
		BigDecimal amount = new BigDecimal("50.00");
		Account account = Fixture.from(Account.class).gimme(VALID.name());

		when(query.findById(account.getAccountNumber())).thenReturn(account);

		command.debitBalance(account.getAccountNumber(), amount);

		InOrder inOrder = this.inOrder(query);
		inOrder.verify(query).findById(account.getAccountNumber());
		inOrder.verifyNoMoreInteractions();
	}

}
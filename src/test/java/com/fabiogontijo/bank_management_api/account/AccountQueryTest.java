package com.fabiogontijo.bank_management_api.account;

import br.com.six2six.fixturefactory.Fixture;
import com.fabiogontijo.bank_management_api.core.BankManagementMessageSource;
import com.fabiogontijo.bank_management_api.core.exception.ResourceNotFoundException;
import com.fabiogontijo.bank_management_api.support.FixtureTemplates;
import com.fabiogontijo.bank_management_api.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class AccountQueryTest extends TestSupport {

	private AccountQuery query;

	@Mock
	private AccountRepository repository;

	@Mock
	private BankManagementMessageSource messageSource;

	@Override
	public void init() {
		this.query = new AccountQuery(repository, messageSource);
	}

	@Test
	void should_retrieve_account_for_id() {
		Account account = Fixture.from(Account.class).gimme(FixtureTemplates.VALID.name());

		when(repository.findById(any())).thenReturn(Optional.ofNullable(account));

		assertEquals(account, query.findById(any()));

		InOrder inOrder = this.inOrder(repository);
		inOrder.verify(repository).findById(any());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_throw_resource_not_found_exception_when_not_find_account() {
		when(repository.findById(any())).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> query.findById(any()));

		InOrder inOrder = this.inOrder(repository);
		inOrder.verify(repository).findById(any());
		inOrder.verifyNoMoreInteractions();
	}

}
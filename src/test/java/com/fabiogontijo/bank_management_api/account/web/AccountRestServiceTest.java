package com.fabiogontijo.bank_management_api.account.web;

import br.com.six2six.fixturefactory.Fixture;
import com.fabiogontijo.bank_management_api.account.Account;
import com.fabiogontijo.bank_management_api.account.AccountCommand;
import com.fabiogontijo.bank_management_api.account.AccountQuery;
import com.fabiogontijo.bank_management_api.support.FixtureTemplates;
import com.fabiogontijo.bank_management_api.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class AccountRestServiceTest extends TestSupport {

	private AccountRestService restService;

	@Mock
	private AccountCommand command;

	@Mock
	private AccountQuery query;

	@Override
	public void init() {
		this.restService = new AccountRestService(command, query);
	}

	@Test
	void should_create_account_successful() {
		Account account = Fixture.from(Account.class).gimme(FixtureTemplates.VALID.name());

		AccountDTO expectedAccountDTO = AccountDTO.of(account.getAccountNumber(), account.getBalance());

		when(command.create(account)).thenReturn(account);

		AccountDTO result = restService.create(account);
		assertEquals(expectedAccountDTO.getAccountNumber(), result.getAccountNumber());
		assertEquals(expectedAccountDTO.getBalance(), result.getBalance());

		InOrder inOrder = this.inOrder(command);
		inOrder.verify(command).create(account);
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_retrieve_account_for_id() {
		Account account = Fixture.from(Account.class).gimme(FixtureTemplates.VALID.name());

		AccountDTO expectedAccountDTO = AccountDTO.of(account.getAccountNumber(), account.getBalance());

		when(query.findById(any())).thenReturn(account);

		assertEquals(expectedAccountDTO, restService.findById(any()));

		InOrder inOrder = this.inOrder(query);
		inOrder.verify(query).findById(any());
		inOrder.verifyNoMoreInteractions();
	}

}
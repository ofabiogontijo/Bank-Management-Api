package com.fabiogontijo.bank_management_api.transaction.web;

import br.com.six2six.fixturefactory.Fixture;
import com.fabiogontijo.bank_management_api.support.FixtureTemplates;
import com.fabiogontijo.bank_management_api.support.TestSupport;
import com.fabiogontijo.bank_management_api.transaction.Transaction;
import com.fabiogontijo.bank_management_api.transaction.TransactionCommand;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

class TransactionRestServiceTest extends TestSupport {

	private TransactionRestService restService;

	@Mock
	private TransactionCommand command;

	@Override
	public void init() {
		this.restService = new TransactionRestService(command);
	}

	@Test
	void should_create_transaction_successfully() {
		TransactionInput input = Fixture.from(TransactionInput.class).gimme(FixtureTemplates.VALID.name());
		Transaction transaction = Fixture.from(Transaction.class).gimme(FixtureTemplates.VALID.name());

		TransactionDTO expectedTransactionDTO = TransactionDTO.of(transaction.getAccount().getAccountNumber(),
				transaction.getAccount().getBalance());

		when(command.create(input)).thenReturn(transaction);

		TransactionDTO result = restService.create(input);
		assertEquals(expectedTransactionDTO.getAccountNumber(), result.getAccountNumber());
		assertEquals(expectedTransactionDTO.getBalance(), result.getBalance());

		InOrder inOrder = this.inOrder(command);
		inOrder.verify(command).create(input);
		inOrder.verifyNoMoreInteractions();
	}

}
package com.fabiogontijo.bank_management_api.transaction;

import br.com.six2six.fixturefactory.Fixture;
import com.fabiogontijo.bank_management_api.core.BeanUtil;
import com.fabiogontijo.bank_management_api.support.FixtureTemplates;
import com.fabiogontijo.bank_management_api.support.TestSupportWithPublisher;
import com.fabiogontijo.bank_management_api.transaction.web.TransactionInput;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class TransactionFeeCalculatorServiceTest extends TestSupportWithPublisher {

	private TransactionFeeCalculatorService calculatorService;

	@Mock
	private TransactionDebitoStrategy debitoStrategy;

	@Mock
	private TransactionCreditoStrategy creditoStrategy;

	@Mock
	private TransactionPixStrategy pixStrategy;

	@Override
	public void init() {
		this.calculatorService = new TransactionFeeCalculatorService();
	}

	@Test
	void should_calculate_fee_for_debito() {
		TransactionInput input = Fixture.from(TransactionInput.class).gimme(FixtureTemplates.VALID.name());

		when(debitoStrategy.calculateFee(input.getAmount())).thenReturn(input.getAmount());
		when(BeanUtil.getBean(TransactionDebitoStrategy.class)).thenReturn(debitoStrategy);

		assertEquals(input.getAmount(),
				calculatorService.calculateFee(input.getAmount(), TransactionPaymentMethodEnum.D));

		InOrder inOrder = this.inOrder(debitoStrategy);
		inOrder.verify(debitoStrategy).calculateFee(input.getAmount());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_calculate_fee_for_credito() {
		TransactionInput input = Fixture.from(TransactionInput.class).gimme(FixtureTemplates.VALID.name());

		when(creditoStrategy.calculateFee(input.getAmount())).thenReturn(input.getAmount());
		when(BeanUtil.getBean(TransactionCreditoStrategy.class)).thenReturn(creditoStrategy);

		assertEquals(input.getAmount(),
				calculatorService.calculateFee(input.getAmount(), TransactionPaymentMethodEnum.C));

		InOrder inOrder = this.inOrder(creditoStrategy);
		inOrder.verify(creditoStrategy).calculateFee(input.getAmount());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_calculate_fee_for_pix() {
		TransactionInput input = Fixture.from(TransactionInput.class).gimme(FixtureTemplates.VALID.name());

		when(pixStrategy.calculateFee(input.getAmount())).thenReturn(input.getAmount());
		when(BeanUtil.getBean(TransactionPixStrategy.class)).thenReturn(pixStrategy);

		assertEquals(input.getAmount(),
				calculatorService.calculateFee(input.getAmount(), TransactionPaymentMethodEnum.P));

		InOrder inOrder = this.inOrder(pixStrategy);
		inOrder.verify(pixStrategy).calculateFee(input.getAmount());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_throw_illegal_argument_exception_when_transaction_type_invalid() {
		TransactionInput input = Fixture.from(TransactionInput.class).gimme(FixtureTemplates.VALID.name());

		assertThrows(IllegalArgumentException.class, () -> {
			calculatorService.calculateFee(input.getAmount(), TransactionPaymentMethodEnum.INVALID);
		});
	}

}
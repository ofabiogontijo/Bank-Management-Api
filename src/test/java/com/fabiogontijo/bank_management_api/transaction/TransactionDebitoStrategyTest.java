package com.fabiogontijo.bank_management_api.transaction;

import com.fabiogontijo.bank_management_api.support.TestSupport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionDebitoStrategyTest extends TestSupport {

	private TransactionDebitoStrategy debitoStrategy;

	@Override
	public void init() {
		this.debitoStrategy = new TransactionDebitoStrategy();
	}

	@Test
	void should_calculate_fee_correctly() {
		BigDecimal amount = new BigDecimal("100.00");
		BigDecimal result = debitoStrategy.calculateFee(amount);

		BigDecimal expected = new BigDecimal("103.00");
		assertEquals(expected, result);
	}

	@Test
	void should_calculate_fee_with_rounding() {
		BigDecimal amount = new BigDecimal("99.99");

		BigDecimal result = debitoStrategy.calculateFee(amount);

		BigDecimal expected = new BigDecimal("102.99");
		assertEquals(expected, result);
	}

}
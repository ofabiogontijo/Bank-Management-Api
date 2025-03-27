package com.fabiogontijo.bank_management_api.transaction;

import com.fabiogontijo.bank_management_api.support.TestSupport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionPixStrategyTest extends TestSupport {

	private TransactionPixStrategy pixStrategy;

	@Override
	public void init() {
		this.pixStrategy = new TransactionPixStrategy();
	}

	@Test
	void should_return_amount_without_fee() {
		BigDecimal amount = new BigDecimal("100.00");
		BigDecimal result = pixStrategy.calculateFee(amount);

		assertEquals(amount, result);
	}

	@Test
	void should_return_zero_when_amount_is_zero() {
		BigDecimal amount = new BigDecimal("0.00");
		BigDecimal result = pixStrategy.calculateFee(amount);

		assertEquals(amount, result);
	}

}
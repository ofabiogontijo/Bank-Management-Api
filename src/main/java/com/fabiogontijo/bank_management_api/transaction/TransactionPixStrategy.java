package com.fabiogontijo.bank_management_api.transaction;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TransactionPixStrategy implements TransactionCalculateFeeStrategy {

	@Override
	public BigDecimal calculateFee(BigDecimal amount) {
		return amount;
	}

}
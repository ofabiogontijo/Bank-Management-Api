package com.fabiogontijo.bank_management_api.transaction;

import com.fabiogontijo.bank_management_api.core.BeanUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionFeeCalculatorService {

	public BigDecimal calculateFee(BigDecimal amount, TransactionPaymentMethodEnum type) {
		TransactionCalculateFeeStrategy strategy = getStrategy(type);
		return strategy.calculateFee(amount);
	}

	private TransactionCalculateFeeStrategy getStrategy(TransactionPaymentMethodEnum type) {
		switch (type) {
		case D:
			return BeanUtil.getBean(TransactionDebitoStrategy.class);
		case C:
			return BeanUtil.getBean(TransactionCreditoStrategy.class);
		case P:
			return BeanUtil.getBean(TransactionPixStrategy.class);
		default:
			throw new IllegalArgumentException("Unsupported transaction type: " + type);
		}
	}

}
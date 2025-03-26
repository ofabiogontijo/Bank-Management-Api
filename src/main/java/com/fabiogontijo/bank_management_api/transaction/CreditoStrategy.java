package com.fabiogontijo.bank_management_api.transaction;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CreditoStrategy implements TransactionStrategy {
    private static final BigDecimal FEE = new BigDecimal("0.05");

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        return amount.add(amount.multiply(FEE)).setScale(2, RoundingMode.HALF_UP);
    }
}

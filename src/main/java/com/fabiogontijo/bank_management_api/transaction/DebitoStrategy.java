package com.fabiogontijo.bank_management_api.transaction;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class DebitoStrategy implements TransactionStrategy {
    private static final BigDecimal TAXA = new BigDecimal("0.03");

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        return amount.add(amount.multiply(TAXA)).setScale(2, RoundingMode.HALF_UP);
    }

}
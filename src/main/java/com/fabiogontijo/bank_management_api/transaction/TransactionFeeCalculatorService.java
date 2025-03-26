package com.fabiogontijo.bank_management_api.transaction;

import com.fabiogontijo.bank_management_api.core.BeanUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
class TransactionFeeCalculatorService {
    public BigDecimal calculateFee(BigDecimal amount, PaymentMethodEnum type) {
        TransactionStrategy strategy = getStrategy(type);
        return strategy.calculateFee(amount);
    }

    private TransactionStrategy getStrategy(PaymentMethodEnum type) {
        switch (type) {
            case D:
                return BeanUtil.getBean(DebitoStrategy.class);
            case C:
                return BeanUtil.getBean(CreditoStrategy.class);
            case P:
                return BeanUtil.getBean(PixStrategy.class);
            default:
                throw new IllegalArgumentException("Unsupported transaction type: " + type);
        }
    }
}
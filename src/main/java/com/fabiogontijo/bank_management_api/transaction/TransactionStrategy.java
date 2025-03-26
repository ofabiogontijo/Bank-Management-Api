package com.fabiogontijo.bank_management_api.transaction;

import java.math.BigDecimal;

interface TransactionStrategy {
    BigDecimal calculateFee(BigDecimal amount);
}

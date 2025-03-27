package com.fabiogontijo.bank_management_api.transaction;

import java.math.BigDecimal;

interface TransactionCalculateFeeStrategy {
    BigDecimal calculateFee(BigDecimal amount);
}

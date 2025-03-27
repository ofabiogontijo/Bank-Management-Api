package com.fabiogontijo.bank_management_api.transaction;

import com.fabiogontijo.bank_management_api.account.Account;
import com.fabiogontijo.bank_management_api.core.auditing.BankManagementAbstractEntity;
import com.fabiogontijo.bank_management_api.transaction.web.TransactionInput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.fabiogontijo.bank_management_api.core.IdGenerator.generateId;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class Transaction extends BankManagementAbstractEntity<Transaction> {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private TransactionPaymentMethodEnum paymentMethod;

    private BigDecimal amount;

    static Transaction of(TransactionInput input, Account account) {
        return new Transaction(generateId(), account, input.getPaymentMethod(), input.getAmount());
    }

}

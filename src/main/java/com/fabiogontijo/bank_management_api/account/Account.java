package com.fabiogontijo.bank_management_api.account;

import com.fabiogontijo.bank_management_api.core.auditing.BankManagementAbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class Account extends BankManagementAbstractEntity<Account>{

    @Id
    @JsonProperty("numero_conta")
    @Column(name = "account_number")
    private Integer accountNumber;

    @JsonProperty("saldo")
    private BigDecimal balance = ZERO;

    static Account of(Account account) {
        return new Account(account.getAccountNumber(), account.getBalance());
    }

}

package com.fabiogontijo.bank_management_api.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AccountCommand {

    private final AccountRepository repository;

    public Account create(Account account) {
        return repository.save(Account.of(account));
    }

    public void debitBalance(Integer account, BigDecimal amount){
        repository.debitBalance(account, amount);
    }

}

package com.fabiogontijo.bank_management_api.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AccountCommand {

    private final AccountRepository repository;

    public Account create(Account account) {
        return repository.save(Account.of(account));
    }

}

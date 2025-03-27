package com.fabiogontijo.bank_management_api.account;

import com.fabiogontijo.bank_management_api.core.BankManagementMessageSource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AccountCommand {

    private final AccountRepository repository;

    private final BankManagementMessageSource messageSource;

    public Account create(Account account) {
        repository.findById(account.getAccountNumber()).ifPresent(existingAccount -> {
            throw new IllegalArgumentException(
                    messageSource.getMessage("account.already.exists", new Object[]{account.getAccountNumber()})
            );
        });
        return repository.save(Account.of(account));
    }

    public void debitBalance(Integer account, BigDecimal amount){
        repository.debitBalance(account, amount);
    }

}

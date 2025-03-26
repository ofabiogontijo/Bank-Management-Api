package com.fabiogontijo.bank_management_api.account;

import com.fabiogontijo.bank_management_api.core.BankManagementMessageSource;
import com.fabiogontijo.bank_management_api.core.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AccountQuery {

    private final AccountRepository repository;

    private final BankManagementMessageSource messageSource;

    public Account findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("account.not.found"), true));
    }

}

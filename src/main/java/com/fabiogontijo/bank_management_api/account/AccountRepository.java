package com.fabiogontijo.bank_management_api.account;

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<Account, Integer> {
}

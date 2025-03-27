package com.fabiogontijo.bank_management_api.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}

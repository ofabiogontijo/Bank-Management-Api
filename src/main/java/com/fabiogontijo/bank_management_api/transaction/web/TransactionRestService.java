package com.fabiogontijo.bank_management_api.transaction.web;

import com.fabiogontijo.bank_management_api.transaction.Transaction;
import com.fabiogontijo.bank_management_api.transaction.TransactionCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/transacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionRestService {

	private final TransactionCommand command;

	@PostMapping
	@ResponseStatus(CREATED)
	TransactionDTO create(@Valid @RequestBody TransactionInput input) {
		Transaction transaction = command.create(input);
		return TransactionDTO.of(transaction.getAccount().getAccountNumber(), transaction.getAccount().getBalance());
	}

}

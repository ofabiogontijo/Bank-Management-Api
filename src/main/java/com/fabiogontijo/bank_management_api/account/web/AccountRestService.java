package com.fabiogontijo.bank_management_api.account.web;

import com.fabiogontijo.bank_management_api.account.Account;
import com.fabiogontijo.bank_management_api.account.AccountCommand;
import com.fabiogontijo.bank_management_api.account.AccountQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/conta", produces = MediaType.APPLICATION_JSON_VALUE)
class AccountRestService {

	private final AccountCommand command;

	private final AccountQuery query;

	@PostMapping
	@ResponseStatus(CREATED)
	AccountDTO create(@Valid @RequestBody Account account) {
		account = command.create(account);
		return AccountDTO.of(account.getAccountNumber(), account.getBalance());
	}

	@GetMapping
	@ResponseStatus(OK)
	AccountDTO findById(@RequestParam(required = false) Integer id) {
		Account account = query.findById(id);
		return AccountDTO.of(account.getAccountNumber(), account.getBalance());
	}

}

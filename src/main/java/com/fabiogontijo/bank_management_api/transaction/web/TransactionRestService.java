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
    Transaction create(@Valid @RequestBody TransactionInput input) {
        return command.create(input);
    }

}

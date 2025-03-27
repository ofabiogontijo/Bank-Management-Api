package com.fabiogontijo.bank_management_api.core;

import lombok.NoArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class IdGenerator {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

}

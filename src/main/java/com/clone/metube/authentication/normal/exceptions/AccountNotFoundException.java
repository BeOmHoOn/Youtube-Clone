package com.clone.metube.authentication.normal.exceptions;

import com.clone.metube.global.exceptions.AccountException;

public class AccountNotFoundException extends AccountException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

package com.clone.metube.global.exceptions;

import com.clone.metube.global.enums.AccountError;

public class AccountNotFoundException extends AccountException {
    public AccountNotFoundException() {
        super(AccountError.ACCOUNT_NOT_FOUND);
    }
}
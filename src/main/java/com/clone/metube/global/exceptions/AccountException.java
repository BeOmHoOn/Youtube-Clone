package com.clone.metube.global.exceptions;

import com.clone.metube.global.enums.AccountError;
import lombok.Getter;

@Getter
public class AccountException extends RuntimeException {
    protected AccountError error;

    public AccountException(AccountError error) {
        super(error.getMessage());
        this.error = error;
    }
}

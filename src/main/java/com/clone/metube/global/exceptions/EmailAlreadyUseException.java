package com.clone.metube.global.exceptions;

import com.clone.metube.global.enums.AccountError;

public class EmailAlreadyUseException extends AccountException {
    public EmailAlreadyUseException() {
        super(AccountError.EMAIL_ALREADY_USE);
    }
}

package com.clone.metube.global.exceptions;

import com.clone.metube.global.enums.AccountError;

public class AccessTokenExpiredException extends AccountException {
    public AccessTokenExpiredException() {super(AccountError.ACCESS_TOKEN_EXPIRED);}
}

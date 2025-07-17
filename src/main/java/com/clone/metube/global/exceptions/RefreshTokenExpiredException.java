package com.clone.metube.global.exceptions;

import com.clone.metube.global.enums.AccountError;

public class RefreshTokenExpiredException extends AccountException {
    public RefreshTokenExpiredException() {
        super(AccountError.REFRESH_TOKEN_EXPIRED);
    }
}

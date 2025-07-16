package com.clone.metube.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AccountError {
    EMPTY_ID_OR_PW(HttpStatus.BAD_REQUEST, "AUTH_001", "계정 혹은 비밀번호는 공백일 수 없습니다."),
    ACCOUNT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH_002", "계정 혹은 비밀번호가 잘못 입력되었습니다."),
    OAUTH2_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "AUTH_003", "소셜 로그인에 실패하였습니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "AUTH_004", "로그인정보가 만료되었습니다.");

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}

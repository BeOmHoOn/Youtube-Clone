package com.clone.metube.global.enums;

public enum ExceptionMessage {
    ACCOUNT_NOT_FOUND("계정 혹은 비밀번호가 잘못 입력되었습니다.");


    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

package com.clone.metube.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ServerError {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown", "확인되지 않은 서버오류가 발생했습니다.");

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}

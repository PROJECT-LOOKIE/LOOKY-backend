package org.example.lookie.common.exception.user;

import lombok.Getter;
import org.example.lookie.common.exception.BaseErrorCode;

@Getter
public class UserCustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    private final Throwable cause;

    public UserCustomException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
        this.cause = null;
    }

    public UserCustomException(BaseErrorCode errorCode, Throwable cause) {
        this.errorCode = errorCode;
        this.cause = cause;
    }
}
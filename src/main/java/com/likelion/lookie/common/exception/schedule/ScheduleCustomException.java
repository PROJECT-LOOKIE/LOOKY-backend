package com.likelion.lookie.common.exception.schedule;

import com.likelion.lookie.common.exception.BaseErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ScheduleCustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public ScheduleCustomException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
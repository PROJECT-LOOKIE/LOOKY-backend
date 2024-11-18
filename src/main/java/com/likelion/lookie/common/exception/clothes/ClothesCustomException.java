package com.likelion.lookie.common.exception.clothes;

import com.likelion.lookie.common.exception.BaseErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClothesCustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public ClothesCustomException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
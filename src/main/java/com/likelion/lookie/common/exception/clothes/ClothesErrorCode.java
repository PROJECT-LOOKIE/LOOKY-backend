package com.likelion.lookie.common.exception.clothes;

import com.likelion.lookie.common.exception.ApiResponse;
import com.likelion.lookie.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ClothesErrorCode implements BaseErrorCode {

    NO_CLOTHES_INFO(HttpStatus.BAD_REQUEST, "400", "해당 옷이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}
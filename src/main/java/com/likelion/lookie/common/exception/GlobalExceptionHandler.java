package com.likelion.lookie.common.exception;


import com.likelion.lookie.common.exception.schedule.ScheduleCustomException;
import com.likelion.lookie.common.exception.user.UserCustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ScheduleCustomException.class)
    public ResponseEntity<ApplicationResponse<Void>> handleScheduleCustomException(ScheduleCustomException ex) {
        BaseErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApplicationResponse.badRequest(null, errorCode.getMessage()));
    }

    @ExceptionHandler(UserCustomException.class)
    public ResponseEntity<ApplicationResponse<Void>> handleUserCustomException(UserCustomException ex) {
        BaseErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApplicationResponse.badRequest(null, errorCode.getMessage()));
    }
}
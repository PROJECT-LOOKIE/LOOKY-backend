package com.likelion.lookie.common.exception.schedule;

import com.likelion.lookie.common.exception.ApiResponse;
import com.likelion.lookie.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ScheduleErrorCode implements BaseErrorCode {

    NO_SCHEDULE_INFO(HttpStatus.BAD_REQUEST, "400", "약속이 존재하지 않습니다."),
    DUPLICATE_INVITATION(HttpStatus.BAD_REQUEST, "400", "이미 초대된 사용자입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}

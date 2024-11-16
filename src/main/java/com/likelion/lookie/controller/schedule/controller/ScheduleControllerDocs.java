package com.likelion.lookie.controller.schedule.controller;


import com.likelion.lookie.common.exception.ApplicationResponse;
import com.likelion.lookie.controller.schedule.dto.CreateScheduleRequestDto;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Schedule", description = "Schedule API")
public interface ScheduleControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "메인-약속 생성 페이지", description = "약속 생성 API")
    ApplicationResponse<String> createSchedule(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO,
            @RequestBody CreateScheduleRequestDto registerScheduleRequestDto);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "메인-약속 초대", description = "약속 초대 API")
    ApplicationResponse<String> inviteSchedule(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO,
            @PathVariable("schedule_id") Long scheduleId);
}

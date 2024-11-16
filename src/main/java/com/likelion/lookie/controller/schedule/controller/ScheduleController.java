package com.likelion.lookie.controller.schedule.controller;


import com.likelion.lookie.common.exception.ApplicationResponse;
import com.likelion.lookie.controller.schedule.dto.CreateScheduleRequestDto;
import com.likelion.lookie.controller.schedule.dto.GetScheduleInfoDto;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import com.likelion.lookie.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController implements ScheduleControllerDocs {

    private final ScheduleService scheduleService;

    @PostMapping
    public ApplicationResponse<String> createSchedule(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO,
            @RequestBody CreateScheduleRequestDto registerScheduleRequestDto
    ) {
        return ApplicationResponse.ok(scheduleService.createSchedule(userInfoDTO.email(), registerScheduleRequestDto));
    }

    @PostMapping("/invite/{schedule_id}")
    public ApplicationResponse<String> inviteSchedule(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO,
            @PathVariable("schedule_id") Long scheduleId
    ) {
        return ApplicationResponse.ok(scheduleService.inviteSchedule(userInfoDTO.email(), scheduleId));
    }

    @GetMapping("/{schedule_id}")
    public ApplicationResponse<GetScheduleInfoDto> getSchedule(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO,
            @PathVariable("schedule_id") Long scheduleId
    ) {
        return ApplicationResponse.ok(scheduleService.getScheduleInfo(userInfoDTO.name(), scheduleId));
    }

}

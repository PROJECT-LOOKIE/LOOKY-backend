package com.likelion.lookie.controller.schedule.controller;


import com.likelion.lookie.common.exception.ApplicationResponse;
import com.likelion.lookie.controller.schedule.dto.CreateScheduleRequestDto;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import com.likelion.lookie.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.likelion.lookie.controller.look.controller;

import com.likelion.lookie.common.exception.ApplicationResponse;
import com.likelion.lookie.controller.look.dto.CreateLookRequestDto;
import com.likelion.lookie.controller.look.dto.GetLookResponseDto;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import com.likelion.lookie.service.look.LookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/look")
public class LookController {

    private final LookService lookService;

    @PostMapping
    public ApplicationResponse<String> createLook(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO,
            @RequestBody CreateLookRequestDto requestDto
    ) {
        return ApplicationResponse.ok(lookService.createLook(userInfoDTO.email(), requestDto));
    }

    @GetMapping("/{schedule_id}")
    public ApplicationResponse<List<GetLookResponseDto>> getLook(
            @PathVariable Long schedule_id
    ) {
        return ApplicationResponse.ok(lookService.getLook(schedule_id));
    }
}

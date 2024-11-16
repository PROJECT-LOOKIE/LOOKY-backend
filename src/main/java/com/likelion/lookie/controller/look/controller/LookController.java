package com.likelion.lookie.controller.look.controller;

import com.likelion.lookie.common.exception.ApplicationResponse;
import com.likelion.lookie.controller.look.dto.CreateLookRequestDto;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import com.likelion.lookie.service.look.LookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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


}

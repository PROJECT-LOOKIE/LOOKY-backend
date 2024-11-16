package com.likelion.lookie.controller.user.controller;

import com.likelion.lookie.controller.user.dto.OnboardingRequestDto;
import lombok.RequiredArgsConstructor;
import com.likelion.lookie.common.exception.ApplicationResponse;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import com.likelion.lookie.service.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController implements UserControllerDocs {

    private final UserService userService;

    @GetMapping
    public ApplicationResponse<UserInfoDTO> getUserInfo(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO
    ) {
        return ApplicationResponse.ok(userInfoDTO);
    }

    @PostMapping
    public ApplicationResponse<String> createUser(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO,
            @RequestBody OnboardingRequestDto requestDto
    ) {
        return ApplicationResponse.ok(userService.createUser(userInfoDTO, requestDto));
    }

}

package com.likelion.lookie.controller.user.controller;

import lombok.RequiredArgsConstructor;
import com.likelion.lookie.common.exception.ApplicationResponse;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import com.likelion.lookie.service.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

package org.example.lookie.controller.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.lookie.common.exception.ApplicationResponse;
import org.example.lookie.controller.user.dto.UserInfoDTO;
import org.example.lookie.service.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/user")
public class UserController implements UserControllerDocs {

    private final UserService userService;

    @GetMapping
    public ApplicationResponse<UserInfoDTO> getUserInfo(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO
    ) {
        return ApplicationResponse.ok(userInfoDTO);
    }

}

package com.likelion.lookie.controller.user.controller;

import com.likelion.lookie.controller.user.dto.OnboardingRequestDto;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.likelion.lookie.common.exception.ApplicationResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "User API")
public interface UserControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "사용자 정보 제공", description = "사용자 정보 전달 API")
    ApplicationResponse<UserInfoDTO> getUserInfo(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "온보딩 - 닉네임 설정", description = "닉네임 설정 및 프로필 사진 변경 API")
    ApplicationResponse<String> createUser(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO,
            @RequestBody OnboardingRequestDto requestDto);


}

package org.example.lookie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.lookie.common.oauth.dto.AuthTokens;
import org.example.lookie.common.oauth.dto.KakaoUserInfoResponseDto;
import org.example.lookie.common.oauth.service.KakaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/callback")
    public ResponseEntity<Map<String, String>> callback(@RequestParam("code") String code) throws IOException {

        // 카카오에서 액세스 토큰 가져오기
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        // 카카오에서 사용자 정보 가져오기
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        // 사용자 정보를 서버에 매칭해보고, 없으면 회원가입, 있으면 로그인
        AuthTokens authTokens = kakaoService.getAuthTokens(userInfo);

        Map<String, String> map = new HashMap<>();
        map.put("access_token", authTokens.getAccessToken());
        map.put("refresh_token", authTokens.getRefreshToken());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
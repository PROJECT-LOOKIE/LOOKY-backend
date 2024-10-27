package org.example.lookie.common.oauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.lookie.common.oauth.dto.OidcDecodePayload;
import org.example.lookie.entity.User;
import org.example.lookie.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoLoginService extends OidcUserService {

    private final UserRepository userRepository;
    private final KakaoIdTokenDecodeAdapter kakaoIdTokenDecodeAdapter;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        try {

            // Access Token 가져오기
            String tokenValue = userRequest.getAccessToken().getTokenValue();
            log.debug("Access Token: {}", tokenValue);

            // Access Token에서 유저 정보 디코딩 (Kakao 특화)
            OidcDecodePayload oidcDecodePayload = kakaoIdTokenDecodeAdapter.getPayloadFromAccessToken(tokenValue);
            log.debug("Decoded Payload: {}", oidcDecodePayload);

            // 카카오 계정에서 사용자 정보 매핑 및 DB 저장
            User user = saveOrUpdateUser(oidcDecodePayload);

            return new DefaultOidcUser(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    userRequest.getIdToken()
            );

        } catch (Exception e) {
            log.error("Error loading user from OAuth2 user request: {}", e.getMessage(), e);
            throw new OAuth2AuthenticationException("Error loading user from OAuth2 user request: " + e.getMessage());
        }
    }

    private User saveOrUpdateUser(OidcDecodePayload oidcDecodePayload) {
        return userRepository.findBySubId(oidcDecodePayload.sub())
                .map(existingUser -> {
                    // 업데이트할 정보가 있다면 업데이트
                    existingUser.updateProfile(
                            oidcDecodePayload.nickname(),
                            oidcDecodePayload.email(),
                            oidcDecodePayload.picture()
                    );
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    // 새로운 사용자라면 생성 후 저장
                    User newUser = User.createSocialUser(oidcDecodePayload);
                    return userRepository.save(newUser);
                });
    }
}


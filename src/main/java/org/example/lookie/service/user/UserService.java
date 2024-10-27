package org.example.lookie.service.user;

import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.example.lookie.common.exception.ApplicationResponse;
import org.example.lookie.common.exception.user.UserCustomException;
import org.example.lookie.common.exception.user.UserErrorCode;
import org.example.lookie.controller.user.dto.UserInfoDTO;
import org.example.lookie.entity.User;
import org.example.lookie.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 인증
    public UserInfoDTO loadUserInfoByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        return new UserInfoDTO(user.getName(), user.getEmail(), user.getPicture());
    }


}

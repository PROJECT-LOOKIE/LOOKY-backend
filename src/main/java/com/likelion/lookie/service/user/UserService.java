package com.likelion.lookie.service.user;

import com.likelion.lookie.common.exception.user.UserCustomException;
import com.likelion.lookie.common.exception.user.UserErrorCode;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import com.likelion.lookie.entity.User;
import com.likelion.lookie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

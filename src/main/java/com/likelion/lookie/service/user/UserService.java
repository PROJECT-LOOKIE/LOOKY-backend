package com.likelion.lookie.service.user;

import com.likelion.lookie.common.exception.user.UserCustomException;
import com.likelion.lookie.common.exception.user.UserErrorCode;
import com.likelion.lookie.controller.user.dto.OnboardingRequestDto;
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


    public String createUser(UserInfoDTO userInfoDTO, OnboardingRequestDto requestDto) {

        boolean nicknameExists = userRepository.existsByNickname(requestDto.nickname());
        if (nicknameExists) {
            throw new UserCustomException(UserErrorCode.NICKNAME_ALREADY_EXISTS);
        }

        User user = userRepository.findByEmail(userInfoDTO.email())
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        user.updateNickname(requestDto.nickname());

        if (requestDto.filePath() != null) {
            user.updateFilePath(requestDto.filePath());
        }

        userRepository.save(user);

        return "User information successfully updated.";
    }
}

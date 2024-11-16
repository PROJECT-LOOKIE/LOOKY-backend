package com.likelion.lookie.service.look;

import com.likelion.lookie.common.exception.clothes.ClothesCustomException;
import com.likelion.lookie.common.exception.clothes.ClothesErrorCode;
import com.likelion.lookie.common.exception.schedule.ScheduleCustomException;
import com.likelion.lookie.common.exception.schedule.ScheduleErrorCode;
import com.likelion.lookie.common.exception.user.UserCustomException;
import com.likelion.lookie.common.exception.user.UserErrorCode;
import com.likelion.lookie.controller.look.dto.CreateLookRequestDto;
import com.likelion.lookie.entity.*;
import com.likelion.lookie.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LookService {

    private final UserRepository userRepository;
    private final LookRepository lookRepository;
    private final ScheduleRepository scheduleRepository;
    private final ClothesRepository clothesRepository;
    private final ClothesLookRepository clothesLookRepository;


    public String createLook(String email, CreateLookRequestDto requestDto) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        Schedule schedule = scheduleRepository.findById(requestDto.scheduleId())
                .orElseThrow(() -> new ScheduleCustomException(ScheduleErrorCode.NO_SCHEDULE_INFO));

        Look look = lookRepository.findByUserAndSchedule(user, schedule);

        for (Long clothesId : requestDto.clothesId()) {
            Clothes clothes = clothesRepository.findById(clothesId)
                    .orElseThrow(() -> new ClothesCustomException(ClothesErrorCode.NO_CLOTHES_INFO));

            ClothesLook clothesLook = ClothesLook.builder()
                    .clothes(clothes)
                    .look(look)
                    .build();
            clothesLookRepository.save(clothesLook);
        }

        return "Look successfully created.";
    }
}

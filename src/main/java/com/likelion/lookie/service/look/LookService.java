package com.likelion.lookie.service.look;

import com.likelion.lookie.common.exception.clothes.ClothesCustomException;
import com.likelion.lookie.common.exception.clothes.ClothesErrorCode;
import com.likelion.lookie.common.exception.schedule.ScheduleCustomException;
import com.likelion.lookie.common.exception.schedule.ScheduleErrorCode;
import com.likelion.lookie.common.exception.user.UserCustomException;
import com.likelion.lookie.common.exception.user.UserErrorCode;
import com.likelion.lookie.common.util.FileService;
import com.likelion.lookie.controller.look.dto.CreateLookRequestDto;
import com.likelion.lookie.controller.look.dto.GetLookResponseDto;
import com.likelion.lookie.entity.*;
import com.likelion.lookie.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LookService {

    private final UserRepository userRepository;
    private final LookRepository lookRepository;
    private final ScheduleRepository scheduleRepository;
    private final ClothesRepository clothesRepository;
    private final ClothesLookRepository clothesLookRepository;

    private final FileService fileService;


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

    public List<GetLookResponseDto> getLook(Long scheduleId) {
        List<Look> looks = lookRepository.findAllByScheduleId(scheduleId);

        List<GetLookResponseDto> responseDtos = new ArrayList<>();
        for (Look look : looks) {
            List<ClothesLook> clothesLooks = clothesLookRepository.findAllByLook(look);
            List<String> clothesImages = new ArrayList<>();

            for (ClothesLook clothesLook : clothesLooks) {
                String presignedUrl = fileService.getDownloadPresignedUrl(clothesLook.getClothes().getImageUrl());
                clothesImages.add(presignedUrl);
            }

            GetLookResponseDto getLookResponseDto = GetLookResponseDto.builder()
                    .lookId(look.getId())
                    .name(look.getUser().getName())
                    .clothesImages(clothesImages)
                    .build();

            responseDtos.add(getLookResponseDto);
        }

        return responseDtos;
    }

    public String deleteLook(Long lookId) {
        clothesLookRepository.deleteAllByLookId(lookId);
        lookRepository.deleteById(lookId);

        return "Look successfully deleted.";
    }
}

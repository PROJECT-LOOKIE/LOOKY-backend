package com.likelion.lookie.service.look;

import com.likelion.lookie.common.exception.clothes.ClothesCustomException;
import com.likelion.lookie.common.exception.clothes.ClothesErrorCode;
import com.likelion.lookie.common.exception.schedule.ScheduleCustomException;
import com.likelion.lookie.common.exception.schedule.ScheduleErrorCode;
import com.likelion.lookie.common.exception.user.UserCustomException;
import com.likelion.lookie.common.exception.user.UserErrorCode;
import com.likelion.lookie.common.util.FileService;
import com.likelion.lookie.controller.look.dto.CreateLookRequestDto;
import com.likelion.lookie.controller.look.dto.GetLookResponseDetailDto;
import com.likelion.lookie.controller.look.dto.GetLookResponseDto;
import com.likelion.lookie.controller.look.dto.CreateLookRequestDetailDto;
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

        for (CreateLookRequestDetailDto detailDto : requestDto.clothes()) {
            Clothes clothes = clothesRepository.findById(detailDto.clothesId())
                    .orElseThrow(() -> new ClothesCustomException(ClothesErrorCode.NO_CLOTHES_INFO));

            ClothesLook clothesLook = ClothesLook.builder()
                    .clothes(clothes)
                    .look(look)
                    .x(detailDto.x())
                    .y(detailDto.y())
                    .size(detailDto.size())
                    .build();
            clothesLookRepository.save(clothesLook);
        }

        return "Look successfully created.";
    }

    public List<GetLookResponseDto> getLook(Long scheduleId) {

        // 스케줄의 룩들을 전체 저장
        List<Look> looks = lookRepository.findAllByScheduleId(scheduleId);

        List<GetLookResponseDto> responseDtos = new ArrayList<>();

        // 각각의 룩들을 돌면서
        for (Look look : looks) {

            // 개별 옷들 조회
            List<ClothesLook> clothesLooks = clothesLookRepository.findAllByLook(look);
            List<GetLookResponseDetailDto> detailDtos = new ArrayList<>();

            for (ClothesLook clothesLook : clothesLooks) {
                String presignedUrl = fileService.getDownloadPresignedUrl(clothesLook.getClothes().getImageUrl());
                GetLookResponseDetailDto detailDto = GetLookResponseDetailDto.builder()
                        .clothesImage(presignedUrl)
                        .x(clothesLook.getX())
                        .y(clothesLook.getY())
                        .size(clothesLook.getSize())
                        .build();
            }

            GetLookResponseDto getLookResponseDto = GetLookResponseDto.builder()
                    .lookId(look.getId())
                    .name(look.getUser().getName())
                    .clothes(detailDtos)
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

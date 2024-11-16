package com.likelion.lookie.service.schedule;

import com.likelion.lookie.common.exception.schedule.ScheduleCustomException;
import com.likelion.lookie.common.exception.schedule.ScheduleErrorCode;
import com.likelion.lookie.common.exception.user.UserCustomException;
import com.likelion.lookie.common.exception.user.UserErrorCode;
import com.likelion.lookie.controller.schedule.dto.CreateScheduleRequestDto;
import com.likelion.lookie.entity.Look;
import com.likelion.lookie.entity.Schedule;
import com.likelion.lookie.entity.User;
import com.likelion.lookie.repository.LookRepository;
import com.likelion.lookie.repository.ScheduleRepository;
import com.likelion.lookie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final LookRepository lookRepository;
    private final ScheduleRepository scheduleRepository;

    public String createSchedule(String userEmail, CreateScheduleRequestDto requestDto) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        LocalDate date = LocalDate.parse(requestDto.date(), DateTimeFormatter.ISO_DATE);

        Schedule schedule = Schedule.builder()
                .name(requestDto.name())
                .date(date)
                .location(requestDto.location())
                .atmosphere(requestDto.atmosphere())
                .decoration(requestDto.decoration())
                .people(requestDto.people())
                .build();

        scheduleRepository.save(schedule);

        Look look = Look.builder()
                .user(user)
                .schedule(schedule)
                .build();

        lookRepository.save(look);

        return "Schedule successfully created.";
    }

    public String inviteSchedule(String userEmail, Long scheduleId) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleCustomException(ScheduleErrorCode.NO_SCHEDULE_INFO));

        // 이미 존재하는 Look인지 확인
        boolean lookExists = lookRepository.existsByUserAndSchedule(user, schedule);
        if (lookExists) {
            throw new ScheduleCustomException(ScheduleErrorCode.DUPLICATE_INVITATION);
        }

        Look look = Look.builder()
                .user(user)
                .schedule(schedule)
                .build();

        lookRepository.save(look);

        return "User successfully invited to the schedule";
    }
}

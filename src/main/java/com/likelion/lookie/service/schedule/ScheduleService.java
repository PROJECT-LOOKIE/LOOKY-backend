package com.likelion.lookie.service.schedule;

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

}

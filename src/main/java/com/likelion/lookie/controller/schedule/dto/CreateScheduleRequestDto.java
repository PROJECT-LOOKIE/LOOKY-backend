package com.likelion.lookie.controller.schedule.dto;

public record CreateScheduleRequestDto(
        String emoji,
        String name,
        String date,
        String location,
        String atmosphere,
        int decoration
) {
}

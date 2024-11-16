package com.likelion.lookie.controller.schedule.dto;

public record CreateScheduleRequestDto(
        String name,
        String date,
        String location,
        String atmosphere,
        int decoration,
        int people
) {
}

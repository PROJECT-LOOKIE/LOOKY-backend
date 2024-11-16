package com.likelion.lookie.controller.schedule.dto;

import lombok.Builder;

@Builder
public record GetScheduleInfoDto(
        Long id,
        String name,
        String location,
        String userName,
        int people
) {
}

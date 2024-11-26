package com.likelion.lookie.controller.schedule.dto;

import lombok.Builder;

@Builder
public record GetScheduleInfoDto(
        Long id,
        String emoji,
        String name,
        String location,
        String atmosphere,
        String userName,
        int people
) {
}

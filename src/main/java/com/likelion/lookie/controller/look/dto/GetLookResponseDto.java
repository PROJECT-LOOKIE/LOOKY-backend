package com.likelion.lookie.controller.look.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GetLookResponseDto(
        Long lookId,
        String name,
        List<GetLookResponseDetailDto> clothes
) {
}

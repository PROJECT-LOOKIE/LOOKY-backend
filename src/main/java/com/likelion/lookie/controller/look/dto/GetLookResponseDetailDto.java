package com.likelion.lookie.controller.look.dto;

import lombok.Builder;

@Builder
public record GetLookResponseDetailDto(
        String clothesImage,
        double x,
        double y,
        double size
) {
}

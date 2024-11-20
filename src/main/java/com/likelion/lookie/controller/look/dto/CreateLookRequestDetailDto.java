package com.likelion.lookie.controller.look.dto;

public record CreateLookRequestDetailDto(
        Long clothesId,
        double x,
        double y,
        double size
) {
}

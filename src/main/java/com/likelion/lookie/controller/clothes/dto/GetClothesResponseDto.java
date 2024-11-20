package com.likelion.lookie.controller.clothes.dto;

public record GetClothesResponseDto(
        Long id,
        String brand,
        String category,
        Integer price
) {
}

package com.likelion.lookie.controller.clothes.dto;

public record CreateClothesRequestDto(
        Long userId,
        String brand,
        String category,
        Integer price,
        String imageUrl
) {
}

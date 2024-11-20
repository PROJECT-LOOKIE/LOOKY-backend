package com.likelion.lookie.controller.clothes.dto;

public record CreateClothesRequestDto(
        String brand,
        String category,
        Integer price,
        String imageUrl
) {
}

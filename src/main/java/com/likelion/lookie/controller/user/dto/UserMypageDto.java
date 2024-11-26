package com.likelion.lookie.controller.user.dto;

import lombok.Builder;

@Builder
public record UserMypageDto(
        String nickname,
        String email,
        String imageUrl
) {
}

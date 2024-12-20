package com.likelion.lookie.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "프리사인드 URL 요청 DTO")
public class PresignedUrlRequest {

    private final String prefix;

    private final String fileName;

}
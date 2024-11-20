package com.likelion.lookie.controller.look.dto;

import java.util.List;

public record CreateLookRequestDto(
        Long scheduleId,
        List<CreateLookRequestDetailDto> clothes
) {
}

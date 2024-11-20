package com.likelion.lookie.controller.clothes.controller;


import com.likelion.lookie.common.exception.ApplicationResponse;
import com.likelion.lookie.controller.clothes.dto.CreateClothesRequestDto;
import com.likelion.lookie.controller.clothes.dto.GetClothesResponseDto;
import com.likelion.lookie.controller.user.dto.UserInfoDTO;
import com.likelion.lookie.service.clothes.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/closet/item")
@RequiredArgsConstructor
public class ClothesController implements ClothesControllerDocs{

    private final ClothesService clothesService;

    @GetMapping
    public ApplicationResponse<List<GetClothesResponseDto>> getAllClothes(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO
            ) {
        return ApplicationResponse.ok(clothesService.getAllClothes(userInfoDTO.email()));
    }

    @PostMapping
    public ApplicationResponse<GetClothesResponseDto> addClothes(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO,
            @RequestBody CreateClothesRequestDto request
    ) {
        return ApplicationResponse.ok(clothesService.addClothes(userInfoDTO.email(), request));
    }

    @GetMapping("/{item_id}")
    public ApplicationResponse<GetClothesResponseDto> getClothesById(@PathVariable Long item_id) {
        return ApplicationResponse.ok(clothesService.getClothesById(item_id));
    }

    @DeleteMapping("/{item_id}")
    public ApplicationResponse<String> deleteClothes(@PathVariable Long item_id) {
        return ApplicationResponse.ok(clothesService.deleteClothes(item_id));
    }
}
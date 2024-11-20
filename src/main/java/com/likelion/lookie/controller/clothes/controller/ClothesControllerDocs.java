package com.likelion.lookie.controller.clothes.controller;


import com.likelion.lookie.controller.clothes.dto.CreateClothesRequestDto;
import com.likelion.lookie.controller.clothes.dto.GetClothesResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Tag(name = "Clothes", description = "Clothes API")
public interface ClothesControllerDocs {

    @Operation(summary = "옷 목록 조회", description = "전체 옷 목록을 조회하는 API")
    ResponseEntity<List<GetClothesResponseDto>> getAllClothes();

    @Operation(summary = "옷 추가", description = "새로운 옷을 추가하는 API")
    ResponseEntity<GetClothesResponseDto> addClothes(@RequestBody CreateClothesRequestDto requestDto);

    @Operation(summary = "옷 상세 조회", description = "특정 ID를 가진 옷의 정보를 조회하는 API")
    ResponseEntity<GetClothesResponseDto> getClothesById(@PathVariable Long item_id);

    @Operation(summary = "옷 삭제", description = "특정 ID를 가진 옷을 삭제하는 API")
    ResponseEntity<Void> deleteClothes(@PathVariable Long item_id);
}

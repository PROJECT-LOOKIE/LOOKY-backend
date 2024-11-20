package com.likelion.lookie.controller.clothes.controller;


import com.likelion.lookie.controller.clothes.dto.CreateClothesRequestDto;
import com.likelion.lookie.controller.clothes.dto.GetClothesResponseDto;
import com.likelion.lookie.service.clothes.ClothesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Clothes", description = "Clothes API")
@RestController
@RequestMapping("/api/v1/closet/item")
public class ClothesController implements ClothesControllerDocs{
    private final ClothesService clothesService;

    public ClothesController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }

    @GetMapping
    public ResponseEntity<List<GetClothesResponseDto>> getAllClothes() {
        return ResponseEntity.ok(clothesService.getAllClothes());
    }

    @PostMapping
    public ResponseEntity<GetClothesResponseDto> addClothes(@RequestBody CreateClothesRequestDto request) {
        return new ResponseEntity<>(clothesService.addClothes(request), HttpStatus.CREATED);
    }

    @GetMapping("/{item_id}")
    public ResponseEntity<GetClothesResponseDto> getClothesById(@PathVariable Long item_id) {
        return ResponseEntity.ok(clothesService.getClothesById(item_id));
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity<Void> deleteClothes(@PathVariable Long item_id) {
        clothesService.deleteClothes(item_id);
        return ResponseEntity.noContent().build();
    }
}
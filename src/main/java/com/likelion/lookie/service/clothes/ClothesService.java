package com.likelion.lookie.service.clothes;


import com.likelion.lookie.controller.clothes.dto.CreateClothesRequestDto;
import com.likelion.lookie.controller.clothes.dto.GetClothesResponseDto;
import com.likelion.lookie.entity.Clothes;
import com.likelion.lookie.entity.User;
import com.likelion.lookie.repository.ClothesRepository;
import com.likelion.lookie.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothesService {
    private final ClothesRepository clothesRepository;
    private final UserRepository userRepository;

    public ClothesService(ClothesRepository clothesRepository, UserRepository userRepository) {
        this.clothesRepository = clothesRepository;
        this.userRepository = userRepository;
    }

    public List<GetClothesResponseDto> getAllClothes() {
        return clothesRepository.findAll().stream()
                .map(c -> new GetClothesResponseDto(c.getId(), c.getBrand(), c.getCategory(), c.getPrice()))
                .collect(Collectors.toList());
    }

    public GetClothesResponseDto getClothesById(Long id) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("옷 아이템을 찾을 수 없습니다."));
        return new GetClothesResponseDto(clothes.getId(), clothes.getBrand(), clothes.getCategory(), clothes.getPrice());
    }

    public GetClothesResponseDto addClothes(CreateClothesRequestDto request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Clothes clothes = new Clothes(user,request.brand(), request.category(), request.price(), request.imageUrl());
        Clothes savedClothes = clothesRepository.save(clothes);
        return new GetClothesResponseDto(savedClothes.getId(), savedClothes.getBrand(), savedClothes.getCategory(), savedClothes.getPrice());
    }

    @Transactional
    public void deleteClothes(Long id) {
        clothesRepository.deleteById(id);
    }
}

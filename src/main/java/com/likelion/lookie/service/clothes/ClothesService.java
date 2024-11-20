package com.likelion.lookie.service.clothes;


import com.likelion.lookie.controller.clothes.dto.CreateClothesRequestDto;
import com.likelion.lookie.controller.clothes.dto.GetClothesResponseDto;
import com.likelion.lookie.entity.Clothes;
import com.likelion.lookie.entity.User;
import com.likelion.lookie.repository.ClothesRepository;
import com.likelion.lookie.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    // 사용자의 옷만 가져오도록 수정해야함.
    // @AuthenticationPrincipal을 이용해서 유저 정보를 가져오는 방식으로 구현
    // 얘는 list로 받는거고
    public List<GetClothesResponseDto> getAllClothes() {
        return clothesRepository.findAll().stream()
                .map(c -> new GetClothesResponseDto(c.getId(), c.getBrand(), c.getCategory(), c.getPrice()))
                .collect(Collectors.toList());
    }

    // 얘는 하나만 상세조회
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

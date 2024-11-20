package com.likelion.lookie.service.clothes;


import com.likelion.lookie.common.exception.user.UserCustomException;
import com.likelion.lookie.common.exception.user.UserErrorCode;
import com.likelion.lookie.common.util.FileService;
import com.likelion.lookie.controller.clothes.dto.CreateClothesRequestDto;
import com.likelion.lookie.controller.clothes.dto.GetClothesResponseDto;
import com.likelion.lookie.entity.Clothes;
import com.likelion.lookie.entity.User;
import com.likelion.lookie.repository.ClothesLookRepository;
import com.likelion.lookie.repository.ClothesRepository;
import com.likelion.lookie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClothesService {

    private final ClothesRepository clothesRepository;
    private final ClothesLookRepository clothesLookRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    // 사용자의 옷만 가져오도록 수정해야함.
    // @AuthenticationPrincipal을 이용해서 유저 정보를 가져오는 방식으로 구현
    // 얘는 list로 받는거고
    public List<GetClothesResponseDto> getAllClothes(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        return clothesRepository.findByUser(user).stream()
                .map(c -> new GetClothesResponseDto(c.getId(), c.getBrand(), c.getCategory(), c.getPrice(), fileService.getDownloadPresignedUrl(c.getImageUrl())))
                .collect(Collectors.toList());
    }

    // 얘는 하나만 상세조회
    public GetClothesResponseDto getClothesById(Long id) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("옷 아이템을 찾을 수 없습니다."));
        return new GetClothesResponseDto(clothes.getId(), clothes.getBrand(), clothes.getCategory(), clothes.getPrice(), fileService.getDownloadPresignedUrl(clothes.getImageUrl()));
    }

    public GetClothesResponseDto addClothes(String email, CreateClothesRequestDto request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Clothes clothes = new Clothes(user,request.brand(), request.category(), request.price(), request.imageUrl());

        Clothes savedClothes = clothesRepository.save(clothes);

        return new GetClothesResponseDto(savedClothes.getId(), savedClothes.getBrand(), savedClothes.getCategory(), savedClothes.getPrice(), savedClothes.getImageUrl());
    }


    // 옷을 삭제할 경우, 룩에 포함되어있는 룩도 다 같이 삭제되어야함
    // 사용자에게 경고 메세지는 띄워줘야될 듯?
    @Transactional
    public String deleteClothes(Long id) {
        clothesLookRepository.deleteAllByClothesId(id);
        clothesRepository.deleteById(id);

        return "Clothes successfully deleted.";
    }
}

package com.likelion.lookie.repository;

import com.likelion.lookie.entity.ClothesLook;
import com.likelion.lookie.entity.Look;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothesLookRepository extends JpaRepository<ClothesLook, Long> {

    List<ClothesLook> findAllByLook(Look look);

    void deleteAllByLookId(Long lookId);

    void deleteAllByClothesId(Long clothesId);
}

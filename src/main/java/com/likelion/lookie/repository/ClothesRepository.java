package com.likelion.lookie.repository;

import com.likelion.lookie.entity.Clothes;
import com.likelion.lookie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {

    List<Clothes> findByUser(User user);

}

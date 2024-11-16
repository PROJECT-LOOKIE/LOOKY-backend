package com.likelion.lookie.repository;

import com.likelion.lookie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname(String nickname); // 닉네임 중복 체크

    Optional<User> findBySubId(String subId);

    Optional<User> findByEmail(String email);

}

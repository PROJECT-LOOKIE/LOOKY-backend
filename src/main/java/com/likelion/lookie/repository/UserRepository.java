package com.likelion.lookie.repository;

import com.likelion.lookie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    // 현재 사용자를 제외한 닉네임 중복 체크
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.nickname = :nickname AND u.id != :userId")
    boolean existsByNicknameAndNotUserId(@Param("nickname") String nickname, @Param("userId") Long userId);

    Optional<User> findBySubId(String subId);

    Optional<User> findByEmail(String email);

}

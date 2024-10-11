package org.example.lookie.repository;

import org.example.lookie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long Id);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}

package com.likelion.lookie.repository;

import com.likelion.lookie.entity.Look;
import com.likelion.lookie.entity.Schedule;
import com.likelion.lookie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LookRepository extends JpaRepository<Look, Long> {

    boolean existsByUserAndSchedule(User user, Schedule schedule);

}

package com.likelion.lookie.repository;

import com.likelion.lookie.entity.Look;
import com.likelion.lookie.entity.Schedule;
import com.likelion.lookie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LookRepository extends JpaRepository<Look, Long> {

    List<Look> findAllByScheduleId(Long scheduleId);

    boolean existsByUserAndSchedule(User user, Schedule schedule);

    int countByScheduleId(Long scheduleId);

    Look findByUserAndSchedule(User user, Schedule schedule);


}

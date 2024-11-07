package com.likelion.lookie.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    private String name;
    private LocalDate date;  // 날짜 필드를 LocalDate로 변경
    private String location;
    private String atmosphere;
    private int decoration;
    private int people;
}

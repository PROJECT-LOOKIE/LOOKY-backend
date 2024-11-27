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

    @Column(columnDefinition = "TEXT") // 이모지 데이터가 길어질 경우 TEXT 타입 사용 가능
    private String emoji;
    private String name;
    private LocalDate date;
    private String location;
    private String atmosphere;
    private int decoration;
    private int people;
}

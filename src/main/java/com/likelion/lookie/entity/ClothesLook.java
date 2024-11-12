package com.likelion.lookie.entity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class ClothesLook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clotheslook_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "look_id")
    private Look look;

}

package com.likelion.lookie.entity;
import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String brand;
    private String category;
    private int price;
    private String imageUrl;

    public Clothes(User user, String brand, String category, Integer price, String imageUrl) {
        this.user = user;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void update(String brand, String category, Integer price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

}

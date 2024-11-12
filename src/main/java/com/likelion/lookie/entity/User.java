package com.likelion.lookie.entity;


import com.likelion.lookie.common.oauth.dto.OidcDecodePayload;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String subId;

    private String name;
    private String email;
    private String picture;
    private String nickname;

    // 새로운 사용자를 생성하는 팩토리 메서드
    public static User createSocialUser(OidcDecodePayload payload) {
        User user = new User();
        user.nickname = payload.sub();
        user.name = payload.nickname();
        user.email = payload.email();
        user.picture = payload.picture();
        return user;
    }

    // 사용자 프로필을 업데이트하는 메서드
    public void updateProfile(String nickname, String email, String profileImage) {
        this.name = nickname;
        this.email = email;
        this.picture = profileImage;
    }

}

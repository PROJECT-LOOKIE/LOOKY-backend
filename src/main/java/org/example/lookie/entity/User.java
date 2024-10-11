package org.example.lookie.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.example.lookie.common.oauth.dto.KakaoUserInfoResponseDto;
import org.example.lookie.common.oauth.dto.OidcDecodePayload;

@Getter
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long Id;
    private String name;
    private String picture;
    private String email;

    public static User createSocialUser(OidcDecodePayload oidcDecodePayload) {

        User user = new User();
        user.Id = oidcDecodePayload.sub();
        user.name = oidcDecodePayload.nickname();
        user.email = oidcDecodePayload.email();
        user.picture = oidcDecodePayload.picture();
        return user;
    }

    public static User of(KakaoUserInfoResponseDto dto) {
        User user = new User();
        user.name = dto.kakaoAccount.profile.nickName;
        user.email = dto.kakaoAccount.email;
        user.picture = dto.kakaoAccount.profile.profileImageUrl;
        return user;
    }

}

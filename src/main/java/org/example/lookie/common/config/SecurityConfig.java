package org.example.lookie.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/callback","/login/page", "/resources/**", "/kakao_login_medium_narrow.png").permitAll()  // 커스텀 로그인 페이지 및 정적 리소스 허용
                                .anyRequest().authenticated()  // 나머지 모든 요청은 인증 필요
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login/page")  // 커스텀 로그인 페이지 설정
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")  // 로그아웃 URL 설정
                                .logoutSuccessUrl("/login/page")  // 로그아웃 후 리디렉션
                                .invalidateHttpSession(true)  // 세션 무효화
                                .deleteCookies("JSESSIONID")  // 쿠키 삭제
                );
        return http.build();
    }
}
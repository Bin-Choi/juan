package com.juanbuild.juan.user.application;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Builder
    private User(String email, String name, String password, UserRole role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public void changeEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("잘못된 이메일 형식입니다.");
        }
        this.email = email;
    }

    public void changeName(String name) {
        if (!name.matches("^[가-힣]{2,20}$")) {
            throw new IllegalArgumentException("이름은 한글 2~20자여야 합니다.");
        }
        this.name = name;
    }

    // TODO: 검증 정책 밖에서 할 예정(정책이 복잡 + 암호화)
    public void changePassword(String password) {
        this.password = password;
    }

    public static User of(String email, String name, String password, UserRole role) {
        return builder().email(email).name(name).password(password).role(role).build();
    }
}

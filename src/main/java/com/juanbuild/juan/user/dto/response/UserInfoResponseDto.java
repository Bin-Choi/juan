package com.juanbuild.juan.user.dto.response;

import com.juanbuild.juan.user.domain.User;
import com.juanbuild.juan.user.domain.UserRole;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private final Long id;
    private final String name;
    private final String username;
    private final String email;
    private final UserRole role;

    @Builder(access = AccessLevel.PRIVATE)
    private UserInfoResponseDto(Long id, String name, String username, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public static UserInfoResponseDto from(User user) {
        return UserInfoResponseDto.builder()
                                  .id(user.getId())
                                  .email(user.getEmail())
                                  .name(user.getName())
                                  .username(user.getUsername())
                                  .role(user.getRole())
                                  .build();
    }
}

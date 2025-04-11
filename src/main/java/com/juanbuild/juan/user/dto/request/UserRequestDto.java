package com.juanbuild.juan.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequestDto {
    private String username;
    private String password;
    private String email;
    private String name;
}

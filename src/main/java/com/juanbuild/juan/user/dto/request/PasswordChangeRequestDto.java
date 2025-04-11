package com.juanbuild.juan.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordChangeRequestDto {
    private String oldPassword;
    private String newPassword;
}

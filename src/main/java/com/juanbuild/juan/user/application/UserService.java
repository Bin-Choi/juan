package com.juanbuild.juan.user.application;

import com.juanbuild.juan.user.domain.User;
import com.juanbuild.juan.user.domain.policy.PasswordPolicy;
import com.juanbuild.juan.user.dto.PasswordChangeRequestDto;
import com.juanbuild.juan.user.dto.UserInfoResponseDto;
import com.juanbuild.juan.user.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordPolicy passwordPolicy;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordPolicy passwordPolicy) {this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordPolicy = passwordPolicy;
    }

    // TODO: user 존재하지 않을 시 에러 처리
    public UserInfoResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return UserInfoResponseDto.from(user);
    }

    @Transactional
    public void changePassword(Long userId, PasswordChangeRequestDto request) {
        User user = userRepository.findById(userId).orElse(null);

        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password does not match");
        }

        if(!passwordPolicy.isValid(request.getNewPassword())) {
            throw new IllegalArgumentException("New password does not match");
        }

        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        user.changePassword(encodedPassword);
    }
}

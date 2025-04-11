package com.juanbuild.juan.user.application;

import com.juanbuild.juan.user.domain.User;
import com.juanbuild.juan.user.domain.UserRole;
import com.juanbuild.juan.user.domain.policy.PasswordPolicy;
import com.juanbuild.juan.user.domain.service.UserFinder;
import com.juanbuild.juan.user.dto.request.PasswordChangeRequestDto;
import com.juanbuild.juan.user.dto.request.UserRequestDto;
import com.juanbuild.juan.user.dto.response.UserInfoResponseDto;
import com.juanbuild.juan.user.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordPolicy passwordPolicy;
    private final UserFinder userFinder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordPolicy passwordPolicy, UserFinder userFinder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordPolicy = passwordPolicy;
        this.userFinder = userFinder;
    }

    @Transactional
    public UserInfoResponseDto createUser(UserRequestDto request) {
        User data = User.of(request.getEmail(), request.getName(), request.getPassword(), request.getUsername(), UserRole.ROLE_USER);
        User savedUser = userRepository.save(data);
        return UserInfoResponseDto.from(savedUser);
    }

    // TODO: user 존재하지 않을 시 에러 처리
    public UserInfoResponseDto getUserInfo(Long userId) {
        User user = userFinder.getById(userId);
        return UserInfoResponseDto.from(user);
    }

    @Transactional
    public void changePassword(Long userId, PasswordChangeRequestDto request) {
        User user = userFinder.getById(userId);

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

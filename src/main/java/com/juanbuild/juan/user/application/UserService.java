package com.juanbuild.juan.user.application;

import com.juanbuild.juan.user.domain.User;
import com.juanbuild.juan.user.dto.UserInfoResponseDto;
import com.juanbuild.juan.user.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    // TODO: user 존재하지 않을 시 에러 처리
    public UserInfoResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return UserInfoResponseDto.from(user);
    }
}

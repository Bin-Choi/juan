package com.juanbuild.juan.user.domain.service;

import com.juanbuild.juan.user.domain.User;
import com.juanbuild.juan.user.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserFinder {

    private final UserRepository userRepository;

    public UserFinder(UserRepository userRepository) {this.userRepository = userRepository;}

    public User getById(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다. id=" + id));
    }
}

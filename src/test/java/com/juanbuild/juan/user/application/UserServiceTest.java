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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PasswordPolicy passwordPolicy;

    @Mock
    private UserFinder userFinder;

    @InjectMocks
    private UserService userService;


    @Test
    void 회원가입_성공() {
        // given
        UserRequestDto request = new UserRequestDto("test@example.com", "홍길동", "1234", "testuser");
        User user = User.of(request.getEmail(), request.getName(), request.getPassword(), request.getUsername(), UserRole.ROLE_USER);

        given(userRepository.save(any(User.class))).willReturn(user);

        // when
        UserInfoResponseDto result = userService.createUser(request);

        // then
        assertThat(result.getEmail()).isEqualTo(request.getEmail());
        assertThat(result.getName()).isEqualTo(request.getName());
        assertThat(result.getUsername()).isEqualTo(request.getUsername());
        assertThat(result.getRole()).isEqualTo(UserRole.ROLE_USER);

        then(userRepository).should(times(1)).save(any(User.class));
    }


    @Test
    void 프로필_조회_성공() {
        // given
        Long userId = 1L;
        User user = User.of("test@example.com", "홍길동", "1234", "testuser", UserRole.ROLE_USER);

        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        // when
        UserInfoResponseDto result = userService.getUserInfo(userId);

        // then
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        assertThat(result.getName()).isEqualTo("홍길동");
    }


    @Test
    void 비밀번호_변경_성공() {
        // given
        Long userId = 1L;
        User user = User.of("test@example.com", "홍길동", "encodedOldPw", "testuser", UserRole.ROLE_USER);
        PasswordChangeRequestDto request = new PasswordChangeRequestDto("oldPw", "newPw");

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(passwordEncoder.matches("oldPw", "encodedOldPw")).willReturn(true);
        given(passwordPolicy.isValid("newPw")).willReturn(true);
        given(passwordEncoder.encode("newPw")).willReturn("encodedNewPw");

        // when
        userService.changePassword(userId, request);

        // then
        assertThat(user.getPassword()).isEqualTo("encodedNewPw");
    }


    @Test
    void 비밀번호_변경_실패_기존비밀번호_불일치() {
        // given
        Long userId = 1L;
        User user = User.of("test@example.com", "홍길동", "encodedOldPw", "testuser", UserRole.ROLE_USER);
        PasswordChangeRequestDto request = new PasswordChangeRequestDto("wrongOldPw", "newPw");

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(passwordEncoder.matches("wrongOldPw", "encodedOldPw")).willReturn(false);

        // when & then
        assertThatThrownBy(() -> userService.changePassword(userId, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Old password does not match");
    }


    @Test
    void 비밀번호_변경_실패_새로운비밀번호_정책불일치() {
        // given
        Long userId = 1L;
        User user = User.of("test@example.com", "홍길동", "encodedOldPw", "testuser", UserRole.ROLE_USER);
        PasswordChangeRequestDto request = new PasswordChangeRequestDto("oldPw", "badPw");

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(passwordEncoder.matches("oldPw", "encodedOldPw")).willReturn(true);
        given(passwordPolicy.isValid("badPw")).willReturn(false);

        // when & then
        assertThatThrownBy(() -> userService.changePassword(userId, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("New password does not match");
    }


    @Test
    void 프로필_조회_실패_사용자없음() {
        // given
        Long userId = 99L;
        given(userFinder.getById(userId)).willThrow(new IllegalArgumentException("존재하지 않는 사용자입니다. id=" + userId));

        // when, then
        assertThatThrownBy(() -> userService.getUserInfo(userId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 사용자입니다. id=" + userId);
    }
}
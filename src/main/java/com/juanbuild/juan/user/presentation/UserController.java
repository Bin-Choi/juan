package com.juanbuild.juan.user.presentation;

import com.juanbuild.juan.user.application.UserService;
import com.juanbuild.juan.user.dto.request.NameChangeRequestDto;
import com.juanbuild.juan.user.dto.request.PasswordChangeRequestDto;
import com.juanbuild.juan.user.dto.request.UserRequestDto;
import com.juanbuild.juan.user.dto.response.UserInfoResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @PatchMapping
    public ResponseEntity<?> signUp(@RequestBody UserRequestDto request) {
        UserInfoResponseDto response = userService.createUser(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo() {
        //TODO: security 에서 userID 가져오도록 변경 예정
        Long userId = 1L;
        UserInfoResponseDto response = userService.getUserInfo(userId);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDto request) {
        //TODO: security 에서 userID 가져오도록 변경 예정
        Long userId = 1L;
        userService.changePassword(userId, request);

        return ResponseEntity.ok("password changed");
    }

    @PatchMapping("/username")
    public ResponseEntity<?> changeUsername(@RequestBody NameChangeRequestDto request) {
        Long userId = 1L;
        UserInfoResponseDto response = userService.changeName(userId, request);

        return ResponseEntity.ok(response);
    }
}

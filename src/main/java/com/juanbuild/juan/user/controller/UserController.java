package com.juanbuild.juan.user.controller;

import com.juanbuild.juan.user.application.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/my")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("/info")
    public ResponseEntity<?> getMyInfo() {
        //TODO: security 에서 userID 가져오도록 변경 예정
        userService.getUserInfo(1L);

        return ResponseEntity.ok("ok");
    }
}

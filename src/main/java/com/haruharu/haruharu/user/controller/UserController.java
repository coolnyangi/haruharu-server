package com.haruharu.haruharu.user.controller;

import com.haruharu.haruharu.common.auth.CurrentUserId;
import com.haruharu.haruharu.common.response.SuccessResponse;
import com.haruharu.haruharu.user.dto.request.UserRequest;
import com.haruharu.haruharu.user.dto.response.UserCreateResponse;
import com.haruharu.haruharu.user.dto.response.UserResponse;
import com.haruharu.haruharu.user.entity.User;
import com.haruharu.haruharu.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 1. 회원가입
    @PostMapping
    public ResponseEntity<SuccessResponse<UserCreateResponse>> create(
            @Valid @RequestBody UserRequest req
    ) {
        User user = userService.createUser(req);

        UserCreateResponse response = new UserCreateResponse(user.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(response));
    }

    // 2. 내 정보 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<UserResponse>> me(
            @CurrentUserId Long userId
    ) {
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(SuccessResponse.ok(toResponse(user)));
    }

    // 3. 내 정보 수정
    @PatchMapping
    public ResponseEntity<SuccessResponse<UserResponse>> patchMe(
            @CurrentUserId Long userId,
            @RequestBody UserRequest req
    ) {
        User updated = userService.updateUser(userId, req);
        return ResponseEntity.ok(SuccessResponse.ok(toResponse(updated)));
    }

    // dto 변환
    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }
}
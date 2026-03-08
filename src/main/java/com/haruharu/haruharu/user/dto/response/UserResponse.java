package com.haruharu.haruharu.user.dto.response;

public record UserResponse(
    Long userId,
    String email,
    String nickname
) {}
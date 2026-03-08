package com.haruharu.haruharu.user.dto.request;

public record UserRequest(
    String email,
    String password,
    String nickname
) {}
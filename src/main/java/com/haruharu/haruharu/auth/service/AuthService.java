package com.haruharu.haruharu.auth.service;

import com.haruharu.haruharu.common.auth.JwtTokenProvider;
import com.haruharu.haruharu.common.exception.BusinessException;
import com.haruharu.haruharu.common.exception.ErrorCode;
import com.haruharu.haruharu.user.entity.User;
import com.haruharu.haruharu.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS));

        if (!user.getPassword().equals(password)) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }

        return jwtTokenProvider.createAccessToken(user.getId());
    }

    // 서버는 로그아웃 상태 저장 안 하는걸로
    public void logout(Long userId) {

    }
}
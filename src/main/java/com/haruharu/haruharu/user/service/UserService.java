package com.haruharu.haruharu.user.service;


import com.haruharu.haruharu.common.exception.BusinessException;
import com.haruharu.haruharu.common.exception.ErrorCode;
import com.haruharu.haruharu.user.dto.request.UserRequest;
import com.haruharu.haruharu.user.entity.User;
import com.haruharu.haruharu.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public User createUser(UserRequest req) {
        // 1. 유저로부터 정보를 입력 받는다.
        // 2. 이메일 중복 확인
        if (userRepository.existsByEmail(req.email())) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        // 3. 새로운 유저 객체 생성
        User user = new User(req.email(), req.password(), req.nickname());

        // 3. 유저 객체 저장
        return  userRepository.save(user);
    }

    // 본인 정보 조회
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.VALIDATION_ERROR));
    }

    // 본인 정보 수정
    @Transactional
    public User updateUser(Long id, UserRequest req) {
        // 1. 유저 아이디로 조회
        User user = getUserById(id);

        // 2. 수정할 값 입력 받음
        if (req.email() != null) user.changeEmail(req.email());
        if (req.password() != null) user.changePassword(req.password());
        if (req.nickname() != null) user.changeNickname(req.nickname());

        // 3. 저장
        return user;
    }
}

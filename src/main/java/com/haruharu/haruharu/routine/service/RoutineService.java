package com.haruharu.haruharu.routine.service;

import com.haruharu.haruharu.common.exception.BusinessException;
import com.haruharu.haruharu.common.exception.ErrorCode;
import com.haruharu.haruharu.routine.entity.Routine;
import com.haruharu.haruharu.routine.entity.RoutineStatus;
import com.haruharu.haruharu.routine.repository.RoutineRepository;
import com.haruharu.haruharu.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;

    // 습관 생성
    @Transactional
    public Routine createRoutine(Long userId, String title, LocalDate startDate, LocalDate endDate, RoutineStatus status) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new BusinessException(ErrorCode.VALIDATION_ERROR));
        // 1. 새로운 루틴에 들어갈 파라미터를 받는다.
        // 2. 새로운 루틴 객체를 만든다.
        Routine routine = new Routine();

        // 3. 생선된 루틴을 저장한다.
        return routineRepository.save(routine);
    }

    // 습관 단일 조회
    public Routine getRoutine(Long routineId) {
        // 1. 루틴 아이디를 가져온다
        return routineRepository.findById(routineId)
                // 2. 없으면 에러 반환
                .orElseThrow(() -> new BusinessException(ErrorCode.VALIDATION_ERROR));
    }

    // 습관 리스트 조회
    public List<Routine> getAllRoutines(Long userId) {
        // 1. 유저 아이디를 가져온다.
        // 2. 리스트로 전체를 조회한다.
        return routineRepository.findAllByUserId(userId);
    }

    // 습관 삭제
    @Transactional
    public void deleteRoutine(Long routineId) {
        // 1. 삭제하고자 하는 루틴 아이디 가져온다.
        // 2. 삭제한다.
        routineRepository.deleteById(routineId);
    }

    // 습관 재시작 (시작일 변경)
    @Transactional
    public Routine updateRoutine(Long routineId, LocalDate startDate) {
        // 1. 루틴 아이디 가져온다.
        Routine routine = getRoutine(routineId);

        // 2. 시작 날짜를 도메인 메서드를 사용하여 변경한다.
        routine.changeStartDate(startDate);

        // 3. 저장한다.
        return routineRepository.save(routine);
    }

    // 습관 이름 변경
    @Transactional
    public Routine updateTitle(Long routineId, String title) {
        // 1. 루틴 아이디를 가져온다.
        Routine routine = getRoutine(routineId);

        // 2. 루틴 이름을 도메인 메서드를 사용하여 변경한다.
        routine.changeTitle(title);

        // 3. 저장한다.
        return routineRepository.save(routine);
    }

    // 습관 성공 -> 루틴 로그에서 21일이 기록되면 자동 실행 -> 컬렉션으로 전달

    // 습관 실패 -> 루틴 로그에서 실패 기록되면 자동 실행 -> 재시작 또는 삭제

}

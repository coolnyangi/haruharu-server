package com.haruharu.haruharu.routine.service;

import com.haruharu.haruharu.routine.entity.Routine;
import com.haruharu.haruharu.routine.entity.RoutineStatus;
import com.haruharu.haruharu.routine.repository.RoutineRepository;
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

    // 습관 생성
    @Transactional
    public Routine createRoutine(String title, LocalDate startDate, LocalDate endDate, RoutineStatus status) {

        Routine routine = new Routine();

        return routineRepository.save(routine);
    }

    // 습관 조회
    public List<Routine> getAllRoutines() {
        return routineRepository.findAll();
    }

    // 습관 삭제
    public Routine deleteRoutine() {
        return routineRepository;
    }

    // 습관 재시작

}

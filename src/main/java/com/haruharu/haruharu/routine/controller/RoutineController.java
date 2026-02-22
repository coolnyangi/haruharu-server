package com.haruharu.haruharu.routine.controller;

import com.haruharu.haruharu.common.auth.CurrentUserId;
import com.haruharu.haruharu.common.response.SuccessResponse;
import com.haruharu.haruharu.routine.dto.request.RoutineCreateRequest;
import com.haruharu.haruharu.routine.dto.response.RoutineCreateResponse;
import com.haruharu.haruharu.routine.dto.response.RoutineResponse;
import com.haruharu.haruharu.routine.entity.Routine;
import com.haruharu.haruharu.routine.entity.RoutineStatus;
import com.haruharu.haruharu.routine.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    // 1. 습관 생성
    @PostMapping
    public ResponseEntity<SuccessResponse<RoutineCreateResponse>> create(
            @CurrentUserId Long userId,
            @RequestBody RoutineCreateRequest req
    ) {
        Routine routine = routineService.createRoutine(
                userId,
                req.title(),
                req.startDate(),
                null, // endDate 아직 없으면 null
                RoutineStatus.PENDING
        );

        RoutineCreateResponse response = new RoutineCreateResponse(routine.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(response));
    }

    // 2. 단일 조회
    @GetMapping("/{routineId}")
    public ResponseEntity<SuccessResponse<RoutineResponse>> getOne(
            @PathVariable Long routineId
    ) {
        Routine routine = routineService.getRoutine(routineId);

        RoutineResponse response = toResponse(routine);

        return ResponseEntity.ok(SuccessResponse.ok(response));
    }

    // 3. 리스트 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<List<RoutineResponse>>> getList(
            @CurrentUserId Long userId
    ) {
        List<RoutineResponse> list = routineService.getAllRoutines(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(SuccessResponse.ok(list));
    }

    // 4. 성공 처리
    @PostMapping("/{routineId}/success")
    public ResponseEntity<SuccessResponse<Void>> success(
            @PathVariable Long routineId
    ) {
        routineService.successRoutine(routineId);
        return ResponseEntity.ok(SuccessResponse.ok(null));
    }

    // 5. 실패 삭제
    @DeleteMapping("/{routineId}/fail")
    public ResponseEntity<SuccessResponse<Void>> deleteFail(
            @PathVariable Long routineId
    ) {
        routineService.deleteFailRoutine(routineId);
        return ResponseEntity.ok(SuccessResponse.ok(null));
    }

    // 6. 실패 재시작
    @PostMapping("/{routineId}/retry")
    public ResponseEntity<SuccessResponse<Void>> retryFail(
            @PathVariable Long routineId
    ) {
        routineService.retryFailRoutine(routineId);
        return ResponseEntity.ok(SuccessResponse.ok(null));
    }

    // dto 변환
    private RoutineResponse toResponse(Routine routine) {
        return new RoutineResponse(
                routine.getId(),
                routine.getTitle(),
                null,
                null,
                routine.getStartDate(),
                routine.getStatus() == RoutineStatus.PENDING
        );
    }
}
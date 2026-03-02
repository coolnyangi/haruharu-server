package com.haruharu.haruharu.routine.dto.response;

import com.haruharu.haruharu.routine.entity.RoutineColor;
import com.haruharu.haruharu.routine.entity.RoutineEmoji;
import com.haruharu.haruharu.routine.entity.RoutineStatus;

import java.time.LocalDate;

public record RoutineResponse(
        Long routineId,
        String title,
        RoutineEmoji emoji,
        RoutineColor color,
        String flower,
        LocalDate startDate,
        LocalDate endDate,
        RoutineStatus status
) {}
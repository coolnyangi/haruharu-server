package com.haruharu.haruharu.routine.dto.request;

import com.haruharu.haruharu.routine.entity.RoutineColor;
import com.haruharu.haruharu.routine.entity.RoutineEmoji;

import java.time.LocalDate;

public record RoutineUpdateRequest(
        String title,
        RoutineEmoji emoji,
        RoutineColor color,
        LocalDate startDate
) {}
package com.haruharu.haruharu.routine.dto.response;

import java.time.LocalDate;

public record RoutineResponse(
        Long routineId,
        String title,
        String emoji,
        String color,
        LocalDate startDate,
        boolean active
) {}
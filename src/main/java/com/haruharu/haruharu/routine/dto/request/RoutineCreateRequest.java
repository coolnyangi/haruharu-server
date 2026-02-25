package com.haruharu.haruharu.routine.dto.request;

import com.haruharu.haruharu.routine.entity.RoutineColor;
import com.haruharu.haruharu.routine.entity.RoutineEmoji;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RoutineCreateRequest(
        @NotBlank String title,
        @NotNull RoutineEmoji emoji,
        @NotNull RoutineColor color,
        @NotNull LocalDate startDate
) {}
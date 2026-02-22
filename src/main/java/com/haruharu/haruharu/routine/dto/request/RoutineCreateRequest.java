package com.haruharu.haruharu.routine.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RoutineCreateRequest(
        @NotBlank String title,
        String emoji,
        String color,
        @NotNull LocalDate startDate
) {}
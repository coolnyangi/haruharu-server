package com.haruharu.haruharu.common.response;

import com.haruharu.haruharu.common.exception.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final int status;
    private final String path;
    private final LocalDateTime timestamp;
    private final List<FieldErrorDetail> errors;

    private ErrorResponse(
            String code,
            String message,
            int status,
            String path,
            LocalDateTime timestamp,
            List<FieldErrorDetail> errors
    ) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public static ErrorResponse of(ErrorCode errorCode, String path, List<FieldErrorDetail> errors) {
        return new ErrorResponse(
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.getStatus().value(),
                path,
                LocalDateTime.now(),
                errors
        );
    }

    @Getter
    public static class FieldErrorDetail {
        private final String field;
        private final String rejectedValue;
        private final String reason;

        public FieldErrorDetail(String field, String rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }
    }
}
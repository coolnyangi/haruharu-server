package com.haruharu.haruharu.global.common.entity;

import com.haruharu.haruharu.global.common.response.ApiResponse;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

@Getter
@MappedSuperclass
@EntityListeners(ApiResponse.class)

public class BaseTimeEntity {
    @CreatedDate
}

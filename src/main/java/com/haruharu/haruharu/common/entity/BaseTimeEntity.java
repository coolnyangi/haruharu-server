package com.haruharu.haruharu.common.entity;

import com.haruharu.haruharu.common.response.ApiResponse;
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

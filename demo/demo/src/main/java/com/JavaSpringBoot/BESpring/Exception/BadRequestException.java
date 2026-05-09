package com.JavaSpringBoot.BESpring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception này được sử dụng khi request từ client không hợp lệ (HTTP 400).
 * Ví dụ: Thiếu tên sinh viên khi tạo mới.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

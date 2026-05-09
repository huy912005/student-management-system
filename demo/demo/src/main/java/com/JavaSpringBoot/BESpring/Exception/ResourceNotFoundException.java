package com.JavaSpringBoot.BESpring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception này được sử dụng khi một tài nguyên không được tìm thấy (HTTP 404).
 * Ví dụ: Tìm sinh viên với ID không tồn tại.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

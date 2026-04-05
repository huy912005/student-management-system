package com.JavaSpringBoot.BESpring.DTO.Response;

public class ApiResponse<T> {
    private String message;
    private T data;

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }
}

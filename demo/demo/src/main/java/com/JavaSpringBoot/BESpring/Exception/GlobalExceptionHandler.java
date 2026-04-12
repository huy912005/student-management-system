package com.JavaSpringBoot.BESpring.Exception;

import com.JavaSpringBoot.BESpring.DTO.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

// @RestControllerAdvice: Đeo biển hiệu "TÔI LÀ QUẢN LÝ LỖI TOÀN HỆ THỐNG".
// Bất kỳ Controller nào trong project ném ra lỗi, Spring Boot sẽ tự động gọi anh quản lý này ra giải quyết.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler: Bảng phân công nhiệm vụ của Quản lý.
    // Dòng này có nghĩa là: "NẾU lỗi là do khách nhập sai dữ liệu (MethodArgumentNotValidException), thì chạy vào hàm này".
    // Cái lỗi này chính là khi khách vi phạm các luật @NotBlank, @Min, @Max
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleValidation(MethodArgumentNotValidException ex){

        // Khi khách nhập sai, Spring nó ném ra một đống thông tin lỗi rất dài.
        String message = ex.getBindingResult() // 1. Lục lại đống báo cáo lỗi tổng hợp
                .getFieldError()               // 2. Nhặt ra cái trường (field) đầu tiên bị sai
                .getDefaultMessage();          // 3. Lấy ra đúng câu thông báo lỗi (message) đã cài đặt

        // Gói câu thông báo tiếng Việt đó vào hộp ErrorResponseDTO và trả về cho Postman.
        // Postman sẽ nhận được 1 cục JSON cực kỳ sạch đẹp, không bị báo lỗi đỏ 500 nữa.
        return new ApiResponse<>(false, message,null);
    }

    // Nhiệm vụ số 2: "NẾU gặp lỗi logic chung chung (RuntimeException), thì chạy vào đây".
    // Nhớ lại lúc bạn code chức năng Login không? Bạn viết: throw new RuntimeException("Sai tài khoản!");
    // Nhờ có hàm này, chữ "Sai tài khoản!" sẽ được bắt lại và trả về cho Front-end một cách êm đẹp.
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleRuntime(RuntimeException ex){

        // ex.getMessage() chính là lấy ra câu "Sai tài khoản!" để nhét vào hộp Response.
        return new ApiResponse<>(false, ex.getMessage(),null);
    }
}
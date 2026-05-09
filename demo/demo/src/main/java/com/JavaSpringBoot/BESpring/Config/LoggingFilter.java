package com.JavaSpringBoot.BESpring.Config;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// LoggingFilter là class dùng để gắn “traceId” cho mỗi request để theo dõi log xuyên suốt hệ thống
@Component
public class LoggingFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  throws ServletException, IOException {
        String traceId = UUID.randomUUID().toString(); // Tạo ra một mã định danh duy nhất cho mỗi yêu cầu
        MDC.put("traceId", traceId);// Đưa mã này vào MDC để log4j có thể lấy ra và in vào log
        try {
            filterChain.doFilter(request, response); // Cho phép yêu cầu tiếp tục đi qua các filter khác và đến controller
        } finally {
            MDC.remove("traceId"); // Sau khi xong việc, dọn dẹp MDC để tránh rò rỉ thông tin giữa các yêu cầu
        }
    }

}
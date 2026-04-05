package com.JavaSpringBoot.BESpring.Config;

import com.JavaSpringBoot.BESpring.Security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Đánh dấu đây là lớp cấu hình hệ thống
public class SecurityConfig {

    @Bean // Tạo ra một "Cỗ máy lọc" để Spring sử dụng
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // Tắt bảo vệ CSRF (thường tắt khi làm API để dễ test Postman)
                .authorizeHttpRequests(auth -> auth
                        // 1. Cho phép tất cả mọi người truy cập vào các đường dẫn bắt đầu bằng /auth/
                        // (Ví dụ: /auth/login, /auth/register thì không cần thẻ)
                        .requestMatchers("user/auth/**").permitAll()

                        // 2. Tất cả các yêu cầu còn lại (như /sinhvien)
                        // THÌ BẮT BUỘC phải đăng nhập (có thẻ) mới được vào
                        .anyRequest().authenticated()
                );
        // 4. QUAN TRỌNG NHẤT: Thêm "Trạm gác JWT" của bạn vào quy trình.
        // Lệnh này bảo Spring: "Trước khi kiểm tra Username/Password mặc định, hãy chạy qua cái JwtFilter của tôi để soi thẻ trước".
        http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build(); // Chốt cấu hình và xuất xưởng bộ lọc
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
package com.JavaSpringBoot.BESpring.Config;

import com.JavaSpringBoot.BESpring.Security.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration // Đánh dấu đây là lớp cấu hình hệ thống
@EnableMethodSecurity //kích hoạt tính năng phân quyền ở cấp độ methodb. dùng đc (@PreAuthorize: kiểm tra quyền trước
// ,@PostAuthorize: Thực hiện xong mới kiểm tra nếu k thuộc thì chặn, @PreFilter / @PostFilter: lọc Collection)
public class SecurityConfig {
    @Autowired
    private LoggingFilter loggingFilter; // Đưa cái "Cỗ máy lọc log" vào đây để Spring biết và sử dụng nó

    @Bean // Tạo ra một "Cỗ máy lọc" để Spring sử dụng
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // Tắt bảo vệ CSRF (thường tắt khi làm API để dễ test Postman)
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        // 1. Cho phép tất cả mọi người truy cập vào các đường dẫn bắt đầu bằng /auth/
                        // (Ví dụ: /auth/login, /auth/register thì không cần thẻ)
                        .requestMatchers("/user/auth/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/user/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // 2. Tất cả các yêu cầu còn lại (như /sinhvien)
                        // THÌ BẮT BUỘC phải đăng nhập (có thẻ) mới được vào
                        .anyRequest().authenticated()
                );
        // 4. QUAN TRỌNG NHẤT: Thêm "Trạm gác JWT" của bạn vào quy trình.
        // Lệnh này bảo Spring: "Trước khi kiểm tra Username/Password mặc định, hãy chạy qua cái JwtFilter của tôi để soi thẻ trước".
        http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build(); // Chốt cấu hình và xuất xưởng bộ lọc
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:5173")); // React
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
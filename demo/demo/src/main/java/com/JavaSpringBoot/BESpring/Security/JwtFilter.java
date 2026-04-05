package com.JavaSpringBoot.BESpring.Security;

import com.JavaSpringBoot.BESpring.Utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

// OncePerRequestFilter: Đảm bảo mỗi lần khách gọi API, anh bảo vệ chỉ kiểm tra thẻ ĐÚNG 1 LẦN
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if(path.startsWith("/auth")){
            filterChain.doFilter(request, response);
            return;
        }
        // 1. Lấy thông tin từ Header có tên là "Authorization"
        // (Khách thường gửi thẻ trong túi này)
        String header = request.getHeader("Authorization");
        // 2. Kiểm tra xem khách có mang túi thẻ không?
        // Và cái thẻ đó có phải loại "Bearer " (thẻ JWT tiêu chuẩn) không?
        if(header != null && header.startsWith("Bearer ")){
            // 3. Cắt bỏ chữ "Bearer " để lấy đúng cái chuỗi Token loằng ngoằng phía sau
            String token = header.substring(7);

            try {
                // 4. Dùng máy quét (JwtUtil) để đọc xem tên người dùng ghi trên thẻ là ai
                String username = JwtUtil.getUsername(token);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("User: " + username);
                // Lưu ý: Ở đây bạn mới lấy tên, chưa báo cho Spring biết là người này đã "hợp lệ" hoàn toàn.
            } catch (Exception e){
                // 5. Nếu thẻ giả, thẻ hết hạn hoặc bị rách -> Đuổi khách về với lỗi 401 (Unauthorized)
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

package com.JavaSpringBoot.BESpring.Security;

import com.JavaSpringBoot.BESpring.Utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

//Dùng xác thực danh tính, quyền hạn
// OncePerRequestFilter: Đảm bảo mỗi lần khách gọi API, anh bảo vệ chỉ kiểm tra thẻ ĐÚNG 1 LẦN
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 0. KHU VỰC MIỄN KIỂM TRA: Nếu khách vào các đường dẫn bắt đầu bằng /auth (đăng ký, đăng nhập)
        // thì cho qua luôn, vì lúc này họ đã có thẻ đâu mà kiểm tra!
        if(path.startsWith("/user/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        // 1. LẤY TÚI ĐỰNG THẺ: Tìm trong Header xem khách có gửi kèm túi "Authorization" không.
        String header = request.getHeader("Authorization");

        // 2. KIỂM TRA TÚI: Nếu có túi và trong túi có thẻ loại "Bearer " (chuẩn quốc tế).
        if(header != null && header.startsWith("Bearer ")){
            // 3. RÚT THẺ: Cắt bỏ 7 ký tự đầu "Bearer " để lấy cái lõi Token loằng ngoằng.
            String token = header.substring(7);
            if(TokenBlackList.contains(token)){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            try {
                // 4. QUÉT THẺ: Dùng máy quét JwtUtil để đọc Tên và Chức vụ ghi trên thẻ.
                String username = JwtUtil.getUsername(token);
                String role = JwtUtil.getRole(token);

                // 5. LÀM CĂN CƯỚC TẠM THỜI:
                // Tạo một cái thẻ nội bộ (auth) chứa: Tên, Mật khẩu (null vì đã có token rồi), và Chức vụ.
                // Đoạn "ROLE_" + role giúp Spring hiểu đây là một vai trò chính thức.
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));

                // 6. BÁO CÁO BAN QUẢN LÝ: Nạp cái căn cước tạm thời này vào hệ thống của Spring Security.
                // Kể từ giây phút này, Spring sẽ công nhận người này là "Hợp lệ" cho đến khi xong việc.
                SecurityContextHolder.getContext().setAuthentication(auth);

                System.out.println("Anh bảo vệ xác nhận: Người dùng " + username + " mang quyền " + role + " đã vào cửa.");

            } catch (Exception e){
                // 7. ĐUỔI KHÁCH: Nếu thẻ giả, thẻ hết hạn, máy quét báo lỗi -> Chặn lại và báo lỗi 401.
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        // 8. ĐI TIẾP: Sau khi kiểm tra xong (hoặc không có thẻ để kiểm tra), cho khách tiếp tục hành trình.
        // Các lớp bảo vệ phía sau sẽ dựa vào cái "SecurityContextHolder" ở bước 6 để quyết định cho vào phòng hay không.
        filterChain.doFilter(request, response);
    }
}

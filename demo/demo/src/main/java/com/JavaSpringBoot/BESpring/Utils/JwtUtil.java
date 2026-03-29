package com.JavaSpringBoot.BESpring.Utils;
import io.jsonwebtoken.*;
import java.util.Date;

public class JwtUtil {

    // 1. CHÌA KHÓA BÍ MẬT: Dùng để "ký tên" lên thẻ (Token).
    // Chỉ server của bạn biết chuỗi này. Nếu lộ, hacker có thể tự tạo thẻ giả.
    private static final String SECRET = "phamminhhuy09012005tktayhoquangnamdanghocittaidanang";

    /**
     * HÀM TẠO THẺ (TOKEN)
     * Dùng khi người dùng đã đăng nhập thành công.
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                 // Ghi tên người dùng vào thẻ (Payload)
                .setIssuedAt(new Date())              // Ghi ngày cấp thẻ là ngay bây giờ
                // Ghi ngày hết hạn: Lấy giờ hiện tại + 86.400.000 milliseconds (tức là 24 giờ)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                // KÝ TÊN: Dùng thuật toán HS256 và chìa khóa "huy123" để xác thực thẻ này là thật
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();                           // Nén tất cả lại thành 1 chuỗi ký tự loằng ngoằng
    }

    /**
     * HÀM ĐỌC THẺ (TOKEN)
     * Dùng để kiểm tra xem cái thẻ người dùng gửi lên là thật hay giả và của ai.
     */
    public static String getUsername(String token) {
        return Jwts.parser()                          // Khởi động máy quét thẻ
                .setSigningKey(SECRET)                // Đưa chìa khóa bí mật vào để đối chiếu chữ ký
                .parseClaimsJws(token)                // Bắt đầu giải mã chuỗi loằng ngoằng
                .getBody()                            // Lấy phần nội dung của thẻ
                .getSubject();                        // Rút ra cái "Tên người dùng" đã ghi lúc nãy
    }
}

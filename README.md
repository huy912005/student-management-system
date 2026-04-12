1. JwtUtil làm gì?
   JwtUtil dùng để tạo và giải mã JWT token.
  Nó chứa các thông tin như username, thời gian hết hạn và chữ ký bảo mật.
  Khi user login thành công, hệ thống dùng JwtUtil để tạo token và gửi về client.
  Khi client gửi request, hệ thống dùng JwtUtil để lấy thông tin từ token.
2. wtFilter làm gì?
   JwtFilter là một filter trong Spring Security dùng để chặn mọi request đi vào hệ thống.
  Nó kiểm tra header Authorization có chứa token hay không.
  Nếu token hợp lệ thì cho request đi tiếp, nếu không thì trả về lỗi 401.
3. SecurityConfig làm gì?
   SecurityConfig dùng để cấu hình bảo mật cho toàn bộ hệ thống.
  Nó định nghĩa API nào được truy cập tự do, API nào cần xác thực.
  Đồng thời đăng ký JwtFilter vào hệ thống để xử lý token.

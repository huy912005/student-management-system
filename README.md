I. Security & Authentication
   1. JwtUtil

JwtUtil là lớp tiện ích dùng để tạo và xử lý JWT token.

Tạo token sau khi user đăng nhập thành công
Token chứa thông tin:
username
role
thời gian hết hạn
Dùng để giải mã token khi client gửi request

-Mục đích:

Xác thực người dùng (Authentication)
   2. JwtFilter

JwtFilter là một filter trong Spring Security, chạy trước khi request vào controller.

Lấy token từ header Authorization
Kiểm tra token hợp lệ hay không
Lấy thông tin user và role từ token
Set vào SecurityContext

-Mục đích:

Xác thực request
Gắn quyền cho user
   3. SecurityConfig

SecurityConfig dùng để cấu hình bảo mật cho hệ thống.

Cho phép truy cập public với /user/auth/**
Các API khác yêu cầu xác thực
Kích hoạt @PreAuthorize để phân quyền
Đăng ký JwtFilter

- Mục đích:

Kiểm soát truy cập API
   4. Password Security (BCrypt)
Mật khẩu được mã hóa bằng BCrypt trước khi lưu vào database
Không lưu mật khẩu dạng plain text

- Mục đích:

Bảo vệ thông tin người dùng
   5. Authorization (Phân quyền)
Sử dụng role (admin, user)
Dùng @PreAuthorize để kiểm soát quyền truy cập API

   6. Logout & Token Blacklist
Khi logout, token được thêm vào blacklist
Các request dùng token này sẽ bị từ chối

-Mục đích:

Vô hiệu hóa token
   7. Validation & Exception Handling
Sử dụng annotation như @NotBlank, @Min
Dùng GlobalExceptionHandler để xử lý lỗi

-Mục đích:

Bảo vệ hệ thống khỏi dữ liệu sai
   8. Pagination & Search
Sử dụng Pageable để phân trang
Tìm kiếm theo tên sinh viên
   9. API Documentation
Sử dụng Swagger để test API
---------------------------------------------------------------------------------
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

# Hệ Thống Quản Lý Sinh Viên - Spring Boot + React

## Công nghệ sử dụng

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT Authentication
* MySQL
* Swagger OpenAPI
* Lombok

### Frontend

* ReactJS
* React Router DOM
* React Query
* React Hook Form
* Zod Validation
* Axios
* React Toastify
* SweetAlert2

---

## Chức năng đã hoàn thành

### Authentication & Authorization

* Đăng nhập bằng Username và Password
* Mật khẩu được mã hóa bằng BCrypt
* Sinh JWT Token sau khi đăng nhập thành công
* Tự động gửi Token qua Axios Interceptor
* Logout bằng Blacklist Token
* Chặn truy cập trái phép bằng Spring Security
* Hỗ trợ phân quyền bằng Role

---

### Quản lý Sinh viên

* Thêm sinh viên
* Sửa sinh viên
* Xóa sinh viên
* Tìm kiếm sinh viên theo tên
* Phân trang danh sách sinh viên
* Validation dữ liệu đầu vào

---

### Upload Avatar

* Upload ảnh lên server
* Sinh tên file ngẫu nhiên bằng UUID
* Lưu ảnh trong thư mục uploads
* Truy cập ảnh thông qua URL

Ví dụ:

http://localhost:8080/uploads/abc.jpg

---

### Logging

* Mỗi request được gắn TraceId riêng
* Theo dõi log xuyên suốt vòng đời request
* Hỗ trợ debug hệ thống dễ dàng hơn

---

### Exception Handling

* Validation Exception
* Runtime Exception
* Global Exception Handler
* Trả về ApiResponse thống nhất cho Frontend

---

### Frontend Features

* Protected Route
* React Query Cache
* Debounce Search
* Skeleton Loading
* Toast Notification
* Modal CRUD
* Form Validation bằng Zod

---

## Cấu trúc Backend

Config

* SecurityConfig
* SwaggerConfig
* StaticResourceConfig
* LoggingFilter

Controller

* UserController
* SinhVienController

Service

* UserService
* SinhVienService
* ImageUploadService

Repository

* UserRepository
* SinhVienRepository

Security

* JwtFilter
* TokenBlackList

Utils

* JwtUtil

Exception

* GlobalExceptionHandler

---

## Cấu trúc Frontend

pages

* LoginPage
* AdminPage
* SinhVienPage

layouts

* AdminLayout

hooks

* useLoginMutation
* useSinhVienQuery
* useSaveSinhVien
* useDeleteSinhVien

services

* authService
* sinhvienService

components

* ProtectedRoute
* SkeletonRow

---

## Các kiến thức đã luyện tập

* REST API
* JWT Authentication
* Spring Security
* BCrypt
* Pagination
* Search
* File Upload
* React Query
* Axios Interceptor
* Form Validation
* Clean Architecture cơ bản

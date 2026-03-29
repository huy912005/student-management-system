package com.JavaSpringBoot.BESpring.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordUtils {
    public static String md5(String input) {
        try {
            // 1. Khởi tạo đối tượng 'md' đóng vai trò là "cỗ máy băm"
            // Chúng ta yêu cầu hệ thống cung cấp thuật toán "MD5"
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2. 'input.getBytes()' biến chuỗi mật khẩu (ví dụ: "123") thành mảng các byte dữ liệu
            // 'md.digest()' thực hiện quá trình băm (xay nhuyễn) mảng byte đó
            // Kết quả trả về một mảng byte mới gọi là 'messageDigest' (đây là bản mã thô)
            byte[] messageDigest = md.digest(input.getBytes());

            // 3. 'no' là một số nguyên siêu lớn (BigInteger)
            // Vì mảng byte 'messageDigest' không thể đọc trực tiếp, ta chuyển nó thành một con số
            // Tham số '1' đảm bảo con số này luôn là số dương
            BigInteger no = new BigInteger(1, messageDigest);

            // 4. 'hash' là biến chứa chuỗi ký tự sau khi chuyển con số 'no' sang hệ cơ số 16 (Hexadecimal)
            // Hệ 16 gồm các ký tự từ 0-9 và a-f, thường dùng để biểu diễn mã băm cho gọn
            String hash = no.toString(16);

            // 5. Chuẩn hóa độ dài: Mã MD5 tiêu chuẩn luôn phải có 32 ký tự
            // Đôi khi việc chuyển đổi sang hệ 16 làm mất các số 0 ở đầu, nên ta phải bù vào
            while (hash.length() < 32) {
                hash = "0" + hash; // Thêm số "0" vào trước chuỗi cho đến khi đủ 32 ký tự
            }

            // Trả về chuỗi mật khẩu đã được mã hóa hoàn toàn (ví dụ: e10adc3949ba59abbe56e057f20f883e)
            return hash;

        } catch (Exception e) {
            // Nếu có lỗi (ví dụ sai tên thuật toán), in ra lỗi và trả về null
            e.printStackTrace();
            return null;
        }
    }
}

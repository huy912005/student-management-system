package com.JavaSpringBoot.BESpring.Repository;

import com.JavaSpringBoot.BESpring.Entity.UserEnitity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEnitity,Long> {
//    UserEnitity findByUsernameAndPassword(String username, String password);
//    [Hành động] + By + [Tên thuộc tính trong Entity] + [Điều kiện (Keyword)]
//Hành động (Prefix): Thường bắt đầu bằng find (tìm kiếm), count (đếm số lượng), exists (kiểm tra tồn tại), delete (xóa).
//Chữ By: Bắt buộc phải có để ngăn cách hành động và tên cột.
    UserEnitity findByUsername(String username);
}


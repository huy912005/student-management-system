package com.JavaSpringBoot.BESpring.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String noiLuu="F:/2025-2026/2026/java/New folder/";
        //tạo folder nếu chưa có
        File folder = new File(noiLuu);
        if(!folder.exists()){
            folder.mkdir();
        }
        //đường dẫn lưu file
        String path = noiLuu+file.getOriginalFilename();
        //lưu file
        file.transferTo(new File(path));
        return "upload thành công : "+path;
    }
}

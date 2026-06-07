package com.JavaSpringBoot.BESpring.Controller;

import com.JavaSpringBoot.BESpring.Service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final ImageUploadService imageUploadService;
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            String saveFileName = imageUploadService.uploadImage(file);
            return ResponseEntity.ok("Upload thành công : " + saveFileName);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Upload thất bại : "+e.getMessage());
        }
    }
}

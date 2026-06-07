package com.JavaSpringBoot.BESpring.Controller;

import com.JavaSpringBoot.BESpring.Service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @DeleteMapping("/clean")
    public ResponseEntity<String> clean(){
        imageUploadService.cleanupOrphanImages();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

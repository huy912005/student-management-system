package com.JavaSpringBoot.BESpring.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    String uploadImage(MultipartFile file);
    void deleteImage(String fileName);
    void cleanupOrphanImages();
}

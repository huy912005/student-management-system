package com.JavaSpringBoot.BESpring.Service.impl;

import com.JavaSpringBoot.BESpring.Service.ImageUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {
    @Value("${file.upload-dir:uploads}")
    private String UPLOAD_DIR ;
    public String uploadImage(MultipartFile file)  {
        try {
            String fileName = file.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID()+"_"+fileName;
            Path path = Paths.get(UPLOAD_DIR);
            if(!Files.exists(path)){
                Files.createDirectories(path);
            }
            Path filePath = path.resolve(uniqueFileName);
            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );
            return uniqueFileName;
        }catch (IOException e){
            throw new RuntimeException("Thất bại");
        }
    }
}

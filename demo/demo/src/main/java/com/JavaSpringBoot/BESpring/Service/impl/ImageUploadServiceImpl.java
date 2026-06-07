package com.JavaSpringBoot.BESpring.Service.impl;

import com.JavaSpringBoot.BESpring.Repository.SinhVIenRepository;
import com.JavaSpringBoot.BESpring.Service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ImageUploadServiceImpl implements ImageUploadService {
    @Value("${file.upload-dir:uploads}")
    private String UPLOAD_DIR ;
    private final SinhVIenRepository sinhVIenRepository;
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

    @Override
    public void deleteImage(String fileName) {
        try {
            Path path = Paths.get(UPLOAD_DIR);
            Path filePath = path.resolve(fileName);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi xóa ảnh!");
        }
    }

    @Override
    public void cleanupOrphanImages() {
        Set<String> avatarSet = new HashSet<>(sinhVIenRepository.getAllAvatar());
        Path path = Paths.get(UPLOAD_DIR);
        if(!Files.exists(path)){
            return;
        }
        try (Stream<Path> files = Files.list(path)){
            files.forEach(file -> {
                if(!Files.isRegularFile(file)){
                    return;
                }
                String fileName = file.getFileName().toString();
                if(!avatarSet.contains(fileName)) {
                    deleteImage(fileName);
                }
            });
        }catch (IOException e){
            throw new RuntimeException("Lỗi dọn ảnh mồ côi");
        }
    }
}

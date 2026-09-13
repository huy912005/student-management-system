package com.JavaSpringBoot.BESpring.scheduler;

import com.JavaSpringBoot.BESpring.Service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageCleanupScheduler {
    private final ImageUploadService imageUploadService;
    @Scheduled(cron = "0 0 2 * * *")
    public void cleanupImages() {
        imageUploadService.cleanupOrphanImages();
    }
}

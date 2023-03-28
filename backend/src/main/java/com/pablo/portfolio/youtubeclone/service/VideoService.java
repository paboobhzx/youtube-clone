package com.pablo.portfolio.youtubeclone.service;

import com.pablo.portfolio.youtubeclone.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {
    @Autowired
    private final S3Service s3Service;
    public void uploadVideo(MultipartFile multipartFile){
        String videoUrl = s3Service.uploadVideo(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);

    }
}

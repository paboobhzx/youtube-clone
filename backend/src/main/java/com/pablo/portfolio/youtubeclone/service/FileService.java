package com.pablo.portfolio.youtubeclone.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadVideo(MultipartFile file);
}

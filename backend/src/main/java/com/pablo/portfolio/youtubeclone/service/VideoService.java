package com.pablo.portfolio.youtubeclone.service;

import com.amazonaws.services.s3.transfer.Upload;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pablo.portfolio.youtubeclone.dto.UploadVideoResponse;
import com.pablo.portfolio.youtubeclone.dto.VideoDto;
import com.pablo.portfolio.youtubeclone.model.Video;
import com.pablo.portfolio.youtubeclone.repository.IVideoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.management.Query;
import java.lang.annotation.Target;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final S3Service s3Service;
    private final IVideoRepository videoRepository;
    public UploadVideoResponse uploadVideo(MultipartFile multipartFile){
        String videoUrl = s3Service.uploadVideo(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);

        Video savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId(),savedVideo.getVideoUrl());

    }

    public VideoDto editVideo(VideoDto videoDto) {
        Video savedVideo = getVideoById(videoDto.getId());

        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setTags(videoDto.getTags());
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());

        videoRepository.save(savedVideo);
        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        Video savedVideo = getVideoById(videoId);
        String thumbnailUrl = s3Service.uploadVideo(file);
        savedVideo.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(savedVideo);
        return thumbnailUrl;
    }

    Video getVideoById(String videoId){
        Video savedVideo = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by id: " + videoId));
        return savedVideo;

    }

    public VideoDto getVideoDetails(String videoId) {
        Video savedVideo = getVideoById(videoId);
        VideoDto videoDto = new VideoDto();
        videoDto.setVideoUrl(savedVideo.getVideoUrl());
        videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
        videoDto.setId(savedVideo.getId());
        videoDto.setTitle(savedVideo.getTitle());
        videoDto.setDescription(savedVideo.getDescription());
        videoDto.setTags(savedVideo.getTags());
        videoDto.setVideoStatus(savedVideo.getVideoStatus());

        return videoDto;
    }
}

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
    private final UserService userService;
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
        VideoDto videoDto = new VideoDto();
        try
        {
            Video savedVideo = getVideoById(videoId);

            videoDto.setVideoUrl(savedVideo.getVideoUrl());
            videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
            videoDto.setId(savedVideo.getId());
            videoDto.setTitle(savedVideo.getTitle());
            videoDto.setDescription(savedVideo.getDescription());
            videoDto.setTags(savedVideo.getTags());
            videoDto.setVideoStatus(savedVideo.getVideoStatus());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


        return videoDto;
    }

    public VideoDto likeVideo(String videoId) {
        //Get video by ID
        Video videoById = getVideoById(videoId);

        //INcrement like count
        //If user already liked the video, then decrement like count
        //like 0, dislike - 0
        //like 1, dislike - 0
        //like 0, dislike 0
        //like 0, dislike 1
        //like1, dislike 0

        if(userService.ifLikedVideo(videoId)){
            videoById.decrementLikes();
            userService.removeFromLikedVideos(videoId);
        } else if(userService.ifDisLikedVideo(videoId)){
            videoById.decrementDislikes();
            userService.removeFromDislikedVideos(videoId);
            videoById.incrementLikes();
            userService.addToLikedVideos(videoId);
        } else {
            videoById.incrementLikes();
            userService.addToLikedVideos(videoId);
        }

        VideoDto videoDto = new VideoDto();
        videoDto.setVideoUrl(videoById.getVideoUrl());
        videoDto.setThumbnailUrl(videoById.getThumbnailUrl());
        videoDto.setId(videoById.getId());
        videoDto.setTitle(videoById.getTitle());
        videoDto.setDescription(videoById.getDescription());
        videoDto.setTags(videoById.getTags());
        videoDto.setVideoStatus(videoById.getVideoStatus());
        videoDto.setLikedCount(videoById.getLikes().get());
        videoDto.setDislikedCount(videoById.getDislikes().get());

        //If user already disliked the video, then increment like count and decrement dislike count
        return null;
    }
}

package com.pablo.portfolio.youtubeclone.controller;

import com.pablo.portfolio.youtubeclone.dto.UploadVideoResponse;
import com.pablo.portfolio.youtubeclone.dto.VideoDto;
import com.pablo.portfolio.youtubeclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;



    @PostMapping
    //@CrossOrigin(origins = "http://localhost:8080")
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam("file")MultipartFile file){
        return videoService.uploadVideo(file);

    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail
            (@RequestParam("file")MultipartFile file, @RequestParam("videoId") String videoId){
        return videoService.uploadThumbnail(file, videoId);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetaData(@RequestBody VideoDto videoDto){
        return videoService.editVideo(videoDto);
    }



    @GetMapping("/{videoId}")
    public VideoDto getVideoDetails(@PathVariable String videoId){
        return videoService.getVideoDetails(videoId);
    }

}

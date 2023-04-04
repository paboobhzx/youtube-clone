package com.pablo.portfolio.youtubeclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UploadVideoResponse {

    public String videoId;
    public String videoUrl;
}

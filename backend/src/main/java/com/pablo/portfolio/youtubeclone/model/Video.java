package com.pablo.portfolio.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@Document(value = "Video")
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    private String Id;
    private String title;
    private String description;
    private String userId;
    private Integer likes;
    private Integer dislikes;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String thumbnailUrl;
    private List<Comment> commentList;
}

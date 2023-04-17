package com.pablo.portfolio.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Document(value = "Video")
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private AtomicInteger likes;
    private AtomicInteger dislikes;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String thumbnailUrl;
    private List<Comment> commentList;

    public  void incrementLikes(){
        likes.incrementAndGet();
    }
    public  void decrementLikes(){
        likes.decrementAndGet();
    }

    public  void  incrementDislikes(){
        dislikes.incrementAndGet();
    }

    public  void  decrementDislikes(){
        dislikes.decrementAndGet();
    }
}

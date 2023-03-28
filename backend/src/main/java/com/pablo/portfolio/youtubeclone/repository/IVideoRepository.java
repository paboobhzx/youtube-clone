package com.pablo.portfolio.youtubeclone.repository;

import com.pablo.portfolio.youtubeclone.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoRepository extends MongoRepository<Video,String> {
}

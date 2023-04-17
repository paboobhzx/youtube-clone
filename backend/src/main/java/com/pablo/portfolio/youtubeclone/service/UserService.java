package com.pablo.portfolio.youtubeclone.service;

import com.pablo.portfolio.youtubeclone.model.User;
import com.pablo.portfolio.youtubeclone.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

    public User getCurrentUser(){
        var currUser = (Jwt)SecurityContextHolder.getContext().getAuthentication().getCredentials();
        User userLogged = new User();

        return null;
        }

    public void addToLikedVideos(String videoId) {
        var currUser = getCurrentUser();
        currUser.addToLikedVideos(videoId);
        userRepository.save(currUser);
    }

    public boolean ifLikedVideo(String videoId){
        return getCurrentUser().getLikedVideos().stream().anyMatch(likedVideo -> likedVideo.equals(videoId));
    }

    public boolean ifDisLikedVideo(String videoId){
        return getCurrentUser().getDislikedVideos().stream().anyMatch(likedVideo -> likedVideo.equals(videoId));
    }

    public void removeFromLikedVideos(String videoId) {
        var currUser = getCurrentUser();
        currUser.removeFromLikedVideos(videoId);
        userRepository.save(currUser);
    }

    public void removeFromDislikedVideos(String videoId) {
        var currUser = getCurrentUser();
        currUser.removeFromDislikedVideos(videoId);
        userRepository.save(currUser);
    }
}


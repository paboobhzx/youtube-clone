package com.pablo.portfolio.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@Document(value = "User")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String Id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private Set<String> subscribedToUsers;
    private Set<String> subscribers;
    private List<String> videoHistory;
    private List<String> likedVideos;
    private List<String> dislikedVideos;
}

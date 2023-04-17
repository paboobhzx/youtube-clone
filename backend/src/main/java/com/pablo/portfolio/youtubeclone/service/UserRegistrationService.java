package com.pablo.portfolio.youtubeclone.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablo.portfolio.youtubeclone.dto.UserInfoDto;
import com.pablo.portfolio.youtubeclone.model.User;
import com.pablo.portfolio.youtubeclone.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    @Value("{auth0.userInfoEndpoint}")
    private String userInfoEndpoint;

    private final IUserRepository userRepository;
    public String registerUser(String tokenValue) {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(userInfoEndpoint))
                .setHeader("Authorization", String.format("Bearer %s", tokenValue))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        try {
            HttpResponse<String> responseString = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String body = responseString.body();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserInfoDto userInfoDTO = objectMapper.readValue(body, UserInfoDto.class);

            Optional<User> userBySubject = userRepository.findById(userInfoDTO.getId());
            if(userBySubject.isPresent()){
                return userBySubject.get().getId();
            } else {
                User user = new User();
                user.setFirstName(userInfoDTO.getGivenName());
                user.setLastName(userInfoDTO.getFamilyName());
                user.setFullName(userInfoDTO.getName());
                user.setEmailAddress(userInfoDTO.getEmail());
                user.setSub(userInfoDTO.getSub());

                return userRepository.save(user).getId();
            }

        } catch (Exception exception) {
            throw new RuntimeException("Exception occurred while registering user", exception);
        }
    }
}

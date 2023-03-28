package com.pablo.portfolio.youtubeclone.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;
import java.util.UUID;


@Service
public class S3Service implements FileService{

    public static final String BUCKET_NAME = "pabloawsyoutubeclone";
    private final AmazonS3Client awsS3Client;

    @Autowired
    public S3Service(AmazonS3Client awsS3Client) {
        this.awsS3Client = awsS3Client;
    }

    @Override
    public String uploadVideo(MultipartFile file){

        var fileNameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID().toString() +  "." + fileNameExtension;
        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try{
            awsS3Client.putObject(BUCKET_NAME,key,file.getInputStream(),metadata);
        } catch(IOException ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception occurred during upload");
        }
        awsS3Client.setObjectAcl(BUCKET_NAME,key, CannedAccessControlList.PublicRead);
        return awsS3Client.getResourceUrl(BUCKET_NAME,key);

    }
}

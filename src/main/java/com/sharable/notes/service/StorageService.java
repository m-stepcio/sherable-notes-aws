package com.sharable.notes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${aws.region}")
    private String region;
    @Value("${aws.storage.bucket}")
    private String bucketName;
    @Value("${env}")
    private String envName;

    public void createPresignedUrl(){

    }

    private String generateKey(){
        return envName + "/notes/" + UUID.randomUUID();
    }


    public void uploadFile(InputStream inputStream){
        int size=0;
        S3Client s3Client = S3Client
                .builder()
                .region(Region.of(region))
                .build();
        Map<String, String> metadata = new HashMap<>();
        s3Client.putObject(request ->
                        request.bucket(bucketName)
                                .key(generateKey())
                                .metadata(metadata)
                                .ifNoneMatch("*"),
                RequestBody.fromInputStream(inputStream, size)
        );
    }
}

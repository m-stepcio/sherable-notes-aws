package com.sharable.notes.service;

import com.sharable.auth.model.User;
import com.sharable.notes.config.StorageConfigProvider;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.awscore.presigner.PresignedRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

@Service
public class StorageService {

    private StorageConfigProvider storageConfigProvider;

    public StorageService(StorageConfigProvider storageConfigProvider) {
        this.storageConfigProvider = storageConfigProvider;
    }
}

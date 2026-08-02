package com.sharable.notes.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.time.Duration;

@Configuration
public class S3Config {
    @Getter
    @Value("${aws.region}")
    private String region;
    @Getter
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Value("${aws.s3.signature_duration}")
    private String signatureDuration;


    @Bean
    public S3Client s3Client(){
        return S3Client
                .builder()
                .region(Region.of(region))
                .build();
    }

    public Duration getSignatureDuration() {
        return Duration.ofMinutes(Integer.parseInt(signatureDuration));
    }
}

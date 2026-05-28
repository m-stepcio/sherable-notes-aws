package com.sharable.notes.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;

import static software.amazon.awssdk.utils.StringUtils.isBlank;

@Configuration
public class DynamoDbConfig {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.dynamodb.endpoint:}")
    private String endpoint;

    @Bean
    public DynamoDbClient dynamoDbClient(){
        DynamoDbClientBuilder builder =  DynamoDbClient.builder()
                .region(Region.of(region));
        if(isBlank(endpoint)){
            return builder.build();
        }
        return builder.endpointOverride(URI.create(endpoint)).build();
    }

}

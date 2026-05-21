package com.sharable.notes.config;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.Bucket;

public class StorageConfigProvider {
    Region region;
    Bucket bucket;
}

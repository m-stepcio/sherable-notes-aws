package com.sharable.notes.service;

import com.sharable.auth.model.User;
import com.sharable.notes.dto.CreateNoteRequest;
import com.sharable.notes.model.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NoteService {
    private final StorageService storageService;
    private final DynamoDbClient dynamoDbClient;

//    Map<String, String> metadata = new HashMap<>();
//        s3Client.putObject(request ->
//            request.bucket(bucketName)
//            .key(generateKey())
//            .metadata(metadata)
//                                .ifNoneMatch("*"),
//                RequestBody.fromInputStream(inputStream, size)

    public void createNote(CreateNoteRequest request, User user){
        Note.builder()
                .title(request.name())
                .fileId(UUID.randomUUID())
                .ownerId(user.getId())
                .creationTime(Instant.now())
                .size(request.size())
                .fileType(request.noteType())
                .version(1)
                .lastModifyBy(user.getId())
                .build();
    }
}

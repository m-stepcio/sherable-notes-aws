package com.sharable.notes.service;

import com.sharable.auth.model.User;
import com.sharable.notes.model.CreateNoteRequest;
import com.sharable.notes.model.Note;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class NoteService {
    private final StorageService storageService;
    private final DynamoConnectionService dynamoConnectionService;


    public


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

        d

    }
}

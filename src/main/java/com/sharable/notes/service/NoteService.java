package com.sharable.notes.service;

import com.sharable.auth.model.User;
import com.sharable.notes.model.CreateNoteRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NoteService {
    private final StorageService storageService;
    private final DynamoConnectionService dynamoConnectionService;


    public


    public void createNote(CreateNoteRequest request, User user){
        Instant instant = Instant.now();

    }
}

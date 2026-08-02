package com.sharable.notes.service;

import com.sharable.notes.model.Note;
import com.sharable.notes.model.NotePage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DynamoDbService {
    private final DynamoDbClient dynamoDbClient;

    public void createNote(Note note){

    }

    public Optional<Note> getNote(String ownerId, UUID noteId){
        return Optional.empty();
    }

    public NotePage listNotes(String ownerId, int limit, String cursor){
        return null;
    }

    public void updateNote(Note note, long expectedVersion){}

    public void deleteNote(String ownerId, UUID noteId){}

    private PutItemRequest createPutItemRequest(){
        PutItemRequest.builder().
    }

}

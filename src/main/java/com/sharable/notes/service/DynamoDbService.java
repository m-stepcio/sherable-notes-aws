package com.sharable.notes.service;

import com.sharable.notes.model.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Service
@AllArgsConstructor
public class DynamoDbService {
    private final DynamoDbClient dynamoDbClient;

    public void createNewNote(Note note){
    }

}

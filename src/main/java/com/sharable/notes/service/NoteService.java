package com.sharable.notes.service;

import com.sharable.auth.model.User;
import com.sharable.exception.ValidationException;
import com.sharable.notes.dto.CreateNoteRequest;
import com.sharable.notes.model.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.utils.StringUtils;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static software.amazon.awssdk.utils.StringUtils.isBlank;
import static software.amazon.awssdk.utils.StringUtils.isNotBlank;

@Service
@AllArgsConstructor
public class NoteService {

    private final StorageService storageService;
    private final DynamoDbService dynamoDbService;



    public void createNote(CreateNoteRequest request, User user){
        String directory = parseDirectory(request.directory());
        validateDirectory(directory);

        Note.NoteBuilder noteBuilder = Note.builder()
                .title(request.name())
                .ownerId(user.getId())
                .creationTime(Instant.now())
                .size(request.size())
                .fileType(request.noteType())
                .version(1)
                .lastModifyBy(user.getId())
                .directory(directory);

        dynamoDbService.createNewNote(noteBuilder.build());
        storageService.createPresignedUrl();
    }



    private String parseDirectory(String directory){

        if(isBlank(directory)){
            return "/";
        }

        StringBuilder directoryBuilder = new StringBuilder();
        Arrays.stream(directory.split("/"))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .forEach(fragment -> {
                    directoryBuilder.append("/");
                    directoryBuilder.append(fragment);
                });
        directoryBuilder.append("/");
        return directoryBuilder.toString();
    }

    private void validateDirectory(String directory){
        Pattern pattern = Pattern.compile(
                "^/(?:[\\p{L}\\p{N}_-]+(?: +[\\p{L}\\p{N}_-]+)*/)*$"
        );
        if(!pattern.matcher(directory).matches()){
            throw new ValidationException("Invalid directory pattern");
        }
    }
}

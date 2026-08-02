package com.sharable.notes.service;

import com.sharable.auth.model.User;
import com.sharable.enums.FileStatus;
import com.sharable.exception.ValidationException;
import com.sharable.notes.dto.CreateNoteRequest;
import com.sharable.notes.dto.CreateNoteResponse;
import com.sharable.notes.model.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.utils.StringUtils;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

import static software.amazon.awssdk.utils.StringUtils.isBlank;

@Service
@AllArgsConstructor
public class NoteService {

    private final StorageService storageService;
    private final DynamoDbService dynamoDbService;


    public CreateNoteResponse createNote(CreateNoteRequest request, User user){
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
                .directory(directory)
                .fileStatus(FileStatus.PENDING);

        String objectName = UUID.randomUUID().toString();
        noteBuilder.objectName(objectName);

        dynamoDbService.createNewNote(noteBuilder.build());
        String presignedUrl = storageService.createPresignedUrl(objectName);
        return new CreateNoteResponse(presignedUrl);
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

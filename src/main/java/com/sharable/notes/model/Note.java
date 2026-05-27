package com.sharable.notes.model;

import com.sharable.enums.NoteType;
import lombok.Builder;

import java.time.Instant;

@Builder
public class Note {
    private String title;
    private String  fileId;
    private String ownerId;
    private Instant creationTime;
    private Integer size;
    private NoteType fileType;
    private long version;
    private String lastModifyBy;
}

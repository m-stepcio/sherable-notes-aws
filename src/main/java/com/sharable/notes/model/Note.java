package com.sharable.notes.model;

import com.sharable.enums.NoteType;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public class Note {
    private String title;
    private UUID fileId;
    private String ownerId;
    private Instant creationTime;
    private long size;
    private NoteType fileType;
    private long version;
    private String lastModifyBy;
}

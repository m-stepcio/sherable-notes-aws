package com.sharable.notes.model;

import com.sharable.enums.FileStatus;
import com.sharable.enums.NoteType;
import lombok.Builder;

import java.time.Instant;

@Builder
public class Note {
    private String title;
    private String fileId;
    private String ownerId;
    private Instant creationTime;
    private long size;
    private String directory;
    private NoteType fileType;
    private long version;
    private String lastModifyBy;
    private FileStatus fileStatus;
    private String objectName;
    private boolean isLatest;
    private String rootNoteId;
}

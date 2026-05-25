package com.sharable.notes.model;

import com.sharable.enums.FileType;

import java.time.Instant;

public class Note {
    private String title;
    private byte[] content;
    private String ownerId;
    private Instant creationTime;
    private Integer size;
    private FileType fileType;
}

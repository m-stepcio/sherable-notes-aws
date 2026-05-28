package com.sharable.notes.dto;

import com.sharable.enums.NoteType;

import java.io.InputStream;

public record CreateNoteRequest(
         String name,
         InputStream content,
         long size,
         NoteType noteType
) {

}

package com.sharable.notes.model;

import com.sharable.enums.NoteType;

import java.io.InputStream;

public record CreateNoteRequest(
         String name,
         InputStream content,
         long size,
         NoteType noteType
) {

}

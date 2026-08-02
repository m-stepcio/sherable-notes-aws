package com.sharable.notes.dto;

import com.sharable.enums.NoteType;


public record CreateNoteRequest(
         String name,
         String directory,
         long size,
         NoteType noteType
) {

}

package com.sharable.notes.model;

public record CreateNoteRequest(
         String ownerId,
         String title,
         byte[] content
) {

}

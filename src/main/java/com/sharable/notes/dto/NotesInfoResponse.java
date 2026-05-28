package com.sharable.notes.dto;

import com.sharable.notes.model.Note;
import lombok.Data;

import java.util.List;

@Data
public class NotesInfoResponse {
    List<Note> notesInfo;
}

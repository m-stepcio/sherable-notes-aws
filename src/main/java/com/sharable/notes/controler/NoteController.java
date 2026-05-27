package com.sharable.notes.controler;


import com.sharable.notes.model.CreateNoteRequest;
import com.sharable.notes.model.Note;
import com.sharable.notes.response.CreateResponse;
import com.sharable.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/notes")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping()
    public CreateResponse createNote(@RequestParam CreateNoteRequest note){
        noteService.createNote(note);
        return new CreateResponse();
    }
}

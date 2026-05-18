package com.sharable.notes.controler;


import com.sharable.notes.response.CreateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/notes")
public class NoteController {

    @PostMapping()
    public CreateResponse createNote(){

    }
}

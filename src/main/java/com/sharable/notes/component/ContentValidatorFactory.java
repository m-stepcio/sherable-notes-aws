package com.sharable.notes.component;

import com.sharable.enums.NoteType;

public class ContentValidatorFactory {
    ContentValidator getContentValidator(NoteType noteType){
        switch (noteType){
            case PLAIN_TEXT: return new PlainTextNote();
            case MARKDOWN: return new
        }
    }
}

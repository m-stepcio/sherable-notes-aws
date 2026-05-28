package com.sharable.notes.component;

import com.sharable.enums.NoteType;

public class ContentValidatorFactory {
    ContentValidator getContentValidator(NoteType noteType) throws Exception{
        return switch (noteType) {
            case PLAIN_TEXT -> new PlainTextNote();
            case MARKDOWN -> new MarkdownValidator();
            default -> throw new Exception("There is no validator implementation for type " + noteType);
        };
    }
}

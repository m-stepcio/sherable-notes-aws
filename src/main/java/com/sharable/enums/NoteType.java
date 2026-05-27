package com.sharable.enums;

import lombok.Getter;

@Getter
public enum NoteType {
    MARKDOWN("markdown"),
    PLAIN_TEXT("plain text");

    NoteType(String name) {
        this.name = name;
    }

    private String name;
}

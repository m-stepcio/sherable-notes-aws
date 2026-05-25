package com.sharable.enums;

import lombok.Getter;

@Getter
public enum FileType {
    MARKDOWN("markdown"),
    PLAIN_TEXT("plain text");

    FileType(String name) {
        this.name = name;
    }

    private String name;
}

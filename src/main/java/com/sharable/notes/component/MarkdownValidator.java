package com.sharable.notes.component;

import java.io.InputStream;

public class MarkdownValidator implements ContentValidator{
    @Override
    public boolean validate(InputStream inputStream) {
        return false;
    }

}

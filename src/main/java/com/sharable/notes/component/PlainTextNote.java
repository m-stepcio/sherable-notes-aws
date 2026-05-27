package com.sharable.notes.component;

import java.io.InputStream;

public class PlainTextNote implements ContentValidator{

    @Override
    public boolean validate(InputStream inputStream) {
        return true;
    }
}

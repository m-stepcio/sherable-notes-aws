package com.sharable.notes.component;

import java.io.InputStream;

public interface ContentValidator {
    boolean validate(InputStream inputStream);
}

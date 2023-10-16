package cz.msebera.android.http;

import java.nio.charset.CharacterCodingException;

public class MessageConstraintException extends CharacterCodingException {
    private final String message;

    public MessageConstraintException(String str) {
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }
}

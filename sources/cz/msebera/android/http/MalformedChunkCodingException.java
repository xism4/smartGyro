package cz.msebera.android.http;

import java.io.IOException;

public class MalformedChunkCodingException extends IOException {
    public MalformedChunkCodingException(String str) {
        super(str);
    }
}

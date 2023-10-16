package cz.msebera.android.http;

public class TruncatedChunkException extends MalformedChunkCodingException {
    public TruncatedChunkException(String str) {
        super(str);
    }
}

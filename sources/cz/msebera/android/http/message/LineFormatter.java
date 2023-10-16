package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.mime.CharArrayBuffer;

public interface LineFormatter {
    CharArrayBuffer formatHeader(CharArrayBuffer charArrayBuffer, Header header);

    CharArrayBuffer formatRequestLine(CharArrayBuffer charArrayBuffer, RequestLine requestLine);
}

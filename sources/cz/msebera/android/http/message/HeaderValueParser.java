package cz.msebera.android.http.message;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.mime.CharArrayBuffer;

public interface HeaderValueParser {
    HeaderElement[] parseElements(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2);

    HeaderElement parseHeaderElement(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2);
}

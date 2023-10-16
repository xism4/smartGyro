package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.mime.CharArrayBuffer;

public interface LineParser {
    boolean hasProtocolVersion(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2);

    Header parseHeader(CharArrayBuffer charArrayBuffer);

    StatusLine parseStatusLine(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2);
}

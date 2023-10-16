package cz.msebera.android.http;

import cz.msebera.android.http.mime.CharArrayBuffer;

public interface FormattedHeader extends Header {
    CharArrayBuffer getBuffer();

    int getValuePos();
}

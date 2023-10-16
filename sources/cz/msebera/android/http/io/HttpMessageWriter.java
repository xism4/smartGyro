package cz.msebera.android.http.io;

import c.a.a.a.q;
import cz.msebera.android.http.HttpMessage;

public interface HttpMessageWriter<T extends q> {
    void write(HttpMessage httpMessage);
}

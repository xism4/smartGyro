package cz.msebera.android.http.io;

import c.a.a.a.q;
import cz.msebera.android.http.HttpMessage;

public interface HttpMessageParser<T extends q> {
    HttpMessage parse();
}

package cz.msebera.android.http.entity;

import cz.msebera.android.http.HttpMessage;

public interface ContentLengthStrategy {
    long determineLength(HttpMessage httpMessage);
}

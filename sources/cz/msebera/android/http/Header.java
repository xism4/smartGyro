package cz.msebera.android.http;

public interface Header {
    HeaderElement[] getElements();

    String getName();

    String getValue();
}

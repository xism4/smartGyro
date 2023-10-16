package cz.msebera.android.http;

import java.util.Iterator;

public interface TokenIterator extends Iterator<Object> {
    boolean hasNext();

    String nextToken();
}

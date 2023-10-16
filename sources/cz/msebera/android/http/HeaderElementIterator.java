package cz.msebera.android.http;

import java.util.Iterator;

public interface HeaderElementIterator extends Iterator<Object> {
    boolean hasNext();

    HeaderElement nextElement();
}

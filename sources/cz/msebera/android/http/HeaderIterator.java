package cz.msebera.android.http;

import java.util.Iterator;

public interface HeaderIterator extends Iterator<Object> {
    boolean hasNext();

    Header nextHeader();
}

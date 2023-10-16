package cz.msebera.android.http.message;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HeaderElementIterator;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.NoSuchElementException;

public class BasicHeaderElementIterator implements HeaderElementIterator {
    private CharArrayBuffer buffer;
    private HeaderElement currentElement;
    private CharArrayBuffer cursor;
    private final HeaderIterator headerIt;
    private final HeaderValueParser parser;

    public BasicHeaderElementIterator(HeaderIterator headerIterator) {
        this(headerIterator, BasicHeaderValueParser.INSTANCE);
    }

    public BasicHeaderElementIterator(HeaderIterator $r1, HeaderValueParser $r2) {
        this.currentElement = null;
        this.buffer = null;
        this.cursor = null;
        Args.notNull($r1, "Header iterator");
        this.headerIt = $r1;
        Args.notNull($r2, "Parser");
        this.parser = $r2;
    }

    private void bufferHeaderValue() {
        this.cursor = null;
        this.buffer = null;
        while (this.headerIt.hasNext()) {
            Header $r2 = this.headerIt.nextHeader();
            if ($r2 instanceof FormattedHeader) {
                FormattedHeader $r3 = (FormattedHeader) $r2;
                this.buffer = $r3.getBuffer();
                this.cursor = new CharArrayBuffer(0, this.buffer.length());
                this.cursor.append($r3.getValuePos());
                return;
            }
            String $r6 = $r2.getValue();
            if ($r6 != null) {
                this.buffer = new CharArrayBuffer($r6.length());
                this.buffer.append($r6);
                this.cursor = new CharArrayBuffer(0, this.buffer.length());
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseNextElement() {
        /*
            r9 = this;
        L_0x0000:
            cz.msebera.android.http.HeaderIterator r0 = r9.headerIt
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x000e
            cz.msebera.android.http.message.CharArrayBuffer r2 = r9.cursor
            if (r2 == 0) goto L_0x000d
            goto L_0x000e
        L_0x000d:
            return
        L_0x000e:
            cz.msebera.android.http.message.CharArrayBuffer r2 = r9.cursor
            if (r2 == 0) goto L_0x0018
            boolean r1 = r2.atEnd()
            if (r1 == 0) goto L_0x001b
        L_0x0018:
            r9.bufferHeaderValue()
        L_0x001b:
            cz.msebera.android.http.message.CharArrayBuffer r2 = r9.cursor
            if (r2 == 0) goto L_0x0000
        L_0x001f:
            cz.msebera.android.http.message.CharArrayBuffer r2 = r9.cursor
            boolean r1 = r2.atEnd()
            if (r1 != 0) goto L_0x0044
            cz.msebera.android.http.message.HeaderValueParser r3 = r9.parser
            cz.msebera.android.http.mime.CharArrayBuffer r4 = r9.buffer
            cz.msebera.android.http.message.CharArrayBuffer r2 = r9.cursor
            cz.msebera.android.http.HeaderElement r5 = r3.parseHeaderElement(r4, r2)
            java.lang.String r6 = r5.getName()
            int r7 = r6.length()
            if (r7 != 0) goto L_0x0041
            java.lang.String r6 = r5.getValue()
            if (r6 == 0) goto L_0x001f
        L_0x0041:
            r9.currentElement = r5
            return
        L_0x0044:
            cz.msebera.android.http.message.CharArrayBuffer r2 = r9.cursor
            boolean r1 = r2.atEnd()
            if (r1 == 0) goto L_0x0000
            r8 = 0
            r9.cursor = r8
            r8 = 0
            r9.buffer = r8
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.message.BasicHeaderElementIterator.parseNextElement():void");
    }

    public boolean hasNext() {
        if (this.currentElement == null) {
            parseNextElement();
        }
        return this.currentElement != null;
    }

    public final Object next() {
        return nextElement();
    }

    public HeaderElement nextElement() {
        if (this.currentElement == null) {
            parseNextElement();
        }
        HeaderElement $r1 = this.currentElement;
        if ($r1 != null) {
            this.currentElement = null;
            return $r1;
        }
        throw new NoSuchElementException("No more header elements available");
    }

    public void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}

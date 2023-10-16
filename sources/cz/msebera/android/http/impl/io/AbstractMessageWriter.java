package cz.msebera.android.http.impl.io;

import c.a.a.a.j.d;
import c.a.a.a.q;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.message.BasicLineFormatter;
import cz.msebera.android.http.message.LineFormatter;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.util.HttpParams;

public abstract class AbstractMessageWriter<T extends q> implements d<T> {
    protected final CharArrayBuffer lineBuf = new CharArrayBuffer(128);
    protected final LineFormatter lineFormatter;
    protected final SessionOutputBuffer sessionBuffer;

    public AbstractMessageWriter(SessionOutputBuffer sessionOutputBuffer, LineFormatter $r2, HttpParams httpParams) {
        Args.notNull(sessionOutputBuffer, "Session input buffer");
        this.sessionBuffer = sessionOutputBuffer;
        this.lineFormatter = $r2 == null ? BasicLineFormatter.INSTANCE : $r2;
    }

    public void write(HttpMessage httpMessage) {
        Args.notNull(httpMessage, "HTTP message");
        writeHeadLine(httpMessage);
        HeaderIterator $r5 = httpMessage.headerIterator();
        while ($r5.hasNext()) {
            this.sessionBuffer.writeLine(this.lineFormatter.formatHeader(this.lineBuf, $r5.nextHeader()));
        }
        this.lineBuf.clear();
        this.sessionBuffer.writeLine(this.lineBuf);
    }

    /* access modifiers changed from: protected */
    public abstract void writeHeadLine(HttpMessage httpMessage);
}

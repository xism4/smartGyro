package cz.msebera.android.http.impl.io;

import c.a.a.a.i.f.b;
import c.a.a.a.r;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.message.LineFormatter;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public class HttpRequestWriter extends b<r> {
    public HttpRequestWriter(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter, HttpParams httpParams) {
        super(sessionOutputBuffer, lineFormatter, httpParams);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [cz.msebera.android.http.impl.io.AbstractMessageWriter, cz.msebera.android.http.impl.io.HttpRequestWriter] */
    /* access modifiers changed from: protected */
    public void writeHeadLine(HttpRequest httpRequest) {
        this.lineFormatter.formatRequestLine(this.lineBuf, httpRequest.getRequestLine());
        this.sessionBuffer.writeLine(this.lineBuf);
    }
}

package cz.msebera.android.http.impl.io;

import c.a.a.a.j.c;
import c.a.a.a.p.d;
import c.a.a.a.q;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.MessageConstraintException;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.message.BasicLineParser;
import cz.msebera.android.http.message.LineParser;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.protocol.MessageConstraints;
import cz.msebera.android.http.util.HttpParamConfig;
import cz.msebera.android.http.util.HttpParams;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMessageParser<T extends q> implements c<T> {
    private final List<d> headerLines;
    protected final LineParser lineParser;
    private T message;
    private final MessageConstraints messageConstraints;
    private final SessionInputBuffer sessionBuffer;
    private int state;

    public AbstractMessageParser(SessionInputBuffer sessionInputBuffer, LineParser $r4, HttpParams httpParams) {
        Args.notNull(sessionInputBuffer, "Session input buffer");
        Args.notNull(httpParams, "HTTP parameters");
        this.sessionBuffer = sessionInputBuffer;
        this.messageConstraints = HttpParamConfig.getMessageConstraints(httpParams);
        this.lineParser = $r4 == null ? BasicLineParser.INSTANCE : $r4;
        this.headerLines = new ArrayList();
        this.state = 0;
    }

    public static Header[] parseHeaders(SessionInputBuffer sessionInputBuffer, int i, int i2, LineParser $r3) {
        ArrayList $r0 = new ArrayList();
        if ($r3 == null) {
            $r3 = BasicLineParser.INSTANCE;
        }
        return parseHeaders(sessionInputBuffer, i, i2, $r3, $r0);
    }

    public static Header[] parseHeaders(SessionInputBuffer sessionInputBuffer, int i, int i2, LineParser lineParser2, List list) {
        int $i3;
        Args.notNull(sessionInputBuffer, "Session input buffer");
        Args.notNull(lineParser2, "Line parser");
        Args.notNull(list, "Header line list");
        CharArrayBuffer $r3 = null;
        CharArrayBuffer $r4 = null;
        while (true) {
            if ($r3 == null) {
                $r3 = new CharArrayBuffer(64);
            } else {
                $r3.clear();
            }
            $i3 = 0;
            if (sessionInputBuffer.readLine($r3) == -1 || $r3.length() < 1) {
                Header[] $r6 = new Header[list.size()];
            } else {
                if (($r3.charAt(0) == ' ' || $r3.charAt(0) == 9) && $r4 != null) {
                    while ($i3 < $r3.length() && (($c4 = $r3.charAt($i3)) == ' ' || $c4 == 9)) {
                        $i3++;
                    }
                    if (i2 <= 0 || (($r4.length() + 1) + $r3.length()) - $i3 <= i2) {
                        $r4.append(' ');
                        $r4.append($r3, $i3, $r3.length() - $i3);
                    } else {
                        throw new MessageConstraintException("Maximum line length limit exceeded");
                    }
                } else {
                    list.add($r3);
                    $r4 = $r3;
                    $r3 = null;
                }
                if (i > 0 && list.size() >= i) {
                    throw new MessageConstraintException("Maximum header count exceeded");
                }
            }
        }
        Header[] $r62 = new Header[list.size()];
        while ($i3 < list.size()) {
            try {
                $r62[$i3] = lineParser2.parseHeader((CharArrayBuffer) list.get($i3));
                $i3++;
            } catch (ParseException $r9) {
                throw new ProtocolException($r9.getMessage());
            }
        }
        return $r62;
    }

    public HttpMessage parse() {
        int $i0 = this.state;
        if ($i0 == 0) {
            try {
                this.message = parseHead(this.sessionBuffer);
                this.state = 1;
            } catch (ParseException $r8) {
                throw new ProtocolException($r8.getMessage(), $r8);
            }
        } else if ($i0 != 1) {
            throw new IllegalStateException("Inconsistent parser state");
        }
        this.message.setHeaders(parseHeaders(this.sessionBuffer, this.messageConstraints.getMaxLineLength(), this.messageConstraints.getMaxHeaderCount(), this.lineParser, this.headerLines));
        HttpMessage $r5 = this.message;
        this.message = null;
        this.headerLines.clear();
        this.state = 0;
        return $r5;
    }

    /* access modifiers changed from: protected */
    public abstract HttpMessage parseHead(SessionInputBuffer sessionInputBuffer);
}

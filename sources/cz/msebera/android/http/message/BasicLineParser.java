package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;

public class BasicLineParser implements LineParser {
    @Deprecated
    public static final BasicLineParser DEFAULT = new BasicLineParser();
    public static final BasicLineParser INSTANCE = new BasicLineParser();
    protected final ProtocolVersion protocol;

    public BasicLineParser() {
        this((ProtocolVersion) null);
    }

    public BasicLineParser(ProtocolVersion $r1) {
        this.protocol = $r1 == null ? HttpVersion.HTTP_1_1 : $r1;
    }

    /* access modifiers changed from: protected */
    public ProtocolVersion createProtocolVersion(int i, int i2) {
        return this.protocol.forVersion(i, i2);
    }

    /* access modifiers changed from: protected */
    public StatusLine createStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        return new BasicStatusLine(protocolVersion, i, str);
    }

    public boolean hasProtocolVersion(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(charArrayBuffer2, "Parser cursor");
        int $i0 = charArrayBuffer2.getPos();
        int $i1 = $i0;
        String $r4 = this.protocol.getProtocol();
        int $i2 = $r4.length();
        if (charArrayBuffer.length() < $i2 + 4) {
            return false;
        }
        if ($i0 < 0) {
            $i1 = (charArrayBuffer.length() - 4) - $i2;
        } else if ($i0 == 0) {
            while ($i1 < charArrayBuffer.length() && HTTP.isWhitespace(charArrayBuffer.charAt($i1))) {
                $i1++;
            }
        }
        int $i02 = $i1 + $i2;
        if ($i02 + 4 > charArrayBuffer.length()) {
            return false;
        }
        boolean $z0 = true;
        int $i3 = 0;
        while ($z0 && $i3 < $i2) {
            $z0 = charArrayBuffer.charAt($i1 + $i3) == $r4.charAt($i3);
            $i3++;
        }
        return $z0 ? charArrayBuffer.charAt($i02) == '/' : $z0;
    }

    public Header parseHeader(CharArrayBuffer charArrayBuffer) {
        return new BufferedHeader(charArrayBuffer);
    }

    public ProtocolVersion parseProtocolVersion(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(charArrayBuffer2, "Parser cursor");
        String $r6 = this.protocol.getProtocol();
        int $i0 = $r6.length();
        int $i1 = charArrayBuffer2.getPos();
        int $i2 = charArrayBuffer2.length();
        skipWhitespace(charArrayBuffer, charArrayBuffer2);
        int $i3 = charArrayBuffer2.getPos();
        int $i4 = $i3 + $i0;
        if ($i4 + 4 <= $i2) {
            boolean $z0 = true;
            int $i5 = 0;
            while ($z0 && $i5 < $i0) {
                $z0 = charArrayBuffer.charAt($i3 + $i5) == $r6.charAt($i5);
                $i5++;
            }
            if ($z0) {
                $z0 = charArrayBuffer.charAt($i4) == '/';
            }
            if ($z0) {
                int $i32 = $i3 + $i0 + 1;
                int $i02 = charArrayBuffer.indexOf(46, $i32, $i2);
                if ($i02 != -1) {
                    try {
                        int $i33 = Integer.parseInt(charArrayBuffer.substringTrimmed($i32, $i02));
                        int $i42 = $i02 + 1;
                        int $i52 = charArrayBuffer.indexOf(32, $i42, $i2);
                        int $i03 = $i52;
                        if ($i52 == -1) {
                            $i03 = $i2;
                        }
                        try {
                            int $i12 = Integer.parseInt(charArrayBuffer.substringTrimmed($i42, $i03));
                            charArrayBuffer2.append($i03);
                            return createProtocolVersion($i33, $i12);
                        } catch (NumberFormatException e) {
                            throw new ParseException("Invalid protocol minor version number: " + charArrayBuffer.substring($i1, $i2));
                        }
                    } catch (NumberFormatException e2) {
                        throw new ParseException("Invalid protocol major version number: " + charArrayBuffer.substring($i1, $i2));
                    }
                } else {
                    throw new ParseException("Invalid protocol version number: " + charArrayBuffer.substring($i1, $i2));
                }
            } else {
                throw new ParseException("Not a valid protocol version: " + charArrayBuffer.substring($i1, $i2));
            }
        } else {
            throw new ParseException("Not a valid protocol version: " + charArrayBuffer.substring($i1, $i2));
        }
    }

    public StatusLine parseStatusLine(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        String $r9;
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(charArrayBuffer2, "Parser cursor");
        int $i0 = charArrayBuffer2.getPos();
        int $i1 = charArrayBuffer2.length();
        try {
            ProtocolVersion $r8 = parseProtocolVersion(charArrayBuffer, charArrayBuffer2);
            skipWhitespace(charArrayBuffer, charArrayBuffer2);
            int $i2 = charArrayBuffer2.getPos();
            int $i3 = charArrayBuffer.indexOf(32, $i2, $i1);
            int $i4 = $i3;
            if ($i3 < 0) {
                $i4 = $i1;
            }
            String $r92 = charArrayBuffer.substringTrimmed($i2, $i4);
            int $i22 = 0;
            while ($i22 < $r92.length()) {
                try {
                    if (Character.isDigit($r92.charAt($i22))) {
                        $i22++;
                    } else {
                        throw new ParseException("Status line contains invalid status code: " + charArrayBuffer.substring($i0, $i1));
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new ParseException("Invalid status line: " + charArrayBuffer.substring($i0, $i1));
                }
            }
            try {
                int $i23 = Integer.parseInt($r92);
                if ($i4 < $i1) {
                    try {
                        $r9 = charArrayBuffer.substringTrimmed($i4, $i1);
                    } catch (IndexOutOfBoundsException e2) {
                        throw new ParseException("Invalid status line: " + charArrayBuffer.substring($i0, $i1));
                    }
                } else {
                    $r9 = "";
                }
                return createStatusLine($r8, $i23, $r9);
            } catch (NumberFormatException e3) {
                throw new ParseException("Status line contains invalid status code: " + charArrayBuffer.substring($i0, $i1));
            } catch (IndexOutOfBoundsException e4) {
                throw new ParseException("Invalid status line: " + charArrayBuffer.substring($i0, $i1));
            }
        } catch (IndexOutOfBoundsException e5) {
            throw new ParseException("Invalid status line: " + charArrayBuffer.substring($i0, $i1));
        }
    }

    /* access modifiers changed from: protected */
    public void skipWhitespace(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        int $i0 = charArrayBuffer2.getPos();
        int $i1 = charArrayBuffer2.length();
        while ($i0 < $i1 && HTTP.isWhitespace(charArrayBuffer.charAt($i0))) {
            $i0++;
        }
        charArrayBuffer2.append($i0);
    }
}

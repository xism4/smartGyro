package cz.msebera.android.http.conn.ssl;

import javax.security.auth.x500.X500Principal;

public final class DistinguishedNameParser {
    private int beg;
    private char[] chars;
    private int cur;
    private final String dn;
    private int end;
    private final int length = this.dn.length();
    private int pos;

    public DistinguishedNameParser(X500Principal x500Principal) {
        this.dn = x500Principal.getName("RFC2253");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a3, code lost:
        r3 = r7.chars;
        r0 = r7.beg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00af, code lost:
        return new java.lang.String(r3, r0, r7.cur - r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String escapedAV() {
        /*
            r7 = this;
            int r0 = r7.pos
            r7.beg = r0
            r7.end = r0
        L_0x0006:
            int r0 = r7.pos
            int r1 = r7.length
            if (r0 < r1) goto L_0x0019
            java.lang.String r2 = new java.lang.String
            char[] r3 = r7.chars
            int r0 = r7.beg
            int r1 = r7.end
            int r1 = r1 - r0
            r2.<init>(r3, r0, r1)
            return r2
        L_0x0019:
            char[] r3 = r7.chars
            char r4 = r3[r0]
            r5 = 32
            if (r4 == r5) goto L_0x005c
            r5 = 59
            if (r4 == r5) goto L_0x004f
            r5 = 92
            if (r4 == r5) goto L_0x0040
            r5 = 43
            if (r4 == r5) goto L_0x004f
            r5 = 44
            if (r4 == r5) goto L_0x004f
            int r1 = r7.end
            int r6 = r1 + 1
            r7.end = r6
            char r4 = r3[r0]
            r3[r1] = r4
        L_0x003b:
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x0006
        L_0x0040:
            int r0 = r7.end
            int r1 = r0 + 1
            r7.end = r1
            char r4 = r7.getEscaped()
            r3[r0] = r4
            int r0 = r7.pos
            goto L_0x003b
        L_0x004f:
            java.lang.String r2 = new java.lang.String
            char[] r3 = r7.chars
            int r0 = r7.beg
            int r1 = r7.end
            int r1 = r1 - r0
            r2.<init>(r3, r0, r1)
            return r2
        L_0x005c:
            int r1 = r7.end
            r7.cur = r1
            int r0 = r0 + 1
            r7.pos = r0
            int r0 = r1 + 1
            r7.end = r0
            r5 = 32
            r3[r1] = r5
        L_0x006c:
            int r0 = r7.pos
            int r1 = r7.length
            if (r0 >= r1) goto L_0x0089
            char[] r3 = r7.chars
            char r4 = r3[r0]
            r5 = 32
            if (r4 != r5) goto L_0x0089
            int r6 = r7.end
            int r1 = r6 + 1
            r7.end = r1
            r5 = 32
            r3[r6] = r5
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x006c
        L_0x0089:
            int r0 = r7.pos
            int r1 = r7.length
            if (r0 == r1) goto L_0x00a3
            char[] r3 = r7.chars
            char r4 = r3[r0]
            r5 = 44
            if (r4 == r5) goto L_0x00a3
            char r4 = r3[r0]
            r5 = 43
            if (r4 == r5) goto L_0x00a3
            char r4 = r3[r0]
            r5 = 59
            if (r4 != r5) goto L_0x0006
        L_0x00a3:
            java.lang.String r2 = new java.lang.String
            char[] r3 = r7.chars
            int r0 = r7.beg
            int r1 = r7.cur
            int r1 = r1 - r0
            r2.<init>(r3, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.conn.ssl.DistinguishedNameParser.escapedAV():java.lang.String");
    }

    private int getByte(int i) {
        int $i0;
        int $i1;
        int $i12 = i + 1;
        if ($i12 < this.length) {
            char $c3 = this.chars[i];
            if ($c3 >= '0' && $c3 <= '9') {
                $i0 = $c3 - '0';
            } else if ($c3 >= 'a' && $c3 <= 'f') {
                $i0 = $c3 - 'W';
            } else if ($c3 < 'A' || $c3 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.dn);
            } else {
                $i0 = $c3 - '7';
            }
            char $c32 = this.chars[$i12];
            if ($c32 >= '0' && $c32 <= '9') {
                $i1 = $c32 - '0';
            } else if ($c32 >= 'a' && $c32 <= 'f') {
                $i1 = $c32 - 'W';
            } else if ($c32 < 'A' || $c32 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.dn);
            } else {
                $i1 = $c32 - '7';
            }
            return ($i0 << 4) + $i1;
        }
        throw new IllegalStateException("Malformed DN: " + this.dn);
    }

    private char getEscaped() {
        this.pos++;
        int $i0 = this.pos;
        if ($i0 != this.length) {
            char $c2 = this.chars[$i0];
            if (!($c2 == ' ' || $c2 == '%' || $c2 == '\\' || $c2 == '_' || $c2 == '\"' || $c2 == '#')) {
                switch ($c2) {
                    case '*':
                    case '+':
                    case ',':
                        break;
                    default:
                        switch ($c2) {
                            case ';':
                            case '<':
                            case '=':
                            case '>':
                                break;
                            default:
                                return getUTF8();
                        }
                }
            }
            return this.chars[this.pos];
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.dn);
    }

    private char getUTF8() {
        byte $b3;
        int $i1;
        int $i0 = getByte(this.pos);
        this.pos++;
        if ($i0 < 128) {
            return (char) $i0;
        }
        if ($i0 < 192 || $i0 > 247) {
            return '?';
        }
        if ($i0 <= 223) {
            $i1 = $i0 & 31;
            $b3 = 1;
        } else if ($i0 <= 239) {
            $b3 = 2;
            $i1 = $i0 & 15;
        } else {
            $b3 = 3;
            $i1 = $i0 & 7;
        }
        for (int $i02 = 0; $i02 < $b3; $i02++) {
            this.pos++;
            int $i4 = this.pos;
            if ($i4 == this.length || this.chars[$i4] != '\\') {
                return '?';
            }
            this.pos = $i4 + 1;
            int $i42 = getByte(this.pos);
            this.pos++;
            if (($i42 & 192) != 128) {
                return '?';
            }
            $i1 = ($i1 << 6) + ($i42 & 63);
        }
        return (char) $i1;
    }

    private String hexAV() {
        int $i0 = this.pos;
        if ($i0 + 4 < this.length) {
            this.beg = $i0;
            while (true) {
                this.pos = $i0 + 1;
                int $i02 = this.pos;
                if ($i02 == this.length) {
                    break;
                }
                char[] $r1 = this.chars;
                if ($r1[$i02] == '+' || $r1[$i02] == ',' || $r1[$i02] == ';') {
                    break;
                } else if ($r1[$i02] == ' ') {
                    this.end = $i02;
                    do {
                        this.pos = $i02 + 1;
                        $i02 = this.pos;
                        if ($i02 >= this.length) {
                            break;
                        }
                    } while (this.chars[$i02] != ' ');
                } else {
                    if ($r1[$i02] >= 'A' && $r1[$i02] <= 'F') {
                        $r1[$i02] = (char) ($r1[$i02] + ' ');
                    }
                    $i0 = this.pos;
                }
            }
            this.end = this.pos;
            int $i03 = this.end;
            int $i1 = this.beg;
            int $i04 = $i03 - $i1;
            if ($i04 < 5 || ($i04 & 1) == 0) {
                throw new IllegalStateException("Unexpected end of DN: " + this.dn);
            }
            byte[] $r2 = new byte[($i04 / 2)];
            int $i12 = $i1 + 1;
            for (int $i2 = 0; $i2 < $r2.length; $i2++) {
                $r2[$i2] = (byte) getByte($i12);
                $i12 += 2;
            }
            return new String(this.chars, this.beg, $i04);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.dn);
    }

    private String nextAT() {
        char[] $r1;
        while (true) {
            int $i1 = this.pos;
            if ($i1 >= this.length || this.chars[$i1] != ' ') {
                int $i12 = this.pos;
            } else {
                this.pos = $i1 + 1;
            }
        }
        int $i122 = this.pos;
        if ($i122 == this.length) {
            return null;
        }
        this.beg = $i122;
        do {
            this.pos = $i122 + 1;
            $i122 = this.pos;
            if ($i122 >= this.length) {
                break;
            }
            $r1 = this.chars;
            if ($r1[$i122] == '=') {
                break;
            }
        } while ($r1[$i122] == ' ');
        int $i13 = this.pos;
        if ($i13 < this.length) {
            this.end = $i13;
            if (this.chars[$i13] == ' ') {
                while (true) {
                    int $i14 = this.pos;
                    if ($i14 >= this.length) {
                        break;
                    }
                    char[] $r12 = this.chars;
                    if ($r12[$i14] == '=' || $r12[$i14] != ' ') {
                        break;
                    }
                    this.pos = $i14 + 1;
                }
                char[] $r13 = this.chars;
                int $i15 = this.pos;
                if ($r13[$i15] != '=' || $i15 == this.length) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.dn);
                }
            }
            int $i16 = this.pos;
            do {
                this.pos = $i16 + 1;
                $i16 = this.pos;
                if ($i16 >= this.length || this.chars[$i16] != ' ') {
                    int $i2 = this.end;
                    int $i17 = this.beg;
                }
                this.pos = $i16 + 1;
                $i16 = this.pos;
                break;
            } while (this.chars[$i16] != ' ');
            int $i22 = this.end;
            int $i172 = this.beg;
            if ($i22 - $i172 > 4) {
                char[] $r14 = this.chars;
                if ($r14[$i172 + 3] == '.' && ($r14[$i172] == 'O' || $r14[$i172] == 'o')) {
                    char[] $r15 = this.chars;
                    int $i18 = this.beg;
                    if ($r15[$i18 + 1] == 'I' || $r15[$i18 + 1] == 'i') {
                        char[] $r16 = this.chars;
                        int $i19 = this.beg;
                        if ($r16[$i19 + 2] == 'D' || $r16[$i19 + 2] == 'd') {
                            this.beg += 4;
                        }
                    }
                }
            }
            char[] $r17 = this.chars;
            int $i110 = this.beg;
            return new String($r17, $i110, this.end - $i110);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.dn);
    }

    private String quotedAV() {
        this.pos++;
        this.beg = this.pos;
        int $i0 = this.beg;
        while (true) {
            this.end = $i0;
            int $i02 = this.pos;
            if ($i02 != this.length) {
                char[] $r1 = this.chars;
                if ($r1[$i02] == '\"') {
                    do {
                        this.pos = $i02 + 1;
                        $i02 = this.pos;
                        if ($i02 >= this.length || this.chars[$i02] != ' ') {
                            char[] $r12 = this.chars;
                            int $i03 = this.beg;
                        }
                        this.pos = $i02 + 1;
                        $i02 = this.pos;
                        break;
                    } while (this.chars[$i02] != ' ');
                    char[] $r122 = this.chars;
                    int $i032 = this.beg;
                    return new String($r122, $i032, this.end - $i032);
                }
                if ($r1[$i02] == '\\') {
                    $r1[this.end] = getEscaped();
                } else {
                    $r1[this.end] = $r1[$i02];
                }
                this.pos++;
                $i0 = this.end + 1;
            } else {
                throw new IllegalStateException("Unexpected end of DN: " + this.dn);
            }
        }
    }

    public String findMostSpecific(String str) {
        String $r4;
        this.pos = 0;
        this.beg = 0;
        this.end = 0;
        this.cur = 0;
        this.chars = this.dn.toCharArray();
        String $r42 = nextAT();
        String $r2 = $r42;
        if ($r42 == null) {
            return null;
        }
        do {
            int $i0 = this.pos;
            if ($i0 == this.length) {
                return null;
            }
            char $c2 = this.chars[$i0];
            String $r43 = $c2 != '\"' ? $c2 != '#' ? ($c2 == '+' || $c2 == ',' || $c2 == ';') ? "" : escapedAV() : hexAV() : quotedAV();
            if (str.equalsIgnoreCase($r2)) {
                return $r43;
            }
            int $i02 = this.pos;
            if ($i02 >= this.length) {
                return null;
            }
            char[] $r3 = this.chars;
            if ($r3[$i02] == ',' || $r3[$i02] == ';' || $r3[$i02] == '+') {
                this.pos++;
                $r4 = nextAT();
                $r2 = $r4;
            } else {
                throw new IllegalStateException("Malformed DN: " + this.dn);
            }
        } while ($r4 != null);
        throw new IllegalStateException("Malformed DN: " + this.dn);
    }
}

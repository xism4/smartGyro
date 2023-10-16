package cz.msebera.android.http.mime;

import cz.msebera.android.http.execchain.HTTP;
import java.io.Serializable;
import java.nio.CharBuffer;

public final class CharArrayBuffer implements CharSequence, Serializable {
    private char[] buffer;
    private int len;

    public CharArrayBuffer(int i) {
        Args.notNegative(i, "Buffer capacity");
        this.buffer = new char[i];
    }

    private void expand(int i) {
        char[] $r1 = new char[Math.max(this.buffer.length << 1, i)];
        System.arraycopy(this.buffer, 0, $r1, 0, this.len);
        this.buffer = $r1;
    }

    public void append(char c) {
        int $i2 = this.len + 1;
        if ($i2 > this.buffer.length) {
            expand($i2);
        }
        this.buffer[this.len] = c;
        this.len = $i2;
    }

    public void append(ByteArrayBuffer byteArrayBuffer, int i, int i2) {
        if (byteArrayBuffer != null) {
            append(byteArrayBuffer.buffer(), i, i2);
        }
    }

    public void append(CharArrayBuffer charArrayBuffer, int i, int i2) {
        if (charArrayBuffer != null) {
            append(charArrayBuffer.buffer, i, i2);
        }
    }

    public void append(String $r2) {
        if ($r2 == null) {
            $r2 = "null";
        }
        int $i1 = $r2.length();
        int $i2 = this.len + $i1;
        if ($i2 > this.buffer.length) {
            expand($i2);
        }
        $r2.getChars(0, $i1, this.buffer, this.len);
        this.len = $i2;
    }

    public void append(byte[] bArr, int $i0, int $i1) {
        int $i2;
        if (bArr != null) {
            if ($i0 < 0 || $i0 > bArr.length || $i1 < 0 || ($i2 = $i0 + $i1) < 0 || $i2 > bArr.length) {
                throw new IndexOutOfBoundsException("off: " + $i0 + " len: " + $i1 + " b.length: " + bArr.length);
            } else if ($i1 != 0) {
                int $i22 = this.len;
                int $i12 = $i1 + $i22;
                if ($i12 > this.buffer.length) {
                    expand($i12);
                }
                while ($i22 < $i12) {
                    this.buffer[$i22] = (char) (bArr[$i0] & 255);
                    $i0++;
                    $i22++;
                }
                this.len = $i12;
            }
        }
    }

    public void append(char[] cArr, int i, int i2) {
        int $i2;
        if (cArr != null) {
            if (i < 0 || i > cArr.length || i2 < 0 || ($i2 = i + i2) < 0 || $i2 > cArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + cArr.length);
            } else if (i2 != 0) {
                int $i22 = this.len + i2;
                if ($i22 > this.buffer.length) {
                    expand($i22);
                }
                System.arraycopy(cArr, i, this.buffer, this.len, i2);
                this.len = $i22;
            }
        }
    }

    public char[] buffer() {
        return this.buffer;
    }

    public char charAt(int i) {
        return this.buffer[i];
    }

    public void clear() {
        this.len = 0;
    }

    public void ensureCapacity(int i) {
        if (i > 0) {
            int $i1 = this.buffer.length;
            int $i2 = this.len;
            if (i > $i1 - $i2) {
                expand($i2 + i);
            }
        }
    }

    public int indexOf(int i) {
        return indexOf(i, 0, this.len);
    }

    public int indexOf(int i, int $i1, int $i2) {
        if ($i1 < 0) {
            $i1 = 0;
        }
        int $i3 = this.len;
        if ($i2 > $i3) {
            $i2 = $i3;
        }
        if ($i1 > $i2) {
            return -1;
        }
        while ($i1 < $i2) {
            if (this.buffer[$i1] == i) {
                return $i1;
            }
            $i1++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.len == 0;
    }

    public int length() {
        return this.len;
    }

    public CharSequence subSequence(int $i0, int i) {
        if ($i0 < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + $i0);
        } else if (i > this.len) {
            throw new IndexOutOfBoundsException("endIndex: " + i + " > length: " + this.len);
        } else if ($i0 <= i) {
            return CharBuffer.wrap(this.buffer, $i0, i);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + $i0 + " > endIndex: " + i);
        }
    }

    public String substring(int $i0, int $i1) {
        if ($i0 < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + $i0);
        } else if ($i1 > this.len) {
            throw new IndexOutOfBoundsException("endIndex: " + $i1 + " > length: " + this.len);
        } else if ($i0 <= $i1) {
            return new String(this.buffer, $i0, $i1 - $i0);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + $i0 + " > endIndex: " + $i1);
        }
    }

    public String substringTrimmed(int $i0, int $i1) {
        if ($i0 < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + $i0);
        } else if ($i1 > this.len) {
            throw new IndexOutOfBoundsException("endIndex: " + $i1 + " > length: " + this.len);
        } else if ($i0 <= $i1) {
            while ($i0 < $i1 && HTTP.isWhitespace(this.buffer[$i0])) {
                $i0++;
            }
            while ($i1 > $i0 && HTTP.isWhitespace(this.buffer[$i1 - 1])) {
                $i1--;
            }
            return new String(this.buffer, $i0, $i1 - $i0);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + $i0 + " > endIndex: " + $i1);
        }
    }

    public String toString() {
        return new String(this.buffer, 0, this.len);
    }
}

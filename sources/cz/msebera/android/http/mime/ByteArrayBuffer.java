package cz.msebera.android.http.mime;

import java.io.Serializable;

public final class ByteArrayBuffer implements Serializable {
    private byte[] buffer;
    private int len;

    public ByteArrayBuffer(int i) {
        Args.notNegative(i, "Buffer capacity");
        this.buffer = new byte[i];
    }

    private void expand(int i) {
        byte[] $r1 = new byte[Math.max(this.buffer.length << 1, i)];
        System.arraycopy(this.buffer, 0, $r1, 0, this.len);
        this.buffer = $r1;
    }

    public void append(int i) {
        int $i2 = this.len + 1;
        if ($i2 > this.buffer.length) {
            expand($i2);
        }
        this.buffer[this.len] = (byte) i;
        this.len = $i2;
    }

    public void append(CharArrayBuffer charArrayBuffer, int i, int i2) {
        if (charArrayBuffer != null) {
            append(charArrayBuffer.buffer(), i, i2);
        }
    }

    public void append(byte[] bArr, int i, int i2) {
        int $i2;
        if (bArr != null) {
            if (i < 0 || i > bArr.length || i2 < 0 || ($i2 = i + i2) < 0 || $i2 > bArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + bArr.length);
            } else if (i2 != 0) {
                int $i22 = this.len + i2;
                if ($i22 > this.buffer.length) {
                    expand($i22);
                }
                System.arraycopy(bArr, i, this.buffer, this.len, i2);
                this.len = $i22;
            }
        }
    }

    public void append(char[] cArr, int $i0, int $i1) {
        int $i2;
        if (cArr != null) {
            if ($i0 < 0 || $i0 > cArr.length || $i1 < 0 || ($i2 = $i0 + $i1) < 0 || $i2 > cArr.length) {
                throw new IndexOutOfBoundsException("off: " + $i0 + " len: " + $i1 + " b.length: " + cArr.length);
            } else if ($i1 != 0) {
                int $i22 = this.len;
                int $i12 = $i1 + $i22;
                if ($i12 > this.buffer.length) {
                    expand($i12);
                }
                while ($i22 < $i12) {
                    this.buffer[$i22] = (byte) cArr[$i0];
                    $i0++;
                    $i22++;
                }
                this.len = $i12;
            }
        }
    }

    public byte[] buffer() {
        return this.buffer;
    }

    public int byteAt(int i) {
        return this.buffer[i];
    }

    public int capacity() {
        return this.buffer.length;
    }

    public void clear() {
        this.len = 0;
    }

    public boolean isEmpty() {
        return this.len == 0;
    }

    public boolean isFull() {
        return this.len == this.buffer.length;
    }

    public int length() {
        return this.len;
    }

    public byte[] toByteArray() {
        int $i0 = this.len;
        byte[] $r1 = new byte[$i0];
        if ($i0 > 0) {
            System.arraycopy(this.buffer, 0, $r1, 0, $i0);
        }
        return $r1;
    }
}

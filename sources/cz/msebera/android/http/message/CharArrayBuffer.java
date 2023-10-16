package cz.msebera.android.http.message;

public class CharArrayBuffer {
    private final int buffer;
    private final int len;
    private int pos;

    public CharArrayBuffer(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        } else if (i <= i2) {
            this.buffer = i;
            this.len = i2;
            this.pos = i;
        } else {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
    }

    public void append(int $i0) {
        if ($i0 < this.buffer) {
            throw new IndexOutOfBoundsException("pos: " + $i0 + " < lowerBound: " + this.buffer);
        } else if ($i0 <= this.len) {
            this.pos = $i0;
        } else {
            throw new IndexOutOfBoundsException("pos: " + $i0 + " > upperBound: " + this.len);
        }
    }

    public boolean atEnd() {
        return this.pos >= this.len;
    }

    public int getPos() {
        return this.pos;
    }

    public int length() {
        return this.len;
    }

    public String toString() {
        return '[' + Integer.toString(this.buffer) + '>' + Integer.toString(this.pos) + '>' + Integer.toString(this.len) + ']';
    }
}

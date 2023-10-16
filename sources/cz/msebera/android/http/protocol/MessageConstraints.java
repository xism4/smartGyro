package cz.msebera.android.http.protocol;

public class MessageConstraints implements Cloneable {
    public static final MessageConstraints DEFAULT = new Builder().build();
    private final int maxHeaderCount;
    private final int maxLineLength;

    public class Builder {
        private int maxHeaderCount = -1;
        private int maxLineLength = -1;

        Builder() {
        }

        public MessageConstraints build() {
            return new MessageConstraints(this.maxLineLength, this.maxHeaderCount);
        }

        public Builder setMaxHeaderCount(int i) {
            this.maxHeaderCount = i;
            return this;
        }

        public Builder setMaxLineLength(int i) {
            this.maxLineLength = i;
            return this;
        }
    }

    MessageConstraints(int i, int i2) {
        this.maxHeaderCount = i;
        this.maxLineLength = i2;
    }

    public static Builder custom() {
        return new Builder();
    }

    /* access modifiers changed from: protected */
    public MessageConstraints clone() {
        return (MessageConstraints) super.clone();
    }

    public int getMaxHeaderCount() {
        return this.maxHeaderCount;
    }

    public int getMaxLineLength() {
        return this.maxLineLength;
    }

    public String toString() {
        return "[maxLineLength=" + this.maxHeaderCount + ", maxHeaderCount=" + this.maxLineLength + "]";
    }
}

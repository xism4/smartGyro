package android.support.v7.widget;

class RtlSpacingHelper {
    private int mEnd = Integer.MIN_VALUE;
    private int mExplicitLeft = 0;
    private int mExplicitRight = 0;
    private boolean mIsRelative = false;
    private boolean mIsRtl = false;
    private int mLeft = 0;
    private int mRight = 0;
    private int mStart = Integer.MIN_VALUE;

    RtlSpacingHelper() {
    }

    public int getEnd() {
        return this.mIsRtl ? this.mLeft : this.mRight;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getStart() {
        return this.mIsRtl ? this.mRight : this.mLeft;
    }

    public void setAbsolute(int i, int i2) {
        this.mIsRelative = false;
        if (i != Integer.MIN_VALUE) {
            this.mExplicitLeft = i;
            this.mLeft = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.mExplicitRight = i2;
            this.mRight = i2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        if (r1 != Integer.MIN_VALUE) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        if (r1 != Integer.MIN_VALUE) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDirection(boolean r4) {
        /*
            r3 = this;
            boolean r0 = r3.mIsRtl
            if (r4 != r0) goto L_0x0005
            return
        L_0x0005:
            r3.mIsRtl = r4
            boolean r0 = r3.mIsRelative
            if (r0 == 0) goto L_0x0035
            if (r4 == 0) goto L_0x0021
            int r1 = r3.mEnd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r2) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            int r1 = r3.mExplicitLeft
        L_0x0017:
            r3.mLeft = r1
            int r1 = r3.mStart
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r2) goto L_0x0039
            goto L_0x003b
        L_0x0021:
            int r1 = r3.mStart
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r2) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            int r1 = r3.mExplicitLeft
        L_0x002b:
            r3.mLeft = r1
            int r1 = r3.mEnd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r2) goto L_0x0039
            goto L_0x003b
        L_0x0035:
            int r1 = r3.mExplicitLeft
            r3.mLeft = r1
        L_0x0039:
            int r1 = r3.mExplicitRight
        L_0x003b:
            r3.mRight = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RtlSpacingHelper.setDirection(boolean):void");
    }

    public void setRelative(int i, int i2) {
        this.mStart = i;
        this.mEnd = i2;
        this.mIsRelative = true;
        if (this.mIsRtl) {
            if (i2 != Integer.MIN_VALUE) {
                this.mLeft = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.mRight = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.mLeft = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.mRight = i2;
        }
    }
}

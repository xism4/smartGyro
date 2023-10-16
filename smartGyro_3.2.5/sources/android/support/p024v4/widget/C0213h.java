package android.support.p024v4.widget;

import android.widget.ListView;

/* renamed from: android.support.v4.widget.h */
public class C0213h extends C0201a {

    /* renamed from: s */
    private final ListView f552s;

    public C0213h(ListView listView) {
        super(listView);
        this.f552s = listView;
    }

    /* renamed from: a */
    public void mo865a(int i, int i2) {
        C0214i.m799a(this.f552s, i2);
    }

    /* renamed from: a */
    public boolean mo866a(int i) {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0038 A[RETURN] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo869b(int r8) {
        /*
            r7 = this;
            android.widget.ListView r0 = r7.f552s
            int r1 = r0.getCount()
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            int r3 = r0.getChildCount()
            int r4 = r0.getFirstVisiblePosition()
            int r5 = r4 + r3
            r6 = 1
            if (r8 <= 0) goto L_0x0029
            if (r5 < r1) goto L_0x0038
            int r3 = r3 - r6
            android.view.View r8 = r0.getChildAt(r3)
            int r8 = r8.getBottom()
            int r0 = r0.getHeight()
            if (r8 > r0) goto L_0x0038
            return r2
        L_0x0029:
            if (r8 >= 0) goto L_0x0039
            if (r4 > 0) goto L_0x0038
            android.view.View r8 = r0.getChildAt(r2)
            int r8 = r8.getTop()
            if (r8 < 0) goto L_0x0038
            return r2
        L_0x0038:
            return r6
        L_0x0039:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p024v4.widget.C0213h.mo869b(int):boolean");
    }
}

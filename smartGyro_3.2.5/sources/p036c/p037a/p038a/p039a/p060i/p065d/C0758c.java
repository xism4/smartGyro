package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.Date;
import p036c.p037a.p038a.p039a.p057f.C0654o;

/* renamed from: c.a.a.a.i.d.c */
public class C0758c extends C0759d implements C0654o {

    /* renamed from: j */
    private String f2238j;

    /* renamed from: k */
    private int[] f2239k;

    /* renamed from: l */
    private boolean f2240l;

    public C0758c(String str, String str2) {
        super(str, str2);
    }

    public Object clone() {
        C0758c cVar = (C0758c) super.clone();
        int[] iArr = this.f2239k;
        if (iArr != null) {
            cVar.f2239k = (int[]) iArr.clone();
        }
        return cVar;
    }

    public int[] getPorts() {
        return this.f2239k;
    }

    public boolean isExpired(Date date) {
        return this.f2240l || super.isExpired(date);
    }

    public void setCommentURL(String str) {
        this.f2238j = str;
    }

    public void setDiscard(boolean z) {
        this.f2240l = z;
    }

    public void setPorts(int[] iArr) {
        this.f2239k = iArr;
    }
}

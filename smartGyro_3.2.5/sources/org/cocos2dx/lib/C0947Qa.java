package org.cocos2dx.lib;

import android.util.Log;
import p026b.p033c.p034a.p035a.C0470g;
import p036c.p037a.p038a.p039a.C0576e;

/* renamed from: org.cocos2dx.lib.Qa */
class C0947Qa extends C0470g {

    /* renamed from: j */
    int f2571j;

    /* renamed from: k */
    private Cocos2dxDownloader f2572k;

    /* renamed from: l */
    private long f2573l = 0;

    public C0947Qa(Cocos2dxDownloader cocos2dxDownloader, int i) {
        super(new String[]{".*"});
        this.f2572k = cocos2dxDownloader;
        this.f2571j = i;
    }

    /* renamed from: a */
    public void mo2378a(int i, C0576e[] eVarArr, byte[] bArr) {
        mo3658a("onSuccess(i:" + i + " headers:" + eVarArr);
        this.f2572k.onFinish(this.f2571j, 0, (String) null, bArr);
    }

    /* renamed from: a */
    public void mo2380a(long j, long j2) {
        this.f2572k.onProgress(this.f2571j, j - this.f2573l, j, j2);
        this.f2573l = j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo3658a(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    /* renamed from: b */
    public void mo2392b(int i, C0576e[] eVarArr, byte[] bArr, Throwable th) {
        mo3658a("onFailure(i:" + i + " headers:" + eVarArr + " throwable:" + th);
        this.f2572k.onFinish(this.f2571j, i, th != null ? th.toString() : "", (byte[]) null);
    }

    /* renamed from: h */
    public void mo2403h() {
        this.f2572k.runNextTaskIfExists();
    }

    /* renamed from: i */
    public void mo2404i() {
        this.f2572k.onStart(this.f2571j);
    }
}

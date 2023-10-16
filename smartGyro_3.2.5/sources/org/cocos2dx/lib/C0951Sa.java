package org.cocos2dx.lib;

import android.util.Log;
import java.io.File;
import p026b.p033c.p034a.p035a.C0471h;
import p036c.p037a.p038a.p039a.C0576e;

/* renamed from: org.cocos2dx.lib.Sa */
class C0951Sa extends C0471h {

    /* renamed from: m */
    int f2584m;

    /* renamed from: n */
    File f2585n;

    /* renamed from: o */
    private long f2586o = mo2410k().length();

    /* renamed from: p */
    private long f2587p = 0;

    /* renamed from: q */
    private Cocos2dxDownloader f2588q;

    public C0951Sa(Cocos2dxDownloader cocos2dxDownloader, int i, File file, File file2) {
        super(file, true);
        this.f2585n = file2;
        this.f2588q = cocos2dxDownloader;
        this.f2584m = i;
    }

    /* renamed from: a */
    public void mo2407a(int i, C0576e[] eVarArr, File file) {
        String str;
        StringBuilder sb;
        String str2;
        mo3663a("onSuccess(i:" + i + " headers:" + eVarArr + " file:" + file);
        if (this.f2585n.exists()) {
            if (this.f2585n.isDirectory()) {
                sb = new StringBuilder();
                str2 = "Dest file is directory:";
            } else if (!this.f2585n.delete()) {
                sb = new StringBuilder();
                str2 = "Can't remove old file:";
            }
            sb.append(str2);
            sb.append(this.f2585n.getAbsolutePath());
            str = sb.toString();
            this.f2588q.onFinish(this.f2584m, 0, str, (byte[]) null);
        }
        mo2410k().renameTo(this.f2585n);
        str = null;
        this.f2588q.onFinish(this.f2584m, 0, str, (byte[]) null);
    }

    /* renamed from: a */
    public void mo2408a(int i, C0576e[] eVarArr, Throwable th, File file) {
        mo3663a("onFailure(i:" + i + " headers:" + eVarArr + " throwable:" + th + " file:" + file);
        this.f2588q.onFinish(this.f2584m, i, th != null ? th.toString() : "", (byte[]) null);
    }

    /* renamed from: a */
    public void mo2380a(long j, long j2) {
        long j3 = j - this.f2587p;
        long j4 = this.f2586o;
        this.f2588q.onProgress(this.f2584m, j3, j + j4, j2 + j4);
        this.f2587p = j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo3663a(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    /* renamed from: h */
    public void mo2403h() {
        this.f2588q.runNextTaskIfExists();
    }

    /* renamed from: i */
    public void mo2404i() {
        this.f2588q.onStart(this.f2584m);
    }
}

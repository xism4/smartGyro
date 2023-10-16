package org.cocos2dx.lib;

import android.util.Log;
import p026b.p033c.p034a.p035a.C0468f;
import p036c.p037a.p038a.p039a.C0576e;

/* renamed from: org.cocos2dx.lib.Xa */
class C0961Xa extends C0468f {

    /* renamed from: i */
    int f2612i;

    /* renamed from: j */
    String f2613j;

    /* renamed from: k */
    String f2614k;

    /* renamed from: l */
    String f2615l;

    /* renamed from: m */
    private Cocos2dxDownloader f2616m;

    public C0961Xa(Cocos2dxDownloader cocos2dxDownloader, int i, String str, String str2, String str3) {
        this.f2616m = cocos2dxDownloader;
        this.f2612i = i;
        this.f2613j = str;
        this.f2614k = str2;
        this.f2615l = str3;
    }

    /* renamed from: a */
    public void mo2378a(int i, C0576e[] eVarArr, byte[] bArr) {
        int i2 = 0;
        boolean z = false;
        while (true) {
            if (i2 >= eVarArr.length) {
                break;
            }
            C0576e eVar = eVarArr[i2];
            if (eVar.getName().equals("Accept-Ranges")) {
                z = Boolean.valueOf(eVar.getValue().equals("bytes"));
                break;
            }
            i2++;
        }
        Cocos2dxDownloader.setResumingSupport(this.f2613j, z);
        Cocos2dxDownloader.createTask(this.f2616m, this.f2612i, this.f2614k, this.f2615l);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo3673a(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    /* renamed from: b */
    public void mo2392b(int i, C0576e[] eVarArr, byte[] bArr, Throwable th) {
        mo3673a("onFailure(code:" + i + " headers:" + eVarArr + " throwable:" + th + " id:" + this.f2612i);
        this.f2616m.onFinish(this.f2612i, i, th != null ? th.toString() : "", (byte[]) null);
    }

    /* renamed from: h */
    public void mo2403h() {
        this.f2616m.runNextTaskIfExists();
    }
}

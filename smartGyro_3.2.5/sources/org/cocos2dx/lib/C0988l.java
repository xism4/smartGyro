package org.cocos2dx.lib;

import java.util.Map;
import p026b.p033c.p034a.p035a.C0480q;

/* renamed from: org.cocos2dx.lib.l */
class C0988l implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxDownloader f2673a;

    C0988l(Cocos2dxDownloader cocos2dxDownloader) {
        this.f2673a = cocos2dxDownloader;
    }

    public void run() {
        for (Map.Entry value : this.f2673a._taskMap.entrySet()) {
            C0480q qVar = ((C0949Ra) value.getValue()).f2574a;
            if (qVar != null) {
                qVar.mo2433a(true);
            }
        }
    }
}

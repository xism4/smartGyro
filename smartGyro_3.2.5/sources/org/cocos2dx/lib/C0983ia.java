package org.cocos2dx.lib;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import p026b.p031b.p032a.C0459a;

/* renamed from: org.cocos2dx.lib.ia */
class C0983ia implements ServiceConnection {
    C0983ia() {
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C0459a unused = Cocos2dxHelper.mGameServiceBinder = C0459a.C0460a.m1988a(iBinder);
        Cocos2dxHelper.fastLoading(7);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Cocos2dxHelper.sActivity.getApplicationContext().unbindService(Cocos2dxHelper.connection);
    }
}

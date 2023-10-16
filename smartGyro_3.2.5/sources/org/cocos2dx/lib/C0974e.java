package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.e */
class C0974e implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0976f f2645a;

    C0974e(C0976f fVar) {
        this.f2645a = fVar;
    }

    public void run() {
        Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(0);
        Cocos2dxHelper.setAudioFocus(true);
    }
}

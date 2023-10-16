package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.d */
class C0972d implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0976f f2640a;

    C0972d(C0976f fVar) {
        this.f2640a = fVar;
    }

    public void run() {
        Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(3);
        Cocos2dxHelper.setAudioFocus(false);
    }
}

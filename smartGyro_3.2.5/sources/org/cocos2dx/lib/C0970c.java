package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.c */
class C0970c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0976f f2635a;

    C0970c(C0976f fVar) {
        this.f2635a = fVar;
    }

    public void run() {
        Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(2);
        Cocos2dxHelper.setAudioFocus(false);
    }
}

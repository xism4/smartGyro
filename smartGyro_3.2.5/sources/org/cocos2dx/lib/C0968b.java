package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.b */
class C0968b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0976f f2630a;

    C0968b(C0976f fVar) {
        this.f2630a = fVar;
    }

    public void run() {
        Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(1);
        Cocos2dxHelper.setAudioFocus(false);
    }
}

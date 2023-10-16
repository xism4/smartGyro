package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.g */
class C0978g implements Runnable {
    C0978g() {
    }

    public void run() {
        Cocos2dxHelper.setAudioFocus(true);
        Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(0);
    }
}

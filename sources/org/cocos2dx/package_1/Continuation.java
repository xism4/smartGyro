package org.cocos2dx.package_1;

class Continuation implements Runnable {
    final /* synthetic */ CopyOnWriteArrayList mCallbacks;

    Continuation(CopyOnWriteArrayList copyOnWriteArrayList) {
        this.mCallbacks = copyOnWriteArrayList;
    }

    public void run() {
        Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(0);
        Cocos2dxHelper.setAudioFocus(true);
    }
}

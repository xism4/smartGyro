package org.cocos2dx.package_1;

class Notifier implements Runnable {
    final /* synthetic */ CopyOnWriteArrayList wallets;

    Notifier(CopyOnWriteArrayList copyOnWriteArrayList) {
        this.wallets = copyOnWriteArrayList;
    }

    public void run() {
        Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(3);
        Cocos2dxHelper.setAudioFocus(false);
    }
}

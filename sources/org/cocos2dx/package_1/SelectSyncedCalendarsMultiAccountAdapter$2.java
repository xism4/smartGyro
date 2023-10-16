package org.cocos2dx.package_1;

class SelectSyncedCalendarsMultiAccountAdapter$2 implements Runnable {
    final /* synthetic */ CopyOnWriteArrayList this$0;

    SelectSyncedCalendarsMultiAccountAdapter$2(CopyOnWriteArrayList copyOnWriteArrayList) {
        this.this$0 = copyOnWriteArrayList;
    }

    public void run() {
        Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(1);
        Cocos2dxHelper.setAudioFocus(false);
    }
}

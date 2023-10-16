package org.cocos2dx.package_1;

class AgendaByDayAdapter$1 implements Runnable {
    final /* synthetic */ CopyOnWriteArrayList this$0;

    AgendaByDayAdapter$1(CopyOnWriteArrayList copyOnWriteArrayList) {
        this.this$0 = copyOnWriteArrayList;
    }

    public void run() {
        Cocos2dxAudioFocusManager.nativeOnAudioFocusChange(2);
        Cocos2dxHelper.setAudioFocus(false);
    }
}

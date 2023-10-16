package org.cocos2dx.package_1;

class Godot$1 implements Runnable {
    final /* synthetic */ Cocos2dxActivity this$0;
    final /* synthetic */ boolean val$p_enabled;

    Godot$1(Cocos2dxActivity cocos2dxActivity, boolean z) {
        this.this$0 = cocos2dxActivity;
        this.val$p_enabled = z;
    }

    public void run() {
        this.this$0.mGLSurfaceView.setKeepScreenOn(this.val$p_enabled);
    }
}

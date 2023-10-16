package org.cocos2dx.lib;

import org.cocos2dx.lib.Cocos2dxVideoHelper;
import org.cocos2dx.lib.Cocos2dxVideoView;

/* renamed from: org.cocos2dx.lib.ka */
class C0987ka implements Cocos2dxVideoView.OnVideoEventListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxVideoHelper f2672a;

    C0987ka(Cocos2dxVideoHelper cocos2dxVideoHelper) {
        this.f2672a = cocos2dxVideoHelper;
    }

    public void onVideoEvent(int i, int i2) {
        this.f2672a.mActivity.runOnGLThread(new Cocos2dxVideoHelper.C0917a(i, i2));
    }
}

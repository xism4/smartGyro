package org.cocos2dx.package_1;

import org.cocos2dx.package_1.Cocos2dxVideoHelper;
import org.cocos2dx.package_1.Cocos2dxVideoView;

class LogActivity$1 implements Cocos2dxVideoView.OnVideoEventListener {
    final /* synthetic */ Cocos2dxVideoHelper val$ctx;

    LogActivity$1(Cocos2dxVideoHelper cocos2dxVideoHelper) {
        this.val$ctx = cocos2dxVideoHelper;
    }

    public void onVideoEvent(int i, int i2) {
        this.val$ctx.mActivity.runOnGLThread(new Cocos2dxVideoHelper.a(i, i2));
    }
}

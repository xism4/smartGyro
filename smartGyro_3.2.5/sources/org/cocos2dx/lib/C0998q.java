package org.cocos2dx.lib;

import android.util.Log;
import android.view.View;

/* renamed from: org.cocos2dx.lib.q */
class C0998q implements View.OnFocusChangeListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxEditBox f2686a;

    /* renamed from: b */
    final /* synthetic */ C0908A f2687b;

    C0998q(C0908A a, Cocos2dxEditBox cocos2dxEditBox) {
        this.f2687b = a;
        this.f2686a = cocos2dxEditBox;
    }

    public void onFocusChange(View view, boolean z) {
        String str;
        String str2;
        this.f2686a.setTag(true);
        this.f2686a.setChangedTextProgrammatically(false);
        if (z) {
            Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new C0994o(this));
            Cocos2dxEditBox cocos2dxEditBox = this.f2686a;
            cocos2dxEditBox.setSelection(cocos2dxEditBox.getText().length());
            Cocos2dxEditBoxHelper.mFrameLayout.setEnableForceDoLayout(true);
            Cocos2dxEditBoxHelper.mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(true);
            str2 = Cocos2dxEditBoxHelper.TAG;
            str = "edit box get focus";
        } else {
            this.f2686a.setVisibility(8);
            Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new C0996p(this, new String(this.f2686a.getText().toString())));
            Cocos2dxEditBoxHelper.mCocos2dxActivity.hideVirtualButton();
            Cocos2dxEditBoxHelper.mFrameLayout.setEnableForceDoLayout(false);
            str2 = Cocos2dxEditBoxHelper.TAG;
            str = "edit box lose focus";
        }
        Log.d(str2, str);
    }
}

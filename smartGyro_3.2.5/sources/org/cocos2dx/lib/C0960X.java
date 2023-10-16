package org.cocos2dx.lib;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

/* renamed from: org.cocos2dx.lib.X */
class C0960X extends Handler {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxGLSurfaceView f2611a;

    C0960X(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.f2611a = cocos2dxGLSurfaceView;
    }

    public void handleMessage(Message message) {
        String str;
        int i = message.what;
        if (i != 2) {
            if (i == 3 && this.f2611a.mCocos2dxEditText != null) {
                this.f2611a.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.f2611a.mCocos2dxEditText.getWindowToken(), 0);
                this.f2611a.requestFocus();
                ((Cocos2dxActivity) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext()).hideVirtualButton();
                str = "HideSoftInput";
            } else {
                return;
            }
        } else if (this.f2611a.mCocos2dxEditText != null && this.f2611a.mCocos2dxEditText.requestFocus()) {
            this.f2611a.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
            this.f2611a.mCocos2dxEditText.setText("");
            String str2 = (String) message.obj;
            this.f2611a.mCocos2dxEditText.append(str2);
            Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper.setOriginText(str2);
            this.f2611a.mCocos2dxEditText.addTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
            ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).showSoftInput(this.f2611a.mCocos2dxEditText, 0);
            str = "showSoftInput";
        } else {
            return;
        }
        Log.d("GLSurfaceView", str);
    }
}

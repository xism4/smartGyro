package org.cocos2dx.package_1;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

class Launcher$17 extends Handler {
    final /* synthetic */ Cocos2dxGLSurfaceView this$0;

    Launcher$17(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.this$0 = cocos2dxGLSurfaceView;
    }

    public void handleMessage(Message message) {
        String $r10;
        Message message2 = message;
        int $i0 = message2.what;
        Message message3 = message2;
        if ($i0 != 2) {
            if ($i0 == 3 && this.this$0.mCocos2dxEditText != null) {
                this.this$0.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.this$0.mCocos2dxEditText.getWindowToken(), 0);
                this.this$0.requestFocus();
                ((Cocos2dxActivity) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext()).hideVirtualButton();
                $r10 = "HideSoftInput";
            } else {
                return;
            }
        } else if (this.this$0.mCocos2dxEditText != null && this.this$0.mCocos2dxEditText.requestFocus()) {
            this.this$0.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
            this.this$0.mCocos2dxEditText.setText("");
            String $r102 = (String) message3.obj;
            this.this$0.mCocos2dxEditText.append($r102);
            Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper.setOriginText($r102);
            this.this$0.mCocos2dxEditText.addTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
            ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).showSoftInput(this.this$0.mCocos2dxEditText, 0);
            $r10 = "showSoftInput";
        } else {
            return;
        }
        Log.d("GLSurfaceView", $r10);
    }
}

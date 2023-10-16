package org.cocos2dx.package_1;

import android.util.Log;
import android.view.View;

class Folder implements View.OnFocusChangeListener {
    final /* synthetic */ Cocos2dxEditBox text;
    final /* synthetic */ Stream title;

    Folder(Stream stream, Cocos2dxEditBox cocos2dxEditBox) {
        this.title = stream;
        this.text = cocos2dxEditBox;
    }

    public void onFocusChange(View view, boolean z) {
        String $r10;
        String $r9;
        this.text.setTag(true);
        this.text.setChangedTextProgrammatically(false);
        if (z) {
            Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new MonthByWeekFragment$2(this));
            Cocos2dxEditBox $r2 = this.text;
            $r2.setSelection($r2.getText().length());
            Cocos2dxEditBoxHelper.mFrameLayout.setEnableForceDoLayout(true);
            Cocos2dxEditBoxHelper.mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(true);
            $r9 = Cocos2dxEditBoxHelper.CLASS_NAME;
            $r10 = "edit box get focus";
        } else {
            this.text.setVisibility(8);
            Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new MainActivity$3(this, new String(this.text.getText().toString())));
            Cocos2dxEditBoxHelper.mCocos2dxActivity.hideVirtualButton();
            Cocos2dxEditBoxHelper.mFrameLayout.setEnableForceDoLayout(false);
            $r9 = Cocos2dxEditBoxHelper.CLASS_NAME;
            $r10 = "edit box lose focus";
        }
        Log.d($r9, $r10);
    }
}

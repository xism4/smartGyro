package org.cocos2dx.lib;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: org.cocos2dx.lib.n */
class C0992n implements TextWatcher {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxEditBox f2678a;

    /* renamed from: b */
    final /* synthetic */ C0908A f2679b;

    C0992n(C0908A a, Cocos2dxEditBox cocos2dxEditBox) {
        this.f2679b = a;
        this.f2678a = cocos2dxEditBox;
    }

    public void afterTextChanged(Editable editable) {
        if (!this.f2678a.getChangedTextProgrammatically().booleanValue() && ((Boolean) this.f2678a.getTag()).booleanValue()) {
            Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new C0990m(this, editable));
        }
        this.f2678a.setChangedTextProgrammatically(false);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}

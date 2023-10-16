package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.I */
class C0930I implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2529a;

    /* renamed from: b */
    final /* synthetic */ String f2530b;

    C0930I(int i, String str) {
        this.f2529a = i;
        this.f2530b = str;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2529a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setChangedTextProgrammatically(true);
            cocos2dxEditBox.setText(this.f2530b);
            cocos2dxEditBox.setSelection(cocos2dxEditBox.getText().length());
        }
    }
}

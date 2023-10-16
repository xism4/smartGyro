package org.cocos2dx.package_1;

import android.widget.FrameLayout;

final class Stream implements Runnable {
    final /* synthetic */ int mColor;
    final /* synthetic */ float mLevel;
    final /* synthetic */ int mOffset;
    final /* synthetic */ int mType;
    final /* synthetic */ int mValue;
    final /* synthetic */ int this$0;

    Stream(float f, int i, int i2, int i3, int i4, int i5) {
        this.mLevel = f;
        this.mOffset = i;
        this.mType = i2;
        this.mValue = i3;
        this.mColor = i4;
        this.this$0 = i5;
    }

    public void run() {
        Cocos2dxEditBox $r1 = new Cocos2dxEditBox(Cocos2dxEditBoxHelper.mCocos2dxActivity);
        $r1.setFocusable(true);
        $r1.setFocusableInTouchMode(true);
        $r1.setInputFlag(5);
        $r1.setInputMode(6);
        $r1.setReturnType(0);
        $r1.setHintTextColor(-7829368);
        $r1.setVisibility(8);
        $r1.setBackgroundColor(0);
        $r1.setTextColor(-1);
        $r1.setSingleLine();
        $r1.setOpenGLViewScaleX(this.mLevel);
        $r1.setPadding(Cocos2dxEditBoxHelper.getPadding(this.mLevel), 0, 0, 0);
        FrameLayout.LayoutParams $r3 = new FrameLayout.LayoutParams(-2, -2);
        $r3.leftMargin = this.mOffset;
        $r3.topMargin = this.mType;
        $r3.width = this.mValue;
        $r3.height = this.mColor;
        $r3.gravity = 51;
        Cocos2dxEditBoxHelper.mFrameLayout.addView($r1, $r3);
        $r1.setTag(false);
        $r1.addTextChangedListener(new MainActivity$2(this, $r1));
        $r1.setOnFocusChangeListener(new Folder(this, $r1));
        $r1.setOnKeyListener(new MainActivity$6(this, $r1));
        $r1.setOnEditorActionListener(new MainActivity$1(this, $r1));
        Cocos2dxEditBoxHelper.mEditBoxArray.put(this.this$0, $r1);
    }
}

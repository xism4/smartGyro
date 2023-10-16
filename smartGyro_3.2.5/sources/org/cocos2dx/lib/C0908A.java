package org.cocos2dx.lib;

import android.widget.FrameLayout;

/* renamed from: org.cocos2dx.lib.A */
class C0908A implements Runnable {

    /* renamed from: a */
    final /* synthetic */ float f2476a;

    /* renamed from: b */
    final /* synthetic */ int f2477b;

    /* renamed from: c */
    final /* synthetic */ int f2478c;

    /* renamed from: d */
    final /* synthetic */ int f2479d;

    /* renamed from: e */
    final /* synthetic */ int f2480e;

    /* renamed from: f */
    final /* synthetic */ int f2481f;

    C0908A(float f, int i, int i2, int i3, int i4, int i5) {
        this.f2476a = f;
        this.f2477b = i;
        this.f2478c = i2;
        this.f2479d = i3;
        this.f2480e = i4;
        this.f2481f = i5;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = new Cocos2dxEditBox(Cocos2dxEditBoxHelper.mCocos2dxActivity);
        cocos2dxEditBox.setFocusable(true);
        cocos2dxEditBox.setFocusableInTouchMode(true);
        cocos2dxEditBox.setInputFlag(5);
        cocos2dxEditBox.setInputMode(6);
        cocos2dxEditBox.setReturnType(0);
        cocos2dxEditBox.setHintTextColor(-7829368);
        cocos2dxEditBox.setVisibility(8);
        cocos2dxEditBox.setBackgroundColor(0);
        cocos2dxEditBox.setTextColor(-1);
        cocos2dxEditBox.setSingleLine();
        cocos2dxEditBox.setOpenGLViewScaleX(this.f2476a);
        cocos2dxEditBox.setPadding(Cocos2dxEditBoxHelper.getPadding(this.f2476a), 0, 0, 0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = this.f2477b;
        layoutParams.topMargin = this.f2478c;
        layoutParams.width = this.f2479d;
        layoutParams.height = this.f2480e;
        layoutParams.gravity = 51;
        Cocos2dxEditBoxHelper.mFrameLayout.addView(cocos2dxEditBox, layoutParams);
        cocos2dxEditBox.setTag(false);
        cocos2dxEditBox.addTextChangedListener(new C0992n(this, cocos2dxEditBox));
        cocos2dxEditBox.setOnFocusChangeListener(new C0998q(this, cocos2dxEditBox));
        cocos2dxEditBox.setOnKeyListener(new C1000r(this, cocos2dxEditBox));
        cocos2dxEditBox.setOnEditorActionListener(new C1002s(this, cocos2dxEditBox));
        Cocos2dxEditBoxHelper.mEditBoxArray.put(this.f2481f, cocos2dxEditBox);
    }
}

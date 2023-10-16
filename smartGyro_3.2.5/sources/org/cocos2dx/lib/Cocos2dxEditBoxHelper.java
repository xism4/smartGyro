package org.cocos2dx.lib;

import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.view.inputmethod.InputMethodManager;

public class Cocos2dxEditBoxHelper {
    /* access modifiers changed from: private */
    public static final String TAG = "Cocos2dxEditBoxHelper";
    /* access modifiers changed from: private */
    public static Cocos2dxActivity mCocos2dxActivity = null;
    /* access modifiers changed from: private */
    public static SparseArray<Cocos2dxEditBox> mEditBoxArray = null;
    /* access modifiers changed from: private */
    public static ResizeLayout mFrameLayout = null;
    private static float mPadding = 5.0f;
    private static int mViewTag;

    public Cocos2dxEditBoxHelper(ResizeLayout resizeLayout) {
        mFrameLayout = resizeLayout;
        mCocos2dxActivity = (Cocos2dxActivity) Cocos2dxActivity.getContext();
        mEditBoxArray = new SparseArray<>();
    }

    public static void __editBoxEditingChanged(int i, String str) {
        editBoxEditingChanged(i, str);
    }

    public static void __editBoxEditingDidBegin(int i) {
        editBoxEditingDidBegin(i);
    }

    public static void __editBoxEditingDidEnd(int i, String str, int i2) {
        editBoxEditingDidEnd(i, str, i2);
    }

    public static void closeKeyboard(int i) {
        mCocos2dxActivity.runOnUiThread(new C1016z(i));
    }

    /* access modifiers changed from: private */
    public static void closeKeyboardOnUiThread(int i) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e(TAG, "closeKeyboardOnUiThread doesn't run on UI thread!");
            return;
        }
        Cocos2dxActivity cocos2dxActivity = mCocos2dxActivity;
        InputMethodManager inputMethodManager = (InputMethodManager) Cocos2dxActivity.getContext().getSystemService("input_method");
        Cocos2dxEditBox cocos2dxEditBox = mEditBoxArray.get(i);
        if (cocos2dxEditBox != null) {
            inputMethodManager.hideSoftInputFromWindow(cocos2dxEditBox.getWindowToken(), 0);
            mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(false);
            mCocos2dxActivity.getGLSurfaceView().requestFocus();
            mCocos2dxActivity.hideVirtualButton();
        }
    }

    public static int createEditBox(int i, int i2, int i3, int i4, float f) {
        mCocos2dxActivity.runOnUiThread(new C0908A(f, i, i2, i3, i4, mViewTag));
        int i5 = mViewTag;
        mViewTag = i5 + 1;
        return i5;
    }

    private static native void editBoxEditingChanged(int i, String str);

    private static native void editBoxEditingDidBegin(int i);

    private static native void editBoxEditingDidEnd(int i, String str, int i2);

    public static int getPadding(float f) {
        return (int) (mPadding * f);
    }

    public static void openKeyboard(int i) {
        mCocos2dxActivity.runOnUiThread(new C1014y(i));
    }

    /* access modifiers changed from: private */
    public static void openKeyboardOnUiThread(int i) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e(TAG, "openKeyboardOnUiThread doesn't run on UI thread!");
            return;
        }
        Cocos2dxActivity cocos2dxActivity = mCocos2dxActivity;
        InputMethodManager inputMethodManager = (InputMethodManager) Cocos2dxActivity.getContext().getSystemService("input_method");
        Cocos2dxEditBox cocos2dxEditBox = mEditBoxArray.get(i);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.requestFocus();
            mCocos2dxActivity.getGLSurfaceView().requestLayout();
            inputMethodManager.showSoftInput(cocos2dxEditBox, 0);
            mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(true);
        }
    }

    public static void removeEditBox(int i) {
        mCocos2dxActivity.runOnUiThread(new C0910B(i));
    }

    public static void setEditBoxViewRect(int i, int i2, int i3, int i4, int i5) {
        mCocos2dxActivity.runOnUiThread(new C1012x(i, i2, i3, i4, i5));
    }

    public static void setFont(int i, String str, float f) {
        mCocos2dxActivity.runOnUiThread(new C0912C(i, str, f));
    }

    public static void setFontColor(int i, int i2, int i3, int i4, int i5) {
        mCocos2dxActivity.runOnUiThread(new C0920D(i, i5, i2, i3, i4));
    }

    public static void setInputFlag(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new C1010w(i, i2));
    }

    public static void setInputMode(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new C1008v(i, i2));
    }

    public static void setMaxLength(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new C0926G(i, i2));
    }

    public static void setPlaceHolderText(int i, String str) {
        mCocos2dxActivity.runOnUiThread(new C0922E(i, str));
    }

    public static void setPlaceHolderTextColor(int i, int i2, int i3, int i4, int i5) {
        mCocos2dxActivity.runOnUiThread(new C0924F(i, i5, i2, i3, i4));
    }

    public static void setReturnType(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new C1004t(i, i2));
    }

    public static void setText(int i, String str) {
        mCocos2dxActivity.runOnUiThread(new C0930I(i, str));
    }

    public static void setTextHorizontalAlignment(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new C1006u(i, i2));
    }

    public static void setVisible(int i, boolean z) {
        mCocos2dxActivity.runOnUiThread(new C0928H(i, z));
    }
}

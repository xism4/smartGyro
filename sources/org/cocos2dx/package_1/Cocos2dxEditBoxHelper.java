package org.cocos2dx.package_1;

import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.view.inputmethod.InputMethodManager;
import org.cocos2dx.lib.Cocos2dxEditBox;

public class Cocos2dxEditBoxHelper {
    /* access modifiers changed from: private */
    public static final String CLASS_NAME = "Cocos2dxEditBoxHelper";
    /* access modifiers changed from: private */
    public static Cocos2dxActivity mCocos2dxActivity;
    /* access modifiers changed from: private */
    public static SparseArray<Cocos2dxEditBox> mEditBoxArray;
    /* access modifiers changed from: private */
    public static ResizeLayout mFrameLayout;
    private static float mPadding;
    private static int mViewTag;

    public Cocos2dxEditBoxHelper(ResizeLayout resizeLayout) {
        mFrameLayout = resizeLayout;
        mCocos2dxActivity = (Cocos2dxActivity) Cocos2dxActivity.getContext();
        mEditBoxArray = new SparseArray();
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
        mCocos2dxActivity.runOnUiThread(new AndroidCallableWrapper$2(i));
    }

    /* access modifiers changed from: private */
    public static void closeKeyboardOnUiThread(int i) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e(CLASS_NAME, "closeKeyboardOnUiThread doesn't run on UI thread!");
            return;
        }
        Cocos2dxActivity cocos2dxActivity = mCocos2dxActivity;
        InputMethodManager $r6 = (InputMethodManager) Cocos2dxActivity.getContext().getSystemService("input_method");
        Cocos2dxEditBox $r8 = mEditBoxArray.get(i);
        if ($r8 != null) {
            $r6.hideSoftInputFromWindow($r8.getWindowToken(), 0);
            mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(false);
            mCocos2dxActivity.getGLSurfaceView().requestFocus();
            mCocos2dxActivity.hideVirtualButton();
        }
    }

    public static int createEditBox(int i, int i2, int i3, int i4, float f) {
        mCocos2dxActivity.runOnUiThread(new Stream(f, i, i2, i3, i4, mViewTag));
        int $i3 = mViewTag;
        mViewTag = $i3 + 1;
        return $i3;
    }

    private static native void editBoxEditingChanged(int i, String str);

    private static native void editBoxEditingDidBegin(int i);

    private static native void editBoxEditingDidEnd(int i, String str, int i2);

    public static int getPadding(float f) {
        return (int) (mPadding * f);
    }

    public static void openKeyboard(int i) {
        mCocos2dxActivity.runOnUiThread(new Fragment(i));
    }

    /* access modifiers changed from: private */
    public static void openKeyboardOnUiThread(int i) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e(CLASS_NAME, "openKeyboardOnUiThread doesn't run on UI thread!");
            return;
        }
        Cocos2dxActivity cocos2dxActivity = mCocos2dxActivity;
        InputMethodManager $r6 = (InputMethodManager) Cocos2dxActivity.getContext().getSystemService("input_method");
        Cocos2dxEditBox $r8 = mEditBoxArray.get(i);
        if ($r8 != null) {
            $r8.requestFocus();
            mCocos2dxActivity.getGLSurfaceView().requestLayout();
            $r6.showSoftInput($r8, 0);
            mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(true);
        }
    }

    public static void removeEditBox(int i) {
        mCocos2dxActivity.runOnUiThread(new ShowcaseView$1(i));
    }

    public static void setEditBoxViewRect(int i, int i2, int i3, int i4, int i5) {
        mCocos2dxActivity.runOnUiThread(new LayoutManager(i, i2, i3, i4, i5));
    }

    public static void setFont(int i, String str, float f) {
        mCocos2dxActivity.runOnUiThread(new BackgroundExecutor$Task(i, str, f));
    }

    public static void setFontColor(int i, int i2, int i3, int i4, int i5) {
        mCocos2dxActivity.runOnUiThread(new ClassWriter(i, i5, i2, i3, i4));
    }

    public static void setInputFlag(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new SweeperPool$Sweeper(i, i2));
    }

    public static void setInputMode(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new AgendaListView$2(i, i2));
    }

    public static void setMaxLength(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new AddSerie$2(i, i2));
    }

    public static void setPlaceHolderText(int i, String str) {
        mCocos2dxActivity.runOnUiThread(new Slider(i, str));
    }

    public static void setPlaceHolderTextColor(int i, int i2, int i3, int i4, int i5) {
        mCocos2dxActivity.runOnUiThread(new MethodWriter(i, i5, i2, i3, i4));
    }

    public static void setReturnType(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new RemoteAppenderStreamClient(i, i2));
    }

    public static void setText(int i, String str) {
        mCocos2dxActivity.runOnUiThread(new MainActivity$5(i, str));
    }

    public static void setTextHorizontalAlignment(int i, int i2) {
        mCocos2dxActivity.runOnUiThread(new FileBrowser$11(i, i2));
    }

    public static void setVisible(int i, boolean z) {
        mCocos2dxActivity.runOnUiThread(new Overlay(i, z));
    }
}

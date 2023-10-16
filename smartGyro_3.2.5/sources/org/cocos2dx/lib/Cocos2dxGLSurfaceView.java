package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

public class Cocos2dxGLSurfaceView extends GLSurfaceView {
    private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
    private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
    private static final String TAG = "Cocos2dxGLSurfaceView";
    /* access modifiers changed from: private */
    public static Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
    /* access modifiers changed from: private */
    public static Cocos2dxTextInputWrapper sCocos2dxTextInputWraper;
    private static Handler sHandler;
    /* access modifiers changed from: private */
    public Cocos2dxEditBox mCocos2dxEditText;
    /* access modifiers changed from: private */
    public Cocos2dxRenderer mCocos2dxRenderer;
    private boolean mMultipleTouchEnabled = true;
    private boolean mSoftKeyboardShown = false;

    public Cocos2dxGLSurfaceView(Context context) {
        super(context);
        initView();
    }

    public Cocos2dxGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public static void closeIMEKeyboard() {
        Message message = new Message();
        message.what = 3;
        sHandler.sendMessage(message);
    }

    private static void dumpMotionEvent(MotionEvent motionEvent) {
        StringBuilder sb = new StringBuilder();
        int action = motionEvent.getAction();
        int i = action & 255;
        sb.append("event ACTION_");
        sb.append(new String[]{"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"}[i]);
        if (i == 5 || i == 6) {
            sb.append("(pid ");
            sb.append(action >> 8);
            sb.append(")");
        }
        sb.append("[");
        int i2 = 0;
        while (i2 < motionEvent.getPointerCount()) {
            sb.append("#");
            sb.append(i2);
            sb.append("(pid ");
            sb.append(motionEvent.getPointerId(i2));
            sb.append(")=");
            sb.append((int) motionEvent.getX(i2));
            sb.append(",");
            sb.append((int) motionEvent.getY(i2));
            i2++;
            if (i2 < motionEvent.getPointerCount()) {
                sb.append(";");
            }
        }
        sb.append("]");
        Log.d(TAG, sb.toString());
    }

    private String getContentText() {
        return this.mCocos2dxRenderer.getContentText();
    }

    public static Cocos2dxGLSurfaceView getInstance() {
        return mCocos2dxGLSurfaceView;
    }

    public static void openIMEKeyboard() {
        Message message = new Message();
        message.what = 2;
        message.obj = mCocos2dxGLSurfaceView.getContentText();
        sHandler.sendMessage(message);
    }

    public static void queueAccelerometer(float f, float f2, float f3, long j) {
        mCocos2dxGLSurfaceView.queueEvent(new C0962Y(f, f2, f3, j));
    }

    public void deleteBackward() {
        queueEvent(new C0958W(this));
    }

    public Cocos2dxEditBox getCocos2dxEditText() {
        return this.mCocos2dxEditText;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setEGLContextClientVersion(2);
        setFocusableInTouchMode(true);
        mCocos2dxGLSurfaceView = this;
        sCocos2dxTextInputWraper = new Cocos2dxTextInputWrapper(this);
        sHandler = new C0960X(this);
    }

    public void insertText(String str) {
        queueEvent(new C0956V(this, str));
    }

    public boolean isMultipleTouchEnabled() {
        return this.mMultipleTouchEnabled;
    }

    public boolean isSoftKeyboardShown() {
        return this.mSoftKeyboardShown;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            Cocos2dxVideoHelper.mVideoHandler.sendEmptyMessage(1000);
        } else if (!(i == 66 || i == 82 || i == 85)) {
            switch (i) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    break;
                default:
                    return super.onKeyDown(i, keyEvent);
            }
        }
        queueEvent(new C0952T(this, i));
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (!(i == 4 || i == 66 || i == 82 || i == 85)) {
            switch (i) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    break;
                default:
                    return super.onKeyUp(i, keyEvent);
            }
        }
        queueEvent(new C0954U(this, i));
        return true;
    }

    public void onPause() {
        queueEvent(new C0967aa(this));
        setRenderMode(0);
    }

    public void onResume() {
        super.onResume();
        setRenderMode(1);
        queueEvent(new C0964Z(this));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!isInEditMode()) {
            this.mCocos2dxRenderer.setScreenWidthAndHeight(i, i2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Runnable runnable;
        Runnable runnable2;
        Runnable runnable3;
        int pointerCount = motionEvent.getPointerCount();
        int[] iArr = new int[pointerCount];
        float[] fArr = new float[pointerCount];
        float[] fArr2 = new float[pointerCount];
        if (this.mSoftKeyboardShown) {
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(((Activity) getContext()).getCurrentFocus().getWindowToken(), 0);
            requestFocus();
            this.mSoftKeyboardShown = false;
        }
        for (int i = 0; i < pointerCount; i++) {
            iArr[i] = motionEvent.getPointerId(i);
            fArr[i] = motionEvent.getX(i);
            fArr2[i] = motionEvent.getY(i);
        }
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            runnable = new C0971ca(this, motionEvent.getPointerId(0), fArr[0], fArr2[0]);
        } else if (action != 1) {
            if (action != 2) {
                if (action != 3) {
                    if (action == 5) {
                        int action2 = motionEvent.getAction() >> 8;
                        if (this.mMultipleTouchEnabled || action2 == 0) {
                            runnable3 = new C0969ba(this, motionEvent.getPointerId(action2), motionEvent.getX(action2), motionEvent.getY(action2));
                        }
                    } else if (action == 6) {
                        int action3 = motionEvent.getAction() >> 8;
                        if (this.mMultipleTouchEnabled || action3 == 0) {
                            runnable3 = new C0977fa(this, motionEvent.getPointerId(action3), motionEvent.getX(action3), motionEvent.getY(action3));
                        }
                    }
                    queueEvent(runnable3);
                } else if (!this.mMultipleTouchEnabled) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= pointerCount) {
                            break;
                        } else if (iArr[i2] == 0) {
                            runnable2 = new C0946Q(this, new int[]{0}, new float[]{fArr[i2]}, new float[]{fArr2[i2]});
                            break;
                        } else {
                            i2++;
                        }
                    }
                } else {
                    runnable2 = new C0950S(this, iArr, fArr, fArr2);
                }
                return true;
            } else if (!this.mMultipleTouchEnabled) {
                int i3 = 0;
                while (true) {
                    if (i3 >= pointerCount) {
                        break;
                    } else if (iArr[i3] == 0) {
                        runnable2 = new C0973da(this, new int[]{0}, new float[]{fArr[i3]}, new float[]{fArr2[i3]});
                        break;
                    } else {
                        i3++;
                    }
                }
            } else {
                runnable2 = new C0975ea(this, iArr, fArr, fArr2);
            }
            queueEvent(runnable2);
            return true;
        } else {
            runnable = new C0944P(this, motionEvent.getPointerId(0), fArr[0], fArr2[0]);
        }
        queueEvent(runnable);
        return true;
    }

    public void setCocos2dxEditText(Cocos2dxEditBox cocos2dxEditBox) {
        Cocos2dxTextInputWrapper cocos2dxTextInputWrapper;
        this.mCocos2dxEditText = cocos2dxEditBox;
        Cocos2dxEditBox cocos2dxEditBox2 = this.mCocos2dxEditText;
        if (cocos2dxEditBox2 != null && (cocos2dxTextInputWrapper = sCocos2dxTextInputWraper) != null) {
            cocos2dxEditBox2.setOnEditorActionListener(cocos2dxTextInputWrapper);
            requestFocus();
        }
    }

    public void setCocos2dxRenderer(Cocos2dxRenderer cocos2dxRenderer) {
        this.mCocos2dxRenderer = cocos2dxRenderer;
        setRenderer(this.mCocos2dxRenderer);
    }

    public void setMultipleTouchEnabled(boolean z) {
        this.mMultipleTouchEnabled = z;
    }

    public void setSoftKeyboardShown(boolean z) {
        this.mSoftKeyboardShown = z;
    }
}

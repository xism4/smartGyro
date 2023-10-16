package org.cocos2dx.package_1;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Cocos2dxGLSurfaceView extends GLSurfaceView {
    private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
    private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
    private static final String logTag = "Cocos2dxGLSurfaceView";
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
        Message $r0 = new Message();
        $r0.what = 3;
        sHandler.sendMessage($r0);
    }

    private static void dumpMotionEvent(MotionEvent motionEvent) {
        StringBuilder $r2 = new StringBuilder();
        int $i0 = motionEvent.getAction();
        int $i1 = $i0 & 255;
        $r2.append("event ACTION_");
        $r2.append(new String[]{"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"}[$i1]);
        if ($i1 == 5 || $i1 == 6) {
            $r2.append("(pid ");
            $r2.append($i0 >> 8);
            $r2.append(")");
        }
        $r2.append("[");
        int $i02 = 0;
        while ($i02 < motionEvent.getPointerCount()) {
            $r2.append("#");
            $r2.append($i02);
            $r2.append("(pid ");
            $r2.append(motionEvent.getPointerId($i02));
            $r2.append(")=");
            $r2.append((int) motionEvent.getX($i02));
            $r2.append(",");
            $r2.append((int) motionEvent.getY($i02));
            $i02++;
            if ($i02 < motionEvent.getPointerCount()) {
                $r2.append(";");
            }
        }
        $r2.append("]");
        Log.d(logTag, $r2.toString());
    }

    private String getContentText() {
        return this.mCocos2dxRenderer.getContentText();
    }

    public static Cocos2dxGLSurfaceView getInstance() {
        return mCocos2dxGLSurfaceView;
    }

    public static void openIMEKeyboard() {
        Message $r0 = new Message();
        $r0.what = 2;
        $r0.obj = mCocos2dxGLSurfaceView.getContentText();
        sHandler.sendMessage($r0);
    }

    public static void queueAccelerometer(float f, float f2, float f3, long j) {
        mCocos2dxGLSurfaceView.queueEvent(new SimpleXYSeries(f, f2, f3, j));
    }

    public void deleteBackward() {
        queueEvent(new ImageLoader$3(this));
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
        sHandler = new Launcher$17(this);
    }

    public void insertText(String str) {
        queueEvent(new XMPPService$4(this, str));
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
        queueEvent(new Session(this, i));
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
        queueEvent(new MainActivity$14(this, i));
        return true;
    }

    public void onPause() {
        queueEvent(new Replay(this));
        setRenderMode(0);
    }

    public void onResume() {
        super.onResume();
        setRenderMode(1);
        queueEvent(new WalletActivity$1(this));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!isInEditMode()) {
            this.mCocos2dxRenderer.setScreenWidthAndHeight(i, i2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v0, resolved type: org.cocos2dx.package_1.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v1, resolved type: org.cocos2dx.package_1.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v2, resolved type: org.cocos2dx.package_1.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: org.cocos2dx.package_1.NumberPicker$PressedStateHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v3, resolved type: org.cocos2dx.package_1.NumberPicker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v4, resolved type: org.cocos2dx.package_1.NumberPicker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v5, resolved type: org.cocos2dx.package_1.NumberPicker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: org.cocos2dx.package_1.FlingerListView$2} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v6, resolved type: org.cocos2dx.package_1.NumberPicker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: org.cocos2dx.package_1.ThemeRoulette$1} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v7, resolved type: org.cocos2dx.package_1.NumberPicker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: org.cocos2dx.package_1.FlingerListView$4} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v8, resolved type: org.cocos2dx.package_1.RunProxy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v9, resolved type: org.cocos2dx.package_1.RunProxy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v10, resolved type: org.cocos2dx.package_1.RunProxy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v45, resolved type: org.cocos2dx.package_1.FlingerListView$3} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r36) {
        /*
            r35 = this;
            r0 = r36
            int r5 = r0.getPointerCount()
            int[] r6 = new int[r5]
            float[] r7 = new float[r5]
            float[] r8 = new float[r5]
            r0 = r35
            boolean r9 = r0.mSoftKeyboardShown
            if (r9 == 0) goto L_0x004e
            r0 = r35
            android.content.Context r10 = r0.getContext()
            java.lang.String r12 = "input_method"
            java.lang.Object r11 = r10.getSystemService(r12)
            r14 = r11
            android.view.inputmethod.InputMethodManager r14 = (android.view.inputmethod.InputMethodManager) r14
            r13 = r14
            r0 = r35
            android.content.Context r10 = r0.getContext()
            r16 = r10
            android.app.Activity r16 = (android.app.Activity) r16
            r15 = r16
            android.view.View r17 = r15.getCurrentFocus()
            r0 = r17
            android.os.IBinder r18 = r0.getWindowToken()
            r19 = 0
            r0 = r18
            r1 = r19
            r13.hideSoftInputFromWindow(r0, r1)
            r0 = r35
            r0.requestFocus()
            r19 = 0
            r0 = r19
            r1 = r35
            r1.mSoftKeyboardShown = r0
        L_0x004e:
            r20 = 0
        L_0x0050:
            r0 = r20
            if (r0 >= r5) goto L_0x0075
            r0 = r36
            r1 = r20
            int r21 = r0.getPointerId(r1)
            r6[r20] = r21
            r0 = r36
            r1 = r20
            float r22 = r0.getX(r1)
            r7[r20] = r22
            r0 = r36
            r1 = r20
            float r22 = r0.getY(r1)
            r8[r20] = r22
            int r20 = r20 + 1
            goto L_0x0050
        L_0x0075:
            r0 = r36
            int r20 = r0.getAction()
            r0 = r20
            r0 = r0 & 255(0xff, float:3.57E-43)
            r20 = r0
            if (r20 == 0) goto L_0x01f6
            r19 = 1
            r0 = r20
            r1 = r19
            if (r0 == r1) goto L_0x01d4
            r19 = 2
            r0 = r20
            r1 = r19
            if (r0 == r1) goto L_0x0178
            r19 = 3
            r0 = r20
            r1 = r19
            if (r0 == r1) goto L_0x0125
            r19 = 5
            r0 = r20
            r1 = r19
            if (r0 == r1) goto L_0x00e5
            r19 = 6
            r0 = r20
            r1 = r19
            if (r0 == r1) goto L_0x00ae
            r19 = 1
            return r19
        L_0x00ae:
            r0 = r36
            int r5 = r0.getAction()
            int r5 = r5 >> 8
            r0 = r35
            boolean r9 = r0.mMultipleTouchEnabled
            if (r9 != 0) goto L_0x00c1
            if (r5 == 0) goto L_0x00c1
            r19 = 1
            return r19
        L_0x00c1:
            r0 = r36
            int r20 = r0.getPointerId(r5)
            r0 = r36
            float r22 = r0.getX(r5)
            r0 = r36
            float r23 = r0.getY(r5)
            org.cocos2dx.package_1.FlingerListView$3 r24 = new org.cocos2dx.package_1.FlingerListView$3
            r25 = r24
            r0 = r24
            r1 = r35
            r2 = r20
            r3 = r22
            r4 = r23
            r0.<init>(r1, r2, r3, r4)
            goto L_0x011b
        L_0x00e5:
            r0 = r36
            int r5 = r0.getAction()
            int r5 = r5 >> 8
            r0 = r35
            boolean r9 = r0.mMultipleTouchEnabled
            if (r9 != 0) goto L_0x00f8
            if (r5 == 0) goto L_0x00f8
            r19 = 1
            return r19
        L_0x00f8:
            r0 = r36
            int r20 = r0.getPointerId(r5)
            r0 = r36
            float r22 = r0.getX(r5)
            r0 = r36
            float r23 = r0.getY(r5)
            org.cocos2dx.package_1.RunProxy r26 = new org.cocos2dx.package_1.RunProxy
            r25 = r26
            r0 = r26
            r1 = r35
            r2 = r20
            r3 = r22
            r4 = r23
            r0.<init>(r1, r2, r3, r4)
        L_0x011b:
            r0 = r35
            r1 = r25
            r0.queueEvent(r1)
            r19 = 1
            return r19
        L_0x0125:
            r0 = r35
            boolean r9 = r0.mMultipleTouchEnabled
            if (r9 != 0) goto L_0x016c
            r20 = 0
        L_0x012d:
            r0 = r20
            if (r0 >= r5) goto L_0x021e
            r21 = r6[r20]
            if (r21 != 0) goto L_0x0169
            r19 = 1
            r0 = r19
            int[] r6 = new int[r0]
            r19 = 0
            r27 = 0
            r6[r19] = r27
            r19 = 1
            r0 = r19
            float[] r0 = new float[r0]
            r28 = r0
            r22 = r7[r20]
            r19 = 0
            r28[r19] = r22
            r19 = 1
            r0 = r19
            float[] r7 = new float[r0]
            r22 = r8[r20]
            r19 = 0
            r7[r19] = r22
            org.cocos2dx.package_1.FlingerListView$4 r29 = new org.cocos2dx.package_1.FlingerListView$4
            r25 = r29
            r0 = r29
            r1 = r35
            r2 = r28
            r0.<init>(r1, r6, r2, r7)
            goto L_0x01ca
        L_0x0169:
            int r20 = r20 + 1
            goto L_0x012d
        L_0x016c:
            org.cocos2dx.package_1.ThemeRoulette$1 r30 = new org.cocos2dx.package_1.ThemeRoulette$1
            r25 = r30
            r0 = r30
            r1 = r35
            r0.<init>(r1, r6, r7, r8)
            goto L_0x01ca
        L_0x0178:
            r0 = r35
            boolean r9 = r0.mMultipleTouchEnabled
            if (r9 != 0) goto L_0x01bf
            r20 = 0
        L_0x0180:
            r0 = r20
            if (r0 >= r5) goto L_0x021e
            r21 = r6[r20]
            if (r21 != 0) goto L_0x01bc
            r19 = 1
            r0 = r19
            int[] r6 = new int[r0]
            r19 = 0
            r27 = 0
            r6[r19] = r27
            r19 = 1
            r0 = r19
            float[] r0 = new float[r0]
            r28 = r0
            r22 = r7[r20]
            r19 = 0
            r28[r19] = r22
            r19 = 1
            r0 = r19
            float[] r7 = new float[r0]
            r22 = r8[r20]
            r19 = 0
            r7[r19] = r22
            org.cocos2dx.package_1.FlingerListView$2 r31 = new org.cocos2dx.package_1.FlingerListView$2
            r25 = r31
            r0 = r31
            r1 = r35
            r2 = r28
            r0.<init>(r1, r6, r2, r7)
            goto L_0x01ca
        L_0x01bc:
            int r20 = r20 + 1
            goto L_0x0180
        L_0x01bf:
            org.cocos2dx.package_1.NumberPicker r32 = new org.cocos2dx.package_1.NumberPicker
            r25 = r32
            r0 = r32
            r1 = r35
            r0.<init>(r1, r6, r7, r8)
        L_0x01ca:
            r0 = r35
            r1 = r25
            r0.queueEvent(r1)
            r19 = 1
            return r19
        L_0x01d4:
            r19 = 0
            r0 = r36
            r1 = r19
            int r5 = r0.getPointerId(r1)
            r19 = 0
            r22 = r7[r19]
            r19 = 0
            r23 = r8[r19]
            org.cocos2dx.package_1.NumberPicker$PressedStateHelper r33 = new org.cocos2dx.package_1.NumberPicker$PressedStateHelper
            r25 = r33
            r0 = r33
            r1 = r35
            r2 = r22
            r3 = r23
            r0.<init>(r1, r5, r2, r3)
            goto L_0x0217
        L_0x01f6:
            r19 = 0
            r0 = r36
            r1 = r19
            int r5 = r0.getPointerId(r1)
            r19 = 0
            r22 = r7[r19]
            r19 = 0
            r23 = r8[r19]
            org.cocos2dx.package_1.h r34 = new org.cocos2dx.package_1.h
            r25 = r34
            r0 = r34
            r1 = r35
            r2 = r22
            r3 = r23
            r0.<init>(r1, r5, r2, r3)
        L_0x0217:
            r0 = r35
            r1 = r25
            r0.queueEvent(r1)
        L_0x021e:
            r19 = 1
            return r19
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.package_1.Cocos2dxGLSurfaceView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setCocos2dxEditText(Cocos2dxEditBox cocos2dxEditBox) {
        Cocos2dxTextInputWrapper $r1;
        this.mCocos2dxEditText = cocos2dxEditBox;
        Cocos2dxEditBox $r2 = this.mCocos2dxEditText;
        if ($r2 != null && ($r1 = sCocos2dxTextInputWraper) != null) {
            $r2.setOnEditorActionListener($r1);
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

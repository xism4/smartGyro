package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import org.cocos2dx.lib.Cocos2dxHandler;
import org.cocos2dx.lib.Cocos2dxHelper;

public abstract class Cocos2dxActivity extends Activity implements Cocos2dxHelper.Cocos2dxHelperListener {
    private static final String TAG = "Cocos2dxActivity";
    private static boolean _stateBarIsBlack = true;
    private static Cocos2dxActivity sContext;
    private boolean hasFocus = false;
    private Cocos2dxEditBoxHelper mEditBoxHelper = null;
    protected ResizeLayout mFrameLayout = null;
    private int[] mGLContextAttrs = null;
    protected Cocos2dxGLSurfaceView mGLSurfaceView = null;
    private Cocos2dxHandler mHandler = null;
    private Cocos2dxVideoHelper mVideoHelper = null;
    private Cocos2dxWebViewHelper mWebViewHelper = null;
    private boolean showVirtualButton = false;

    /* renamed from: org.cocos2dx.lib.Cocos2dxActivity$a */
    private class C0914a implements GLSurfaceView.EGLConfigChooser {

        /* renamed from: a */
        private int[] f2489a;

        /* renamed from: b */
        private final int f2490b = 4;

        /* renamed from: c */
        private final int f2491c = 64;

        public C0914a(int[] iArr) {
            this.f2489a = iArr;
        }

        /* renamed from: a */
        private EGLConfig m3107a(EGL10 egl10, EGLDisplay eGLDisplay, int[] iArr) {
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            int[] iArr2 = new int[1];
            if (!egl10.eglChooseConfig(eGLDisplay, iArr, eGLConfigArr, 1, iArr2) || iArr2[0] <= 0) {
                return null;
            }
            return eGLConfigArr[0];
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[][] iArr = new int[4][];
            int[] iArr2 = new int[19];
            iArr2[0] = 12324;
            int[] iArr3 = this.f2489a;
            iArr2[1] = iArr3[0];
            iArr2[2] = 12323;
            iArr2[3] = iArr3[1];
            iArr2[4] = 12322;
            iArr2[5] = iArr3[2];
            iArr2[6] = 12321;
            iArr2[7] = iArr3[3];
            iArr2[8] = 12325;
            iArr2[9] = iArr3[4];
            iArr2[10] = 12326;
            iArr2[11] = iArr3[5];
            iArr2[12] = 12338;
            iArr2[13] = iArr3[6] > 0 ? 1 : 0;
            iArr2[14] = 12337;
            int[] iArr4 = this.f2489a;
            iArr2[15] = iArr4[6];
            iArr2[16] = 12352;
            iArr2[17] = 4;
            iArr2[18] = 12344;
            iArr[0] = iArr2;
            int[] iArr5 = new int[19];
            iArr5[0] = 12324;
            iArr5[1] = iArr4[0];
            iArr5[2] = 12323;
            iArr5[3] = iArr4[1];
            iArr5[4] = 12322;
            iArr5[5] = iArr4[2];
            iArr5[6] = 12321;
            iArr5[7] = iArr4[3];
            iArr5[8] = 12325;
            iArr5[9] = iArr4[4] >= 24 ? 16 : iArr4[4];
            iArr5[10] = 12326;
            int[] iArr6 = this.f2489a;
            iArr5[11] = iArr6[5];
            iArr5[12] = 12338;
            iArr5[13] = iArr6[6] > 0 ? 1 : 0;
            iArr5[14] = 12337;
            int[] iArr7 = this.f2489a;
            iArr5[15] = iArr7[6];
            iArr5[16] = 12352;
            iArr5[17] = 4;
            iArr5[18] = 12344;
            iArr[1] = iArr5;
            int[] iArr8 = new int[19];
            iArr8[0] = 12324;
            iArr8[1] = iArr7[0];
            iArr8[2] = 12323;
            iArr8[3] = iArr7[1];
            iArr8[4] = 12322;
            iArr8[5] = iArr7[2];
            iArr8[6] = 12321;
            iArr8[7] = iArr7[3];
            iArr8[8] = 12325;
            iArr8[9] = iArr7[4] >= 24 ? 16 : iArr7[4];
            iArr8[10] = 12326;
            iArr8[11] = this.f2489a[5];
            iArr8[12] = 12338;
            iArr8[13] = 0;
            iArr8[14] = 12337;
            iArr8[15] = 0;
            iArr8[16] = 12352;
            iArr8[17] = 4;
            iArr8[18] = 12344;
            iArr[2] = iArr8;
            iArr[3] = new int[]{12352, 4, 12344};
            for (int[] a : iArr) {
                EGLConfig a2 = m3107a(egl10, eGLDisplay, a);
                if (a2 != null) {
                    return a2;
                }
            }
            Log.e("device_policy", "Can not select an EGLConfig for rendering.");
            return null;
        }
    }

    public static Context getContext() {
        return sContext;
    }

    private static native int[] getGLContextAttrs();

    private static boolean isAndroidEmulator() {
        String str = Build.MODEL;
        String str2 = TAG;
        Log.d(str2, "model=" + str);
        String str3 = Build.PRODUCT;
        String str4 = TAG;
        Log.d(str4, "product=" + str3);
        boolean z = false;
        if (str3 != null && (str3.equals("sdk") || str3.contains("_sdk") || str3.contains("sdk_"))) {
            z = true;
        }
        String str5 = TAG;
        Log.d(str5, "isEmulator=" + z);
        return z;
    }

    public static void setStateBarColor(boolean z) {
        _stateBarIsBlack = z;
    }

    public Cocos2dxGLSurfaceView getGLSurfaceView() {
        return this.mGLSurfaceView;
    }

    /* access modifiers changed from: protected */
    public void hideVirtualButton() {
        if (this.showVirtualButton) {
            Log.d("hideVirtualButton", "001");
            return;
        }
        Log.d("hideVirtualButton", "002");
        if (Build.VERSION.SDK_INT >= 19) {
            Class cls = View.class;
            try {
                int intValue = ((Integer) Cocos2dxReflectionHelper.getConstantValue(cls, "SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION")).intValue();
                int intValue2 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(cls, "SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN")).intValue();
                int intValue3 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(cls, "SYSTEM_UI_FLAG_HIDE_NAVIGATION")).intValue();
                int intValue4 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(cls, "SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN")).intValue();
                int intValue5 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(cls, "SYSTEM_UI_FLAG_IMMERSIVE_STICKY")).intValue();
                int intValue6 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(cls, "SYSTEM_UI_FLAG_LAYOUT_STABLE")).intValue();
                if (_stateBarIsBlack) {
                    Object[] objArr = {Integer.valueOf(intValue4 | intValue6 | intValue | intValue2 | intValue3 | 8192 | intValue5)};
                    Cocos2dxReflectionHelper.invokeInstanceMethod(getWindow().getDecorView(), "setSystemUiVisibility", new Class[]{Integer.TYPE}, objArr);
                } else {
                    Object[] objArr2 = {Integer.valueOf(intValue4 | intValue6 | intValue | intValue2 | intValue3 | intValue5)};
                    Cocos2dxReflectionHelper.invokeInstanceMethod(getWindow().getDecorView(), "setSystemUiVisibility", new Class[]{Integer.TYPE}, objArr2);
                }
            } catch (NullPointerException e) {
                Log.e(TAG, "hideVirtualButton", e);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(0);
        }
    }

    public void init() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        this.mFrameLayout = new ResizeLayout(this);
        this.mFrameLayout.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -2);
        Cocos2dxEditBox cocos2dxEditBox = new Cocos2dxEditBox(this);
        cocos2dxEditBox.setLayoutParams(layoutParams2);
        this.mFrameLayout.addView(cocos2dxEditBox);
        this.mGLSurfaceView = onCreateView();
        this.mFrameLayout.addView(this.mGLSurfaceView, 1);
        this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
        this.mGLSurfaceView.setCocos2dxEditText(cocos2dxEditBox);
        setContentView(this.mFrameLayout);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        for (PreferenceManager.OnActivityResultListener onActivityResult : Cocos2dxHelper.getOnActivityResultListeners()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!isTaskRoot()) {
            finish();
            Log.w(TAG, "[Workaround] Ignore the activity started from icon!");
            return;
        }
        hideVirtualButton();
        onLoadNativeLibraries();
        sContext = this;
        this.mHandler = new Cocos2dxHandler(this);
        Cocos2dxHelper.init(this);
        this.mGLContextAttrs = getGLContextAttrs();
        init();
        if (this.mVideoHelper == null) {
            this.mVideoHelper = new Cocos2dxVideoHelper(this, this.mFrameLayout);
        }
        if (this.mWebViewHelper == null) {
            this.mWebViewHelper = new Cocos2dxWebViewHelper(this.mFrameLayout);
        }
        if (this.mEditBoxHelper == null) {
            this.mEditBoxHelper = new Cocos2dxEditBoxHelper(this.mFrameLayout);
        }
        getWindow().setSoftInputMode(32);
        setVolumeControlStream(3);
        Cocos2dxEngineDataManager.init(this, this.mGLSurfaceView);
    }

    public Cocos2dxGLSurfaceView onCreateView() {
        Cocos2dxGLSurfaceView cocos2dxGLSurfaceView = new Cocos2dxGLSurfaceView(this);
        if (this.mGLContextAttrs[3] > 0) {
            cocos2dxGLSurfaceView.getHolder().setFormat(-3);
        }
        cocos2dxGLSurfaceView.setEGLConfigChooser(new C0914a(this.mGLContextAttrs));
        return cocos2dxGLSurfaceView;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Cocos2dxAudioFocusManager.m3110b(this);
        super.onDestroy();
        Cocos2dxEngineDataManager.destroy();
    }

    /* access modifiers changed from: protected */
    public void onLoadNativeLibraries() {
        try {
            System.loadLibrary(getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("android.app.lib_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "onPause()");
        super.onPause();
        Cocos2dxAudioFocusManager.m3110b(this);
        Cocos2dxHelper.onPause();
        this.mGLSurfaceView.onPause();
        Cocos2dxEngineDataManager.pause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
        Cocos2dxAudioFocusManager.m3109a((Context) this);
        hideVirtualButton();
        resumeIfHasFocus();
        Cocos2dxEngineDataManager.resume();
    }

    public void onWindowFocusChanged(boolean z) {
        String str = TAG;
        Log.d(str, "onWindowFocusChanged() hasFocus=" + z);
        super.onWindowFocusChanged(z);
        this.hasFocus = z;
        resumeIfHasFocus();
    }

    /* access modifiers changed from: protected */
    public void resumeIfHasFocus() {
        if (this.hasFocus) {
            hideVirtualButton();
            Cocos2dxHelper.onResume();
            this.mGLSurfaceView.onResume();
        }
    }

    public void runOnGLThread(Runnable runnable) {
        this.mGLSurfaceView.queueEvent(runnable);
    }

    public void setEnableVirtualButton(boolean z) {
        this.showVirtualButton = z;
    }

    public void setKeepScreenOn(boolean z) {
        runOnUiThread(new C0966a(this, z));
    }

    public void showDialog(String str, String str2) {
        Message message = new Message();
        message.what = 1;
        message.obj = new Cocos2dxHandler.DialogMessage(str, str2);
        this.mHandler.sendMessage(message);
    }
}

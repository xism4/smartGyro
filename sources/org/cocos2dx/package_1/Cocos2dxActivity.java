package org.cocos2dx.package_1;

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
import org.cocos2dx.package_1.Cocos2dxHandler;
import org.cocos2dx.package_1.Cocos2dxHelper;

public abstract class Cocos2dxActivity extends Activity implements Cocos2dxHelper.Cocos2dxHelperListener {
    private static final String TAG = "Cocos2dxActivity";
    private static boolean _stateBarIsBlack;
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

    class a implements GLSurfaceView.EGLConfigChooser {
        private final int ScreenHeight = 64;
        private int[] mConfigSpec;
        private final int verticalAlignment = 4;

        public a(int[] iArr) {
            this.mConfigSpec = iArr;
        }

        private EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, int[] iArr) {
            EGLConfig[] $r2 = new EGLConfig[1];
            int[] $r5 = new int[1];
            if (!egl10.eglChooseConfig(eGLDisplay, iArr, $r2, 1, $r5) || $r5[0] <= 0) {
                return null;
            }
            return $r2[0];
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[][] $r4 = new int[4][];
            int[] $r5 = new int[19];
            $r5[0] = 12324;
            int[] $r6 = this.mConfigSpec;
            $r5[1] = $r6[0];
            $r5[2] = 12323;
            $r5[3] = $r6[1];
            $r5[4] = 12322;
            $r5[5] = $r6[2];
            $r5[6] = 12321;
            $r5[7] = $r6[3];
            $r5[8] = 12325;
            $r5[9] = $r6[4];
            $r5[10] = 12326;
            $r5[11] = $r6[5];
            $r5[12] = 12338;
            $r5[13] = $r6[6] > 0 ? 1 : 0;
            $r5[14] = 12337;
            int[] $r62 = this.mConfigSpec;
            $r5[15] = $r62[6];
            $r5[16] = 12352;
            $r5[17] = 4;
            $r5[18] = 12344;
            $r4[0] = $r5;
            int[] $r52 = new int[19];
            $r52[0] = 12324;
            $r52[1] = $r62[0];
            $r52[2] = 12323;
            $r52[3] = $r62[1];
            $r52[4] = 12322;
            $r52[5] = $r62[2];
            $r52[6] = 12321;
            $r52[7] = $r62[3];
            $r52[8] = 12325;
            $r52[9] = $r62[4] >= 24 ? 16 : $r62[4];
            $r52[10] = 12326;
            int[] $r63 = this.mConfigSpec;
            $r52[11] = $r63[5];
            $r52[12] = 12338;
            $r52[13] = $r63[6] > 0 ? 1 : 0;
            $r52[14] = 12337;
            int[] $r64 = this.mConfigSpec;
            $r52[15] = $r64[6];
            $r52[16] = 12352;
            $r52[17] = 4;
            $r52[18] = 12344;
            $r4[1] = $r52;
            int[] $r53 = new int[19];
            $r53[0] = 12324;
            $r53[1] = $r64[0];
            $r53[2] = 12323;
            $r53[3] = $r64[1];
            $r53[4] = 12322;
            $r53[5] = $r64[2];
            $r53[6] = 12321;
            $r53[7] = $r64[3];
            $r53[8] = 12325;
            $r53[9] = $r64[4] >= 24 ? 16 : $r64[4];
            $r53[10] = 12326;
            $r53[11] = this.mConfigSpec[5];
            $r53[12] = 12338;
            $r53[13] = 0;
            $r53[14] = 12337;
            $r53[15] = 0;
            $r53[16] = 12352;
            $r53[17] = 4;
            $r53[18] = 12344;
            $r4[2] = $r53;
            $r4[3] = new int[]{12352, 4, 12344};
            for (int[] $r54 : $r4) {
                EGLConfig $r3 = chooseConfig(egl10, eGLDisplay, $r54);
                if ($r3 != null) {
                    return $r3;
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
        String $r0 = Build.MODEL;
        String $r1 = TAG;
        Log.d($r1, "model=" + $r0);
        String $r12 = Build.PRODUCT;
        String $r02 = TAG;
        Log.d($r02, "product=" + $r12);
        boolean $z0 = false;
        if ($r12 != null && ($r12.equals("sdk") || $r12.contains("_sdk") || $r12.contains("sdk_"))) {
            $z0 = true;
        }
        String $r13 = TAG;
        Log.d($r13, "isEmulator=" + $z0);
        return $z0;
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
            try {
                int $i1 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION")).intValue();
                int $i2 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN")).intValue();
                int $i3 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_HIDE_NAVIGATION")).intValue();
                int $i4 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN")).intValue();
                int $i0 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_IMMERSIVE_STICKY")).intValue();
                int $i5 = ((Integer) Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_LAYOUT_STABLE")).intValue();
                if (_stateBarIsBlack) {
                    Object[] $r3 = new Object[1];
                    int $i42 = $i4 | $i5 | $i1 | $i2 | $i3;
                    int i = $i42;
                    $r3[0] = Integer.valueOf($i42 | 8192 | $i0);
                    View $r5 = getWindow().getDecorView();
                    Class[] $r6 = new Class[1];
                    $r6[0] = Integer.TYPE;
                    Cocos2dxReflectionHelper.invokeInstanceMethod($r5, "setSystemUiVisibility", $r6, $r3);
                } else {
                    Object[] $r32 = new Object[1];
                    $r32[0] = Integer.valueOf($i4 | $i5 | $i1 | $i2 | $i3 | $i0);
                    View $r52 = getWindow().getDecorView();
                    Class[] $r62 = new Class[1];
                    $r62[0] = Integer.TYPE;
                    Cocos2dxReflectionHelper.invokeInstanceMethod($r52, "setSystemUiVisibility", $r62, $r32);
                }
            } catch (NullPointerException $r8) {
                Log.e(TAG, "hideVirtualButton", $r8);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(0);
        }
    }

    public void init() {
        ViewGroup.LayoutParams $r2 = new ViewGroup.LayoutParams(-1, -1);
        this.mFrameLayout = new ResizeLayout(this);
        this.mFrameLayout.setLayoutParams($r2);
        ViewGroup.LayoutParams $r22 = new ViewGroup.LayoutParams(-1, -2);
        Cocos2dxEditBox $r4 = new Cocos2dxEditBox(this);
        $r4.setLayoutParams($r22);
        this.mFrameLayout.addView($r4);
        this.mGLSurfaceView = onCreateView();
        this.mFrameLayout.addView(this.mGLSurfaceView, 1);
        this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
        this.mGLSurfaceView.setCocos2dxEditText($r4);
        setContentView(this.mFrameLayout);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        for (PreferenceManager.OnActivityResultListener $r5 : Cocos2dxHelper.getOnActivityResultListeners()) {
            $r5.onActivityResult(i, i2, intent);
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
        Cocos2dxGLSurfaceView $r1 = new Cocos2dxGLSurfaceView(this);
        if (this.mGLContextAttrs[3] > 0) {
            $r1.getHolder().setFormat(-3);
        }
        $r1.setEGLConfigChooser(new a(this.mGLContextAttrs));
        return $r1;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Cocos2dxAudioFocusManager.unregister(this);
        super.onDestroy();
        Cocos2dxEngineDataManager.destroy();
    }

    /* access modifiers changed from: protected */
    public void onLoadNativeLibraries() {
        try {
            System.loadLibrary(getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("android.app.lib_name"));
        } catch (Exception $r5) {
            $r5.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "onPause()");
        super.onPause();
        Cocos2dxAudioFocusManager.unregister(this);
        Cocos2dxHelper.onPause();
        this.mGLSurfaceView.onPause();
        Cocos2dxEngineDataManager.pause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
        Cocos2dxAudioFocusManager.register(this);
        hideVirtualButton();
        resumeIfHasFocus();
        Cocos2dxEngineDataManager.resume();
    }

    public void onWindowFocusChanged(boolean z) {
        String $r1 = TAG;
        Log.d($r1, "onWindowFocusChanged() hasFocus=" + z);
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
        runOnUiThread(new Godot$1(this, z));
    }

    public void showDialog(String str, String str2) {
        Message $r2 = new Message();
        $r2.what = 1;
        $r2.obj = new Cocos2dxHandler.DialogMessage(str, str2);
        this.mHandler.sendMessage($r2);
    }
}

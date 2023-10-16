package org.cocos2dx.package_1;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.widget.FrameLayout;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.cocos2dx.lib.Cocos2dxWebView;
import org.cocos2dx.package_1.NumberPicker;

public class Cocos2dxWebViewHelper {
    private static final String PAGE_KEY = "Cocos2dxWebViewHelper";
    /* access modifiers changed from: private */
    public static Cocos2dxActivity sCocos2dxActivity;
    private static Handler sHandler;
    /* access modifiers changed from: private */
    public static FrameLayout sLayout;
    private static int viewTag;
    /* access modifiers changed from: private */
    public static SparseArray<Cocos2dxWebView> webViews;

    public Cocos2dxWebViewHelper(FrameLayout frameLayout) {
        sLayout = frameLayout;
        sHandler = new Handler(Looper.myLooper());
        sCocos2dxActivity = (Cocos2dxActivity) Cocos2dxActivity.getContext();
        webViews = new SparseArray();
    }

    public static void _didFailLoading(int i, String str) {
        didFailLoading(i, str);
    }

    public static void _didFinishLoading(int i, String str) {
        didFinishLoading(i, str);
    }

    public static void _onJsCallback(int i, String str) {
        onJsCallback(i, str);
    }

    public static boolean _shouldStartLoading(int i, String str) {
        return !shouldStartLoading(i, str);
    }

    public static Object callInMainThread(Callable callable) {
        FutureTask $r0 = new FutureTask(callable);
        sHandler.post($r0);
        return $r0.get();
    }

    public static boolean canGoBack(int i) {
        try {
            return ((Boolean) callInMainThread(new Observable$21(i))).booleanValue();
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e2) {
            return false;
        }
    }

    public static boolean canGoForward(int i) {
        try {
            return ((Boolean) callInMainThread(new StreamClientImpl$4(i))).booleanValue();
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e2) {
            return false;
        }
    }

    public static int createWebView() {
        sCocos2dxActivity.runOnUiThread(new AsyncAppender$Dispatcher(viewTag));
        int $i0 = viewTag;
        viewTag = $i0 + 1;
        return $i0;
    }

    private static native void didFailLoading(int i, String str);

    private static native void didFinishLoading(int i, String str);

    public static void evaluateJS(int i, String str) {
        sCocos2dxActivity.runOnUiThread(new Server(i, str));
    }

    public static float getOpacityWebView(int i) {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1.0f;
        }
        FutureTask $r0 = new FutureTask(new DatabaseHelper$1(i));
        sCocos2dxActivity.runOnUiThread($r0);
        try {
            return ((Float) $r0.get()).floatValue();
        } catch (Exception $r5) {
            $r5.printStackTrace();
            return 1.0f;
        }
    }

    public static void goBack(int i) {
        sCocos2dxActivity.runOnUiThread(new Sleeper(i));
    }

    public static void goForward(int i) {
        sCocos2dxActivity.runOnUiThread(new NumberPicker.BeginSoftInputOnLongPressCommand(i));
    }

    public static void loadData(int i, String str, String str2, String str3, String str4) {
        sCocos2dxActivity.runOnUiThread(new e(i, str4, str, str2, str3));
    }

    public static void loadFile(int i, String str) {
        sCocos2dxActivity.runOnUiThread(new Task(i, str));
    }

    public static void loadHTMLString(int i, String str, String str2) {
        sCocos2dxActivity.runOnUiThread(new b(i, str2, str));
    }

    public static void loadUrl(int i, String str, boolean z) {
        sCocos2dxActivity.runOnUiThread(new Label(i, z, str));
    }

    private static native void onJsCallback(int i, String str);

    public static void reload(int i) {
        sCocos2dxActivity.runOnUiThread(new ThreadHelper(i));
    }

    public static void removeWebView(int i) {
        sCocos2dxActivity.runOnUiThread(new MainActivity$4(i));
    }

    public static void setBackgroundTransparent(int i) {
        if (Build.VERSION.SDK_INT > 10) {
            sCocos2dxActivity.runOnUiThread(new InitiationListener$1(i));
        }
    }

    public static void setJavascriptInterfaceScheme(int i, String str) {
        sCocos2dxActivity.runOnUiThread(new PlayActivity$7(i, str));
    }

    public static void setOpacityWebView(int i, float f) {
        if (Build.VERSION.SDK_INT > 10) {
            sCocos2dxActivity.runOnUiThread(new FileBrowser$10(i, f));
        }
    }

    public static void setScalesPageToFit(int i, boolean z) {
        sCocos2dxActivity.runOnUiThread(new OpenFileActivity$Finder(i, z));
    }

    public static void setVisible(int i, boolean z) {
        sCocos2dxActivity.runOnUiThread(new Message(i, z));
    }

    public static void setWebViewRect(int i, int i2, int i3, int i4, int i5) {
        sCocos2dxActivity.runOnUiThread(new ExtensionData(i, i2, i3, i4, i5));
    }

    private static native boolean shouldStartLoading(int i, String str);

    public static void stopLoading(int i) {
        sCocos2dxActivity.runOnUiThread(new Model(i));
    }
}

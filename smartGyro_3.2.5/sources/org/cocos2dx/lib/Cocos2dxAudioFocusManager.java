package org.cocos2dx.lib;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

class Cocos2dxAudioFocusManager {

    /* renamed from: a */
    private static AudioManager.OnAudioFocusChangeListener f2493a = new C0976f();

    /* renamed from: a */
    static boolean m3109a(Context context) {
        if (((AudioManager) context.getSystemService("audio")).requestAudioFocus(f2493a, 3, 1) == 1) {
            Log.d("AudioFocusManager", "requestAudioFocus succeed");
            return true;
        }
        Log.e("AudioFocusManager", "requestAudioFocus failed!");
        return false;
    }

    /* renamed from: b */
    static void m3110b(Context context) {
        if (((AudioManager) context.getSystemService("audio")).abandonAudioFocus(f2493a) == 1) {
            Log.d("AudioFocusManager", "abandonAudioFocus succeed!");
        } else {
            Log.e("AudioFocusManager", "abandonAudioFocus failed!");
        }
        Cocos2dxHelper.runOnGLThread(new C0978g());
    }

    /* access modifiers changed from: private */
    public static native void nativeOnAudioFocusChange(int i);
}

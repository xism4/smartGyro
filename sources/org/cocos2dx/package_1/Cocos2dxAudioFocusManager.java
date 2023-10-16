package org.cocos2dx.package_1;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

class Cocos2dxAudioFocusManager {
    private static AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new CopyOnWriteArrayList();

    /* access modifiers changed from: private */
    public static native void nativeOnAudioFocusChange(int i);

    static boolean register(Context context) {
        if (((AudioManager) context.getSystemService("audio")).requestAudioFocus(mAudioFocusListener, 3, 1) == 1) {
            Log.d("AudioFocusManager", "requestAudioFocus succeed");
            return true;
        }
        Log.e("AudioFocusManager", "requestAudioFocus failed!");
        return false;
    }

    static void unregister(Context context) {
        if (((AudioManager) context.getSystemService("audio")).abandonAudioFocus(mAudioFocusListener) == 1) {
            Log.d("AudioFocusManager", "abandonAudioFocus succeed!");
        } else {
            Log.e("AudioFocusManager", "abandonAudioFocus failed!");
        }
        Cocos2dxHelper.runOnGLThread(new Roster());
    }
}

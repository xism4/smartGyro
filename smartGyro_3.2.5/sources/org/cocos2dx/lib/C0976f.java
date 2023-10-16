package org.cocos2dx.lib;

import android.media.AudioManager;
import android.util.Log;

/* renamed from: org.cocos2dx.lib.f */
class C0976f implements AudioManager.OnAudioFocusChangeListener {
    C0976f() {
    }

    public void onAudioFocusChange(int i) {
        Runnable eVar;
        Log.d("AudioFocusManager", "onAudioFocusChange: " + i + ", thread: " + Thread.currentThread().getName());
        if (i == -1) {
            Log.d("AudioFocusManager", "Pause music by AUDIOFOCUS_LOSS");
            eVar = new C0968b(this);
        } else if (i == -2) {
            Log.d("AudioFocusManager", "Pause music by AUDIOFOCUS_LOSS_TRANSILENT");
            eVar = new C0970c(this);
        } else if (i == -3) {
            Log.d("AudioFocusManager", "Lower the volume, keep playing by AUDIOFOCUS_LOSS_TRANSILENT_CAN_DUCK");
            eVar = new C0972d(this);
        } else if (i == 1) {
            Log.d("AudioFocusManager", "Resume music by AUDIOFOCUS_GAIN");
            eVar = new C0974e(this);
        } else {
            return;
        }
        Cocos2dxHelper.runOnGLThread(eVar);
    }
}

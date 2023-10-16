package org.cocos2dx.package_1;

import android.media.AudioManager;
import android.util.Log;

final class CopyOnWriteArrayList implements AudioManager.OnAudioFocusChangeListener {
    CopyOnWriteArrayList() {
    }

    public void onAudioFocusChange(int i) {
        Runnable $r4;
        StringBuilder $r1 = r5;
        StringBuilder r5 = new StringBuilder();
        $r1.append("onAudioFocusChange: ");
        $r1.append(i);
        $r1.append(", thread: ");
        $r1.append(Thread.currentThread().getName());
        Log.d("AudioFocusManager", $r1.toString());
        if (i == -1) {
            Log.d("AudioFocusManager", "Pause music by AUDIOFOCUS_LOSS");
            $r4 = r6;
            Runnable r6 = new SelectSyncedCalendarsMultiAccountAdapter$2(this);
        } else if (i == -2) {
            Log.d("AudioFocusManager", "Pause music by AUDIOFOCUS_LOSS_TRANSILENT");
            $r4 = r7;
            Runnable r7 = new AgendaByDayAdapter$1(this);
        } else if (i == -3) {
            Log.d("AudioFocusManager", "Lower the volume, keep playing by AUDIOFOCUS_LOSS_TRANSILENT_CAN_DUCK");
            $r4 = r8;
            Runnable r8 = new Notifier(this);
        } else if (i == 1) {
            Log.d("AudioFocusManager", "Resume music by AUDIOFOCUS_GAIN");
            $r4 = r9;
            Runnable r9 = new Continuation(this);
        } else {
            return;
        }
        Cocos2dxHelper.runOnGLThread($r4);
    }
}

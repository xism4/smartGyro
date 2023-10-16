package org.cocos2dx.Actors;

import android.content.Intent;

public interface BroadcastCallback {
    void connected(Intent intent);

    void data(Intent intent, byte[] bArr);

    void disconnected(Intent intent);

    void discovered(Intent intent);
}

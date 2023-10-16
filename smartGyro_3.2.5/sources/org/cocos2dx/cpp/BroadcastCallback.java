package org.cocos2dx.cpp;

import android.content.Intent;

public interface BroadcastCallback {
    void connected(Intent intent);

    void data(Intent intent, byte[] bArr);

    void disconnected(Intent intent);

    void discovered(Intent intent);
}

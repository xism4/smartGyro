package org.cocos2dx.package_1;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import lombok.extern.asm.ByteVector;
import lombok.extern.asm.b;

final class SettingsActivity$1 implements ServiceConnection {
    SettingsActivity$1() {
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ByteVector unused = Cocos2dxHelper.mGameServiceBinder = b.a(iBinder);
        Cocos2dxHelper.fastLoading(7);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Cocos2dxHelper.sActivity.getApplicationContext().unbindService(Cocos2dxHelper.connection);
    }
}

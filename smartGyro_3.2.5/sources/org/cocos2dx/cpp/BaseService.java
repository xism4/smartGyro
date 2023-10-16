package org.cocos2dx.cpp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BaseService extends Service {
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public <T extends BaseService> T getService() {
            return BaseService.this;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}

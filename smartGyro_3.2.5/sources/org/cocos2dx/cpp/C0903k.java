package org.cocos2dx.cpp;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import org.cocos2dx.cpp.BaseService;

/* renamed from: org.cocos2dx.cpp.k */
class C0903k implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ BluetoothHolder f2462a;

    C0903k(BluetoothHolder bluetoothHolder) {
        this.f2462a = bluetoothHolder;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.e(BluetoothHolder.TAG, "管理服务生命周期on");
        BluetoothLeService unused = this.f2462a.bluetoothLeService = (BluetoothLeService) ((BaseService.LocalBinder) iBinder).getService();
        if (!this.f2462a.bluetoothLeService.initialize()) {
            Log.e(BluetoothHolder.TAG, "无法初始化蓝牙");
        }
        if (!TextUtils.isEmpty(this.f2462a.deviceAddress) && !TextUtils.isEmpty(this.f2462a.deviceName)) {
            Log.e(BluetoothHolder.TAG, "初始化成功启动自动连接到设备");
            BluetoothHolder bluetoothHolder = this.f2462a;
            bluetoothHolder.connect(bluetoothHolder.deviceName, this.f2462a.deviceAddress);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Log.e(BluetoothHolder.TAG, "管理服务生命周期off");
        BluetoothLeService unused = this.f2462a.bluetoothLeService = null;
        boolean unused2 = this.f2462a.connected = false;
    }
}

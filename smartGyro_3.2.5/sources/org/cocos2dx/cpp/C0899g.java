package org.cocos2dx.cpp;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import java.util.Iterator;

/* renamed from: org.cocos2dx.cpp.g */
class C0899g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ BluetoothDevice f2456a;

    /* renamed from: b */
    final /* synthetic */ byte[] f2457b;

    /* renamed from: c */
    final /* synthetic */ C0900h f2458c;

    C0899g(C0900h hVar, BluetoothDevice bluetoothDevice, byte[] bArr) {
        this.f2458c = hVar;
        this.f2456a = bluetoothDevice;
        this.f2457b = bArr;
    }

    public void run() {
        BluetoothDevice bluetoothDevice = this.f2456a;
        if (bluetoothDevice != null && bluetoothDevice.getName() != null && this.f2456a.getName().length() >= 2) {
            Iterator it = AppActivity.devices.iterator();
            while (it.hasNext()) {
                if (((BluetoothDevice) it.next()).getName().equals(this.f2456a.getName())) {
                    return;
                }
            }
            AppActivity._msgList.add(this.f2457b);
            AppActivity.devices.add(this.f2456a);
            String unused = AppActivity._DevicName = AppActivity._DevicName + this.f2456a.getName() + "*";
            if (AppActivity._isAutoConnect && AppActivity._defaultDeviceName.equals(this.f2456a.getName())) {
                boolean unused2 = AppActivity._isAutoConnect = false;
                AppActivity.ReadMsg2(this.f2457b);
                this.f2458c.f2459a.FinshScanBLE();
                BluetoothHolder.getInstance().connect(this.f2456a.getName(), this.f2456a.getAddress());
            }
            Log.d("AppActivity", "add device name:" + this.f2456a.getName());
        }
    }
}

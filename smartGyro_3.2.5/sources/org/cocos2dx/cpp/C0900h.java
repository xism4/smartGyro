package org.cocos2dx.cpp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

/* renamed from: org.cocos2dx.cpp.h */
class C0900h implements BluetoothAdapter.LeScanCallback {

    /* renamed from: a */
    final /* synthetic */ AppActivity f2459a;

    C0900h(AppActivity appActivity) {
        this.f2459a = appActivity;
    }

    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getName().length() >= 2) {
            if (bluetoothDevice.getName().startsWith("smart") || bluetoothDevice.getName().startsWith("Smart") || bluetoothDevice.getName().startsWith("M0")) {
                this.f2459a.runOnUiThread(new C0899g(this, bluetoothDevice, bArr));
            }
        }
    }
}

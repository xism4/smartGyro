package org.cocos2dx.Actors;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

class UploadActivity$2 implements BluetoothAdapter.LeScanCallback {
    final /* synthetic */ AppActivity this$0;

    UploadActivity$2(AppActivity appActivity) {
        this.this$0 = appActivity;
    }

    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getName().length() >= 2) {
            if (bluetoothDevice.getName().startsWith("smart") || bluetoothDevice.getName().startsWith("Smart") || bluetoothDevice.getName().startsWith("M0")) {
                this.this$0.runOnUiThread(new MonthByWeekFragment$2(this, bluetoothDevice, bArr));
            }
        }
    }
}

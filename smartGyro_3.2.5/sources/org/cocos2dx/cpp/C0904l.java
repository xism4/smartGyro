package org.cocos2dx.cpp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* renamed from: org.cocos2dx.cpp.l */
class C0904l extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ BluetoothHolder f2463a;

    C0904l(BluetoothHolder bluetoothHolder) {
        this.f2463a = bluetoothHolder;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothService.ACTION_GATT_CONNECTED.equals(action)) {
            if (this.f2463a.broadcastCallback != null) {
                this.f2463a.broadcastCallback.connected(intent);
                boolean unused = this.f2463a.connected = true;
            }
        } else if (BluetoothService.ACTION_GATT_DISCONNECTED.equals(action)) {
            boolean unused2 = this.f2463a.connected = false;
            if (this.f2463a.broadcastCallback != null) {
                this.f2463a.broadcastCallback.disconnected(intent);
            }
        } else if (BluetoothService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
            BluetoothHolder bluetoothHolder = this.f2463a;
            bluetoothHolder.searchGattServices(bluetoothHolder.bluetoothLeService.getSupportedGattServices());
            if (this.f2463a.broadcastCallback != null) {
                this.f2463a.broadcastCallback.discovered(intent);
            }
        } else if (!BluetoothService.ACTION_DATA_AVAILABLE.equals(action)) {
            boolean unused3 = this.f2463a.connected = false;
        } else if (intent != null && intent.getExtras() != null) {
            byte[] byteArray = intent.getExtras().getByteArray(BluetoothService.EXTRA_DATA);
            if (this.f2463a.broadcastCallback == null || byteArray == null) {
                if (this.f2463a.broadcastCallback == null) {
                    Log.d("onReceive data", "(broadcastCallback == null");
                }
                if (byteArray == null) {
                    Log.d("onReceive data", "(data == null");
                    return;
                }
                return;
            }
            this.f2463a.broadcastCallback.data(intent, byteArray);
        }
    }
}

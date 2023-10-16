package org.cocos2dx.cpp;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;

/* renamed from: org.cocos2dx.cpp.m */
class C0905m extends BluetoothGattCallback {

    /* renamed from: a */
    final /* synthetic */ BluetoothService f2464a;

    C0905m(BluetoothService bluetoothService) {
        this.f2464a = bluetoothService;
    }

    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.f2464a.broadcastUpdate(BluetoothService.ACTION_DATA_AVAILABLE, bluetoothGattCharacteristic);
    }

    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (i == 0) {
            Log.d(BluetoothService.TAG, "status == BluetoothGatt.GATT_SUCCESS");
            this.f2464a.broadcastUpdate(BluetoothService.ACTION_DATA_AVAILABLE, bluetoothGattCharacteristic);
            return;
        }
        Log.e(BluetoothService.TAG, "status != BluetoothGatt.GATT_SUCCESS");
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (i2 == 2) {
            int unused = this.f2464a.connectionState = 2;
            this.f2464a.broadcastUpdate(BluetoothService.ACTION_GATT_CONNECTED);
            Log.i(BluetoothService.TAG, "Connected to GATT server.");
            String access$200 = BluetoothService.TAG;
            Log.i(access$200, "Attempting to start service discovery:" + this.f2464a.bluetoothGatt.discoverServices());
        } else if (i2 == 0) {
            int unused2 = this.f2464a.connectionState = 0;
            Log.i(BluetoothService.TAG, "Disconnected from GATT server.");
            this.f2464a.broadcastUpdate(BluetoothService.ACTION_GATT_DISCONNECTED);
        }
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        if (i == 0) {
            this.f2464a.broadcastUpdate(BluetoothService.ACTION_GATT_SERVICES_DISCOVERED);
            return;
        }
        String access$200 = BluetoothService.TAG;
        Log.w(access$200, "onServicesDiscovered received: " + i);
    }
}

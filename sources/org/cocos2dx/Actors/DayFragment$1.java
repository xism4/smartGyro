package org.cocos2dx.Actors;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;

class DayFragment$1 extends BluetoothGattCallback {
    final /* synthetic */ BluetoothService this$0;

    DayFragment$1(BluetoothService bluetoothService) {
        this.this$0 = bluetoothService;
    }

    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.this$0.broadcastUpdate(BluetoothService.ACTION_DATA_AVAILABLE, bluetoothGattCharacteristic);
    }

    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (i == 0) {
            Log.d(BluetoothService.packetWriter, "status == BluetoothGatt.GATT_SUCCESS");
            this.this$0.broadcastUpdate(BluetoothService.ACTION_DATA_AVAILABLE, bluetoothGattCharacteristic);
            return;
        }
        Log.e(BluetoothService.packetWriter, "status != BluetoothGatt.GATT_SUCCESS");
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (i2 == 2) {
            int unused = this.this$0.connectionState = 2;
            this.this$0.broadcastUpdate(BluetoothService.ACTION_GATT_CONNECTED);
            Log.i(BluetoothService.packetWriter, "Connected to GATT server.");
            String $r3 = BluetoothService.packetWriter;
            Log.i($r3, "Attempting to start service discovery:" + this.this$0.bluetoothGatt.discoverServices());
        } else if (i2 == 0) {
            int unused2 = this.this$0.connectionState = 0;
            Log.i(BluetoothService.packetWriter, "Disconnected from GATT server.");
            this.this$0.broadcastUpdate(BluetoothService.ACTION_GATT_DISCONNECTED);
        }
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        if (i == 0) {
            this.this$0.broadcastUpdate(BluetoothService.ACTION_GATT_SERVICES_DISCOVERED);
            return;
        }
        String $r4 = BluetoothService.packetWriter;
        Log.w($r4, "onServicesDiscovered received: " + i);
    }
}

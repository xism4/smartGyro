package org.cocos2dx.cpp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BluetoothService extends BaseService {
    public static final String ACTION_DATA_AVAILABLE = "ACTION_DATA_AVAILABLE";
    public static final String ACTION_GATT_CONNECTED = "ACTION_GATT_CONNECTED";
    public static final String ACTION_GATT_DISCONNECTED = "ACTION_GATT_DISCONNECTED";
    public static final String ACTION_GATT_SERVICES_DISCOVERED = "ACTION_GATT_SERVICES_DISCOVERED";
    public static final UUID CCCD = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final UUID RX_CHAR_UUID = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID RX_CHAR_UUID3 = UUID.fromString("0000ae01-0000-1000-8000-00805f9b34fb");
    public static final UUID RX_CHAR_UUID4 = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    public static final UUID RX_CHAR_UUID5 = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    public static final UUID RX_CHAR_UUID6 = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
    public static final UUID RX_SERVICE_UUID = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID RX_SERVICE_UUID3 = UUID.fromString("0000ae00-0000-1000-8000-00805f9b34fb");
    public static final UUID RX_SERVICE_UUID4 = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    public static final UUID RX_SERVICE_UUID5 = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    /* access modifiers changed from: private */
    public static final String TAG = "BluetoothService";
    public static final UUID TX_CHAR_UUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID TX_CHAR_UUID3 = UUID.fromString("0000ae02-0000-1000-8000-00805f9b34fb");
    public static final UUID TX_CHAR_UUID4 = UUID.fromString("0000fff4-0000-1000-8000-00805f9b34fb");
    public static final UUID TX_CHAR_UUID5 = UUID.fromString("0000fff7-0000-1000-8000-00805f9b34fb");
    public static final UUID TX_CHAR_UUID6 = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
    private int _appType = 1;
    private BluetoothAdapter bluetoothAdapter;
    private String bluetoothDeviceAddress;
    /* access modifiers changed from: private */
    public BluetoothGatt bluetoothGatt;
    private BluetoothManager bluetoothManager;
    /* access modifiers changed from: private */
    public int connectionState = 0;
    private final BluetoothGattCallback gattCallback = new C0905m(this);

    /* access modifiers changed from: private */
    public void broadcastUpdate(String str) {
        sendBroadcast(new Intent(str));
    }

    /* access modifiers changed from: private */
    public void broadcastUpdate(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Intent intent = new Intent(str);
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value != null && value.length > 0) {
            for (int i = 0; i < value.length; i++) {
                value[i] = (byte) (value[i] & 255);
            }
            intent.putExtra(EXTRA_DATA, value);
        }
        sendBroadcast(intent);
    }

    public BluetoothGatt GetBluetoothGatt() {
        return this.bluetoothGatt;
    }

    public void SetAppType(int i) {
        this._appType = i;
    }

    public void close() {
        BluetoothGatt bluetoothGatt2 = this.bluetoothGatt;
        if (bluetoothGatt2 != null) {
            bluetoothGatt2.close();
            this.bluetoothGatt = null;
        }
    }

    public boolean connect(String str) {
        String str2;
        String str3;
        if (this.bluetoothAdapter == null) {
            Log.e(TAG, "BluetoothAdapter没有初始化");
            initialize();
        }
        BluetoothAdapter bluetoothAdapter2 = this.bluetoothAdapter;
        if (bluetoothAdapter2 == null) {
            return false;
        }
        if (str == null) {
            str2 = TAG;
            str3 = "BluetoothAdapter未指明的地址";
        } else {
            BluetoothDevice remoteDevice = bluetoothAdapter2.getRemoteDevice(str);
            if (remoteDevice == null) {
                str2 = TAG;
                str3 = "没有找到该设备，无法连接";
            } else {
                BluetoothGatt bluetoothGatt2 = this.bluetoothGatt;
                if (bluetoothGatt2 != null) {
                    bluetoothGatt2.close();
                    this.bluetoothGatt = null;
                }
                this.bluetoothGatt = remoteDevice.connectGatt(this, false, this.gattCallback);
                Log.d(TAG, "试图创建一个新的连接");
                this.bluetoothDeviceAddress = str;
                this.connectionState = 1;
                return true;
            }
        }
        Log.e(str2, str3);
        return false;
    }

    public void disconnect() {
        BluetoothGatt bluetoothGatt2;
        if (this.bluetoothAdapter == null || (bluetoothGatt2 = this.bluetoothGatt) == null) {
            Log.w(TAG, "BluetoothAdapter没有初始化");
        } else {
            bluetoothGatt2.disconnect();
        }
    }

    public void enableTXNotification() {
        BluetoothGattDescriptor descriptor;
        Log.d(TAG, "enableTXNotification");
        BluetoothGatt bluetoothGatt2 = this.bluetoothGatt;
        if (bluetoothGatt2 != null) {
            int i = this._appType;
            if (i == 2) {
                BluetoothGattService service = bluetoothGatt2.getService(RX_SERVICE_UUID3);
                if (service == null) {
                    Log.e(TAG, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic characteristic = service.getCharacteristic(TX_CHAR_UUID3);
                if (characteristic == null) {
                    Log.e(TAG, "TxChar == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification(characteristic, true);
                descriptor = characteristic.getDescriptor(CCCD);
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else if (i == 3) {
                BluetoothGattService service2 = bluetoothGatt2.getService(RX_SERVICE_UUID4);
                if (service2 == null) {
                    Log.e(TAG, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic characteristic2 = service2.getCharacteristic(TX_CHAR_UUID4);
                if (characteristic2 == null) {
                    Log.e(TAG, "TxChar == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification(characteristic2, true);
                descriptor = characteristic2.getDescriptor(CCCD);
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else if (i == 4) {
                BluetoothGattService service3 = bluetoothGatt2.getService(RX_SERVICE_UUID5);
                if (service3 == null) {
                    Log.e(TAG, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic characteristic3 = service3.getCharacteristic(TX_CHAR_UUID5);
                if (characteristic3 == null) {
                    Log.e(TAG, "TxChar5 == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification(characteristic3, true);
                descriptor = characteristic3.getDescriptor(CCCD);
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else if (i == 5) {
                BluetoothGattService service4 = bluetoothGatt2.getService(RX_SERVICE_UUID5);
                if (service4 == null) {
                    Log.e(TAG, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic characteristic4 = service4.getCharacteristic(TX_CHAR_UUID6);
                if (characteristic4 == null) {
                    Log.e(TAG, "TxChar5 == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification(characteristic4, true);
                descriptor = characteristic4.getDescriptor(CCCD);
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else {
                BluetoothGattService service5 = bluetoothGatt2.getService(RX_SERVICE_UUID);
                if (service5 == null) {
                    Log.e(TAG, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic characteristic5 = service5.getCharacteristic(TX_CHAR_UUID);
                if (characteristic5 == null) {
                    Log.e(TAG, "TxChar == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification(characteristic5, true);
                descriptor = characteristic5.getDescriptor(CCCD);
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            }
            this.bluetoothGatt.writeDescriptor(descriptor);
        }
    }

    public int getConnectionState() {
        return this.connectionState;
    }

    public List<BluetoothGattService> getSupportedGattServices() {
        BluetoothGatt bluetoothGatt2 = this.bluetoothGatt;
        if (bluetoothGatt2 != null) {
            return bluetoothGatt2.getServices();
        }
        Log.e(TAG, "bluetoothGatt == null");
        return new ArrayList();
    }

    public boolean initialize() {
        String str;
        String str2;
        if (this.bluetoothManager == null) {
            this.bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
            if (this.bluetoothManager == null) {
                str = TAG;
                str2 = "无法初始化BluetoothManager";
                Log.e(str, str2);
                return false;
            }
        }
        this.bluetoothAdapter = this.bluetoothManager.getAdapter();
        if (this.bluetoothAdapter != null) {
            return true;
        }
        str = TAG;
        str2 = "无法获得BluetoothAdapter";
        Log.e(str, str2);
        return false;
    }

    public boolean onUnbind(Intent intent) {
        close();
        return super.onUnbind(intent);
    }

    public void readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGatt bluetoothGatt2;
        if (this.bluetoothAdapter == null || (bluetoothGatt2 = this.bluetoothGatt) == null) {
            Log.d(TAG, "BluetoothAdapter没有初始化");
            return;
        }
        boolean readCharacteristic = bluetoothGatt2.readCharacteristic(bluetoothGattCharacteristic);
        String str = TAG;
        Log.d(str, "******** readCharacteristic: " + readCharacteristic);
    }

    public void setCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, int i) {
        String str;
        String str2;
        BluetoothGatt bluetoothGatt2;
        if (this.bluetoothAdapter == null || (bluetoothGatt2 = this.bluetoothGatt) == null) {
            str = TAG;
            str2 = "BluetoothAdapter not initialized";
        } else {
            if (bluetoothGatt2 != null) {
                bluetoothGatt2.setCharacteristicNotification(bluetoothGattCharacteristic, z);
            } else {
                Log.e(TAG, "bluetoothGatt == null");
            }
            if (i != 0) {
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(CCCD);
                if (descriptor == null) {
                    str = TAG;
                    str2 = "clientConfig == null";
                } else if (this.bluetoothGatt != null) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    this.bluetoothGatt.writeDescriptor(descriptor);
                    return;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        Log.e(str, str2);
    }

    public void writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGatt bluetoothGatt2;
        if (this.bluetoothAdapter == null || (bluetoothGatt2 = this.bluetoothGatt) == null) {
            Log.d(TAG, "BluetoothAdapter没有初始化");
        } else {
            bluetoothGatt2.writeCharacteristic(bluetoothGattCharacteristic);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        if (r7.bluetoothGatt.writeCharacteristic(r0) == false) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0067, code lost:
        if (r7.bluetoothGatt.writeCharacteristic(r0) == false) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        if (r7.bluetoothGatt.writeCharacteristic(r0) == false) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c0, code lost:
        if (r7.bluetoothGatt.writeCharacteristic(r0) == false) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ec, code lost:
        if (r7.bluetoothGatt.writeCharacteristic(r0) == false) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f4, code lost:
        android.util.Log.d(TAG, "写入数据成功");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeCharacteristic(byte[] r8) {
        /*
            r7 = this;
            android.bluetooth.BluetoothGatt r0 = r7.bluetoothGatt
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            int r1 = r7._appType
            r2 = 2
            java.lang.String r3 = "写入数据失败"
            java.lang.String r4 = "写入数据成功"
            if (r1 != r2) goto L_0x003b
            java.util.UUID r1 = RX_SERVICE_UUID3
            android.bluetooth.BluetoothGattService r0 = r0.getService(r1)
            if (r0 != 0) goto L_0x001e
            java.lang.String r8 = TAG
            java.lang.String r0 = "RxService3 == null"
            android.util.Log.e(r8, r0)
            return
        L_0x001e:
            java.util.UUID r1 = RX_CHAR_UUID3
            android.bluetooth.BluetoothGattCharacteristic r0 = r0.getCharacteristic(r1)
            if (r0 != 0) goto L_0x002e
            java.lang.String r8 = TAG
            java.lang.String r0 = "sendCharacteristic3 == null"
            android.util.Log.e(r8, r0)
            return
        L_0x002e:
            r0.setValue(r8)
            android.bluetooth.BluetoothGatt r8 = r7.bluetoothGatt
            boolean r8 = r8.writeCharacteristic(r0)
            if (r8 != 0) goto L_0x00f4
            goto L_0x00ee
        L_0x003b:
            r2 = 3
            java.lang.String r5 = "RxService4 == null"
            if (r1 != r2) goto L_0x006b
            java.util.UUID r1 = RX_SERVICE_UUID4
            android.bluetooth.BluetoothGattService r0 = r0.getService(r1)
            if (r0 != 0) goto L_0x004e
            java.lang.String r8 = TAG
            android.util.Log.e(r8, r5)
            return
        L_0x004e:
            java.util.UUID r1 = RX_CHAR_UUID4
            android.bluetooth.BluetoothGattCharacteristic r0 = r0.getCharacteristic(r1)
            if (r0 != 0) goto L_0x005e
            java.lang.String r8 = TAG
            java.lang.String r0 = "sendCharacteristic4 == null"
            android.util.Log.e(r8, r0)
            return
        L_0x005e:
            r0.setValue(r8)
            android.bluetooth.BluetoothGatt r8 = r7.bluetoothGatt
            boolean r8 = r8.writeCharacteristic(r0)
            if (r8 != 0) goto L_0x00f4
            goto L_0x00ee
        L_0x006b:
            r2 = 4
            java.lang.String r6 = "sendCharacteristic5 == null"
            if (r1 != r2) goto L_0x0098
            java.util.UUID r1 = RX_SERVICE_UUID5
            android.bluetooth.BluetoothGattService r0 = r0.getService(r1)
            if (r0 != 0) goto L_0x007e
            java.lang.String r8 = TAG
            android.util.Log.e(r8, r5)
            return
        L_0x007e:
            java.util.UUID r1 = RX_CHAR_UUID5
            android.bluetooth.BluetoothGattCharacteristic r0 = r0.getCharacteristic(r1)
            if (r0 != 0) goto L_0x008c
            java.lang.String r8 = TAG
            android.util.Log.e(r8, r6)
            return
        L_0x008c:
            r0.setValue(r8)
            android.bluetooth.BluetoothGatt r8 = r7.bluetoothGatt
            boolean r8 = r8.writeCharacteristic(r0)
            if (r8 != 0) goto L_0x00f4
            goto L_0x00ee
        L_0x0098:
            r2 = 5
            if (r1 != r2) goto L_0x00c3
            java.util.UUID r1 = RX_SERVICE_UUID5
            android.bluetooth.BluetoothGattService r0 = r0.getService(r1)
            if (r0 != 0) goto L_0x00a9
            java.lang.String r8 = TAG
            android.util.Log.e(r8, r5)
            return
        L_0x00a9:
            java.util.UUID r1 = RX_CHAR_UUID6
            android.bluetooth.BluetoothGattCharacteristic r0 = r0.getCharacteristic(r1)
            if (r0 != 0) goto L_0x00b7
            java.lang.String r8 = TAG
            android.util.Log.e(r8, r6)
            return
        L_0x00b7:
            r0.setValue(r8)
            android.bluetooth.BluetoothGatt r8 = r7.bluetoothGatt
            boolean r8 = r8.writeCharacteristic(r0)
            if (r8 != 0) goto L_0x00f4
            goto L_0x00ee
        L_0x00c3:
            java.util.UUID r1 = RX_SERVICE_UUID
            android.bluetooth.BluetoothGattService r0 = r0.getService(r1)
            if (r0 != 0) goto L_0x00d3
            java.lang.String r8 = TAG
            java.lang.String r0 = "RxService == null"
            android.util.Log.e(r8, r0)
            return
        L_0x00d3:
            java.util.UUID r1 = RX_CHAR_UUID
            android.bluetooth.BluetoothGattCharacteristic r0 = r0.getCharacteristic(r1)
            if (r0 != 0) goto L_0x00e3
            java.lang.String r8 = TAG
            java.lang.String r0 = "sendCharacteristic == null"
            android.util.Log.e(r8, r0)
            return
        L_0x00e3:
            r0.setValue(r8)
            android.bluetooth.BluetoothGatt r8 = r7.bluetoothGatt
            boolean r8 = r8.writeCharacteristic(r0)
            if (r8 != 0) goto L_0x00f4
        L_0x00ee:
            java.lang.String r8 = TAG
            android.util.Log.e(r8, r3)
            goto L_0x00f9
        L_0x00f4:
            java.lang.String r8 = TAG
            android.util.Log.d(r8, r4)
        L_0x00f9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.cpp.BluetoothService.writeCharacteristic(byte[]):void");
    }
}

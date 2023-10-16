package org.cocos2dx.Actors;

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
    public static final UUID TX_CHAR_UUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID TX_CHAR_UUID3 = UUID.fromString("0000ae02-0000-1000-8000-00805f9b34fb");
    public static final UUID TX_CHAR_UUID4 = UUID.fromString("0000fff4-0000-1000-8000-00805f9b34fb");
    public static final UUID TX_CHAR_UUID5 = UUID.fromString("0000fff7-0000-1000-8000-00805f9b34fb");
    public static final UUID TX_CHAR_UUID6 = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
    /* access modifiers changed from: private */
    public static final String packetWriter = "BluetoothService";
    private int _appType = 1;
    private BluetoothAdapter bluetoothAdapter;
    private String bluetoothDeviceAddress;
    /* access modifiers changed from: private */
    public BluetoothGatt bluetoothGatt;
    private BluetoothManager bluetoothManager;
    /* access modifiers changed from: private */
    public int connectionState = 0;
    private final BluetoothGattCallback gattCallback = new DayFragment$1(this);

    /* access modifiers changed from: private */
    public void broadcastUpdate(String str) {
        sendBroadcast(new Intent(str));
    }

    /* access modifiers changed from: private */
    public void broadcastUpdate(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Intent $r1 = new Intent(str);
        byte[] $r2 = bluetoothGattCharacteristic.getValue();
        if ($r2 != null && $r2.length > 0) {
            for (int $i0 = 0; $i0 < $r2.length; $i0++) {
                $r2[$i0] = (byte) ($r2[$i0] & 255);
            }
            $r1.putExtra(EXTRA_DATA, $r2);
        }
        sendBroadcast($r1);
    }

    public BluetoothGatt GetBluetoothGatt() {
        return this.bluetoothGatt;
    }

    public void SetAppType(int i) {
        this._appType = i;
    }

    public void close() {
        BluetoothGatt $r1 = this.bluetoothGatt;
        if ($r1 != null) {
            $r1.close();
            this.bluetoothGatt = null;
        }
    }

    public boolean connect(String $r1) {
        String $r12;
        String $r3;
        if (this.bluetoothAdapter == null) {
            Log.e(packetWriter, "BluetoothAdapter没有初始化");
            initialize();
        }
        BluetoothAdapter $r2 = this.bluetoothAdapter;
        if ($r2 == null) {
            return false;
        }
        if ($r1 == null) {
            $r12 = packetWriter;
            $r3 = "BluetoothAdapter未指明的地址";
        } else {
            BluetoothDevice $r4 = $r2.getRemoteDevice($r1);
            if ($r4 == null) {
                $r12 = packetWriter;
                $r3 = "没有找到该设备，无法连接";
            } else {
                BluetoothGatt $r5 = this.bluetoothGatt;
                if ($r5 != null) {
                    $r5.close();
                    this.bluetoothGatt = null;
                }
                this.bluetoothGatt = $r4.connectGatt(this, false, this.gattCallback);
                Log.d(packetWriter, "试图创建一个新的连接");
                this.bluetoothDeviceAddress = $r1;
                this.connectionState = 1;
                return true;
            }
        }
        Log.e($r12, $r3);
        return false;
    }

    public void disconnect() {
        BluetoothGatt $r2;
        if (this.bluetoothAdapter == null || ($r2 = this.bluetoothGatt) == null) {
            Log.w(packetWriter, "BluetoothAdapter没有初始化");
        } else {
            $r2.disconnect();
        }
    }

    public void enableTXNotification() {
        BluetoothGattDescriptor $r7;
        Log.d(packetWriter, "enableTXNotification");
        BluetoothGatt $r2 = this.bluetoothGatt;
        if ($r2 != null) {
            int $i0 = this._appType;
            if ($i0 == 2) {
                BluetoothGattService $r4 = $r2.getService(RX_SERVICE_UUID3);
                if ($r4 == null) {
                    Log.e(packetWriter, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic $r5 = $r4.getCharacteristic(TX_CHAR_UUID3);
                if ($r5 == null) {
                    Log.e(packetWriter, "TxChar == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification($r5, true);
                BluetoothGattDescriptor $r6 = $r5.getDescriptor(CCCD);
                $r7 = $r6;
                $r6.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else if ($i0 == 3) {
                BluetoothGattService $r42 = $r2.getService(RX_SERVICE_UUID4);
                if ($r42 == null) {
                    Log.e(packetWriter, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic $r52 = $r42.getCharacteristic(TX_CHAR_UUID4);
                if ($r52 == null) {
                    Log.e(packetWriter, "TxChar == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification($r52, true);
                BluetoothGattDescriptor $r62 = $r52.getDescriptor(CCCD);
                $r7 = $r62;
                $r62.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else if ($i0 == 4) {
                BluetoothGattService $r43 = $r2.getService(RX_SERVICE_UUID5);
                if ($r43 == null) {
                    Log.e(packetWriter, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic $r53 = $r43.getCharacteristic(TX_CHAR_UUID5);
                if ($r53 == null) {
                    Log.e(packetWriter, "TxChar5 == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification($r53, true);
                BluetoothGattDescriptor $r63 = $r53.getDescriptor(CCCD);
                $r7 = $r63;
                $r63.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else if ($i0 == 5) {
                BluetoothGattService $r44 = $r2.getService(RX_SERVICE_UUID5);
                if ($r44 == null) {
                    Log.e(packetWriter, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic $r54 = $r44.getCharacteristic(TX_CHAR_UUID6);
                if ($r54 == null) {
                    Log.e(packetWriter, "TxChar5 == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification($r54, true);
                BluetoothGattDescriptor $r64 = $r54.getDescriptor(CCCD);
                $r7 = $r64;
                $r64.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else {
                BluetoothGattService $r45 = $r2.getService(RX_SERVICE_UUID);
                if ($r45 == null) {
                    Log.e(packetWriter, "RxService == null");
                    return;
                }
                BluetoothGattCharacteristic $r55 = $r45.getCharacteristic(TX_CHAR_UUID);
                if ($r55 == null) {
                    Log.e(packetWriter, "TxChar == null");
                    return;
                }
                this.bluetoothGatt.setCharacteristicNotification($r55, true);
                BluetoothGattDescriptor $r65 = $r55.getDescriptor(CCCD);
                $r7 = $r65;
                $r65.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            }
            this.bluetoothGatt.writeDescriptor($r7);
        }
    }

    public int getConnectionState() {
        return this.connectionState;
    }

    public List getSupportedGattServices() {
        BluetoothGatt $r2 = this.bluetoothGatt;
        if ($r2 != null) {
            return $r2.getServices();
        }
        Log.e(packetWriter, "bluetoothGatt == null");
        return new ArrayList();
    }

    public boolean initialize() {
        String $r3;
        String $r4;
        if (this.bluetoothManager == null) {
            this.bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
            if (this.bluetoothManager == null) {
                $r3 = packetWriter;
                $r4 = "无法初始化BluetoothManager";
                Log.e($r3, $r4);
                return false;
            }
        }
        this.bluetoothAdapter = this.bluetoothManager.getAdapter();
        if (this.bluetoothAdapter != null) {
            return true;
        }
        $r3 = packetWriter;
        $r4 = "无法获得BluetoothAdapter";
        Log.e($r3, $r4);
        return false;
    }

    public boolean onUnbind(Intent intent) {
        close();
        return super.onUnbind(intent);
    }

    public void readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGatt $r4;
        if (this.bluetoothAdapter == null || ($r4 = this.bluetoothGatt) == null) {
            Log.d(packetWriter, "BluetoothAdapter没有初始化");
            return;
        }
        boolean $z0 = $r4.readCharacteristic(bluetoothGattCharacteristic);
        String $r5 = packetWriter;
        Log.d($r5, "******** readCharacteristic: " + $z0);
    }

    public void setCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, int i) {
        String $r5;
        String $r7;
        BluetoothGatt $r4;
        if (this.bluetoothAdapter == null || ($r4 = this.bluetoothGatt) == null) {
            $r5 = packetWriter;
            $r7 = "BluetoothAdapter not initialized";
        } else {
            if ($r4 != null) {
                $r4.setCharacteristicNotification(bluetoothGattCharacteristic, z);
            } else {
                Log.e(packetWriter, "bluetoothGatt == null");
            }
            if (i != 0) {
                BluetoothGattDescriptor $r1 = bluetoothGattCharacteristic.getDescriptor(CCCD);
                if ($r1 == null) {
                    $r5 = packetWriter;
                    $r7 = "clientConfig == null";
                } else if (this.bluetoothGatt != null) {
                    $r1.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    this.bluetoothGatt.writeDescriptor($r1);
                    return;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        Log.e($r5, $r7);
    }

    public void writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGatt $r3;
        if (this.bluetoothAdapter == null || ($r3 = this.bluetoothGatt) == null) {
            Log.d(packetWriter, "BluetoothAdapter没有初始化");
        } else {
            $r3.writeCharacteristic(bluetoothGattCharacteristic);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        if (r9.bluetoothGatt.writeCharacteristic(r7) == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        if (r9.bluetoothGatt.writeCharacteristic(r7) == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        if (r9.bluetoothGatt.writeCharacteristic(r7) == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c4, code lost:
        if (r9.bluetoothGatt.writeCharacteristic(r7) == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f0, code lost:
        if (r9.bluetoothGatt.writeCharacteristic(r7) == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00fa, code lost:
        android.util.Log.d(packetWriter, "写入数据成功");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0101, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeCharacteristic(byte[] r10) {
        /*
            r9 = this;
            android.bluetooth.BluetoothGatt r0 = r9.bluetoothGatt
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            int r1 = r9._appType
            r2 = 2
            if (r1 != r2) goto L_0x0038
            java.util.UUID r3 = RX_SERVICE_UUID3
            android.bluetooth.BluetoothGattService r4 = r0.getService(r3)
            if (r4 != 0) goto L_0x001a
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "RxService3 == null"
            android.util.Log.e(r5, r6)
            return
        L_0x001a:
            java.util.UUID r3 = RX_CHAR_UUID3
            android.bluetooth.BluetoothGattCharacteristic r7 = r4.getCharacteristic(r3)
            if (r7 != 0) goto L_0x002a
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "sendCharacteristic3 == null"
            android.util.Log.e(r5, r6)
            return
        L_0x002a:
            r7.setValue(r10)
            android.bluetooth.BluetoothGatt r0 = r9.bluetoothGatt
            boolean r8 = r0.writeCharacteristic(r7)
            if (r8 != 0) goto L_0x00fa
            goto L_0x00f2
        L_0x0038:
            r2 = 3
            if (r1 != r2) goto L_0x0069
            java.util.UUID r3 = RX_SERVICE_UUID4
            android.bluetooth.BluetoothGattService r4 = r0.getService(r3)
            if (r4 != 0) goto L_0x004b
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "RxService4 == null"
            android.util.Log.e(r5, r6)
            return
        L_0x004b:
            java.util.UUID r3 = RX_CHAR_UUID4
            android.bluetooth.BluetoothGattCharacteristic r7 = r4.getCharacteristic(r3)
            if (r7 != 0) goto L_0x005b
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "sendCharacteristic4 == null"
            android.util.Log.e(r5, r6)
            return
        L_0x005b:
            r7.setValue(r10)
            android.bluetooth.BluetoothGatt r0 = r9.bluetoothGatt
            boolean r8 = r0.writeCharacteristic(r7)
            if (r8 != 0) goto L_0x00fa
            goto L_0x00f2
        L_0x0069:
            r2 = 4
            if (r1 != r2) goto L_0x0098
            java.util.UUID r3 = RX_SERVICE_UUID5
            android.bluetooth.BluetoothGattService r4 = r0.getService(r3)
            if (r4 != 0) goto L_0x007c
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "RxService4 == null"
            android.util.Log.e(r5, r6)
            return
        L_0x007c:
            java.util.UUID r3 = RX_CHAR_UUID5
            android.bluetooth.BluetoothGattCharacteristic r7 = r4.getCharacteristic(r3)
            if (r7 != 0) goto L_0x008c
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "sendCharacteristic5 == null"
            android.util.Log.e(r5, r6)
            return
        L_0x008c:
            r7.setValue(r10)
            android.bluetooth.BluetoothGatt r0 = r9.bluetoothGatt
            boolean r8 = r0.writeCharacteristic(r7)
            if (r8 != 0) goto L_0x00fa
            goto L_0x00f2
        L_0x0098:
            r2 = 5
            if (r1 != r2) goto L_0x00c7
            java.util.UUID r3 = RX_SERVICE_UUID5
            android.bluetooth.BluetoothGattService r4 = r0.getService(r3)
            if (r4 != 0) goto L_0x00ab
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "RxService4 == null"
            android.util.Log.e(r5, r6)
            return
        L_0x00ab:
            java.util.UUID r3 = RX_CHAR_UUID6
            android.bluetooth.BluetoothGattCharacteristic r7 = r4.getCharacteristic(r3)
            if (r7 != 0) goto L_0x00bb
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "sendCharacteristic5 == null"
            android.util.Log.e(r5, r6)
            return
        L_0x00bb:
            r7.setValue(r10)
            android.bluetooth.BluetoothGatt r0 = r9.bluetoothGatt
            boolean r8 = r0.writeCharacteristic(r7)
            if (r8 != 0) goto L_0x00fa
            goto L_0x00f2
        L_0x00c7:
            java.util.UUID r3 = RX_SERVICE_UUID
            android.bluetooth.BluetoothGattService r4 = r0.getService(r3)
            if (r4 != 0) goto L_0x00d7
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "RxService == null"
            android.util.Log.e(r5, r6)
            return
        L_0x00d7:
            java.util.UUID r3 = RX_CHAR_UUID
            android.bluetooth.BluetoothGattCharacteristic r7 = r4.getCharacteristic(r3)
            if (r7 != 0) goto L_0x00e7
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "sendCharacteristic == null"
            android.util.Log.e(r5, r6)
            return
        L_0x00e7:
            r7.setValue(r10)
            android.bluetooth.BluetoothGatt r0 = r9.bluetoothGatt
            boolean r8 = r0.writeCharacteristic(r7)
            if (r8 != 0) goto L_0x00fa
        L_0x00f2:
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "写入数据失败"
            android.util.Log.e(r5, r6)
            return
        L_0x00fa:
            java.lang.String r5 = packetWriter
            java.lang.String r6 = "写入数据成功"
            android.util.Log.d(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.Actors.BluetoothService.writeCharacteristic(byte[]):void");
    }
}

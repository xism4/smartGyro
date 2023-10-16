package org.cocos2dx.Actors;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import org.cocos2dx.cpp.BluetoothLeService;

public class BluetoothHolder {
    /* access modifiers changed from: private */
    public static final String LOGTAG = "BluetoothHolder";
    public static final UUID UUID_NOTIFY = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_NOTIFY2 = UUID.fromString("6E400002-B5A3-F393-E0A9-E50E24DCCA9E");
    public static final UUID UUID_NOTIFY3 = UUID.fromString("0000ae01-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_NOTIFY4 = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_NOTIFY5 = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_NOTIFY6 = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_Read3 = UUID.fromString("0000ae02-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_Read4 = UUID.fromString("0000fff4-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_Read5 = UUID.fromString("0000fff7-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_Read6 = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_SERVICE2 = UUID.fromString("6E400001-B5A3-F393-E0A9-E50E24DCCA9E");
    public static final UUID UUID_SERVICE3 = UUID.fromString("0000ae00-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_SERVICE4 = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    public static final UUID UUID_SERVICE5 = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
    private static int _isSmallType = 1;
    private static BluetoothHolder holder = new BluetoothHolder();
    private boolean bindService;
    private BluetoothAdapter bluetoothAdapter;
    /* access modifiers changed from: private */
    public BluetoothLeService bluetoothLeService;
    private BluetoothManager bluetoothManager;
    /* access modifiers changed from: private */
    public BroadcastCallback broadcastCallback;
    private final BroadcastReceiver broadcastReceiver = new WalletTransactionsFragment$TransactionsLoader$2(this);
    private ArrayList<BluetoothGattCharacteristic> characteristics = new ArrayList();
    /* access modifiers changed from: private */
    public boolean connected;
    private Context context;
    /* access modifiers changed from: private */
    public String deviceAddress;
    /* access modifiers changed from: private */
    public String deviceName;
    private HashMap<BluetoothGattService, ArrayList<BluetoothGattCharacteristic>> gattServiceData;
    private BluetoothGattCharacteristic notifyCharacteristic;
    private boolean scanning;
    private final ServiceConnection serviceConnection = new OpenPgpServiceConnection$1(this);

    private BluetoothHolder() {
    }

    public static int IsSmallType() {
        return _isSmallType;
    }

    public static void SetIsSmallType(int i) {
        _isSmallType = i;
    }

    public static BluetoothHolder getInstance() {
        BluetoothHolder $r0 = holder;
        if ($r0.context != null) {
            return $r0;
        }
        throw new NullPointerException("请在此之前先初始化：BluetoothHolder.init(context)");
    }

    public static boolean init(Context context2) {
        BluetoothHolder $r0 = holder;
        $r0.context = context2;
        return $r0.initBluetootAdaper();
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        IntentFilter $r0 = new IntentFilter();
        $r0.addAction(BluetoothService.ACTION_GATT_CONNECTED);
        $r0.addAction(BluetoothService.ACTION_GATT_DISCONNECTED);
        $r0.addAction(BluetoothService.ACTION_GATT_SERVICES_DISCOVERED);
        $r0.addAction(BluetoothService.ACTION_DATA_AVAILABLE);
        return $r0;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void searchGattServices(java.util.List r23) {
        /*
            r22 = this;
            if (r23 != 0) goto L_0x0003
            return
        L_0x0003:
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0 = r22
            r0.gattServiceData = r2
            java.lang.String r3 = LOGTAG
            java.lang.String r4 = "开始遍历可用的服务"
            android.util.Log.d(r3, r4)
            r0 = r23
            java.util.Iterator r5 = r0.iterator()
        L_0x0019:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x02b9
            java.lang.Object r7 = r5.next()
            r9 = r7
            android.bluetooth.BluetoothGattService r9 = (android.bluetooth.BluetoothGattService) r9
            r8 = r9
            java.lang.String r3 = LOGTAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r4 = "Sevice uuid : "
            r10.append(r4)
            java.util.UUID r11 = r8.getUuid()
            java.lang.String r12 = r11.toString()
            r10.append(r12)
            java.lang.String r12 = r10.toString()
            android.util.Log.d(r3, r12)
            java.util.UUID r11 = r8.getUuid()
            java.lang.String r3 = r11.toString()
            java.util.UUID r11 = UUID_SERVICE
            java.lang.String r12 = r11.toString()
            boolean r6 = r3.equalsIgnoreCase(r12)
            if (r6 == 0) goto L_0x0062
            int r13 = _isSmallType
            r14 = 1
            if (r13 != r14) goto L_0x0060
        L_0x005e:
            r15 = 3
            goto L_0x00c4
        L_0x0060:
            r15 = 0
            goto L_0x00c4
        L_0x0062:
            java.util.UUID r11 = r8.getUuid()
            java.lang.String r3 = r11.toString()
            java.util.UUID r11 = UUID_SERVICE2
            java.lang.String r12 = r11.toString()
            boolean r6 = r3.equalsIgnoreCase(r12)
            if (r6 == 0) goto L_0x007b
            r14 = 1
            _isSmallType = r14
            r15 = 1
            goto L_0x00c4
        L_0x007b:
            java.util.UUID r11 = r8.getUuid()
            java.lang.String r3 = r11.toString()
            java.util.UUID r11 = UUID_SERVICE3
            java.lang.String r12 = r11.toString()
            boolean r6 = r3.equalsIgnoreCase(r12)
            if (r6 == 0) goto L_0x0094
            r14 = 1
            _isSmallType = r14
            r15 = 2
            goto L_0x00c4
        L_0x0094:
            java.util.UUID r11 = r8.getUuid()
            java.lang.String r3 = r11.toString()
            java.util.UUID r11 = UUID_SERVICE4
            java.lang.String r12 = r11.toString()
            boolean r6 = r3.equalsIgnoreCase(r12)
            if (r6 == 0) goto L_0x00ac
            r14 = 1
            _isSmallType = r14
            goto L_0x005e
        L_0x00ac:
            java.util.UUID r11 = r8.getUuid()
            java.lang.String r3 = r11.toString()
            java.util.UUID r11 = UUID_SERVICE5
            java.lang.String r12 = r11.toString()
            boolean r6 = r3.equalsIgnoreCase(r12)
            if (r6 == 0) goto L_0x0019
            r14 = 1
            _isSmallType = r14
            r15 = 4
        L_0x00c4:
            java.util.ArrayList r16 = new java.util.ArrayList
            r0 = r16
            r0.<init>()
            r0 = r22
            org.cocos2dx.Actors.BluetoothLeService r0 = r0.bluetoothLeService
            r17 = r0
            r0.SetAppType(r15)
            java.lang.String r3 = LOGTAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r4 = "type:"
            r10.append(r4)
            r10.append(r15)
            java.lang.String r12 = r10.toString()
            android.util.Log.d(r3, r12)
            java.util.UUID r11 = UUID_NOTIFY2
            java.lang.String r3 = r11.toString()
            if (r15 != 0) goto L_0x00f9
            java.util.UUID r11 = UUID_NOTIFY
        L_0x00f4:
            java.lang.String r3 = r11.toString()
            goto L_0x010b
        L_0x00f9:
            r14 = 2
            if (r15 != r14) goto L_0x00ff
            java.util.UUID r11 = UUID_NOTIFY3
            goto L_0x00f4
        L_0x00ff:
            r14 = 3
            if (r15 != r14) goto L_0x0105
            java.util.UUID r11 = UUID_NOTIFY4
            goto L_0x00f4
        L_0x0105:
            r14 = 4
            if (r15 != r14) goto L_0x010b
            java.util.UUID r11 = UUID_NOTIFY5
            goto L_0x00f4
        L_0x010b:
            java.util.List r23 = r8.getCharacteristics()
            r0 = r23
            java.util.Iterator r18 = r0.iterator()
        L_0x0115:
            r0 = r18
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x02ad
            r0 = r18
            java.lang.Object r7 = r0.next()
            r20 = r7
            android.bluetooth.BluetoothGattCharacteristic r20 = (android.bluetooth.BluetoothGattCharacteristic) r20
            r19 = r20
            r0 = r22
            org.cocos2dx.Actors.BluetoothLeService r0 = r0.bluetoothLeService
            r17 = r0
            int r13 = _isSmallType
            r14 = 1
            r0 = r17
            r1 = r19
            r0.setCharacteristicNotification(r1, r14, r13)
            r0 = r19
            java.util.UUID r11 = r0.getUuid()
            java.lang.String r12 = r11.toString()
            boolean r6 = r12.equalsIgnoreCase(r3)
            if (r6 == 0) goto L_0x0177
            r0 = r16
            r1 = r19
            r0.add(r1)
            java.lang.String r12 = LOGTAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
        L_0x0157:
            java.lang.String r4 = "写特征:"
            r10.append(r4)
            r0 = r19
            java.util.UUID r11 = r0.getUuid()
            java.lang.String r21 = r11.toString()
            r0 = r21
            r10.append(r0)
            java.lang.String r21 = r10.toString()
        L_0x016f:
            r0 = r21
            android.util.Log.d(r12, r0)
            goto L_0x027f
        L_0x0177:
            r0 = r19
            java.util.UUID r11 = r0.getUuid()
            java.lang.String r12 = r11.toString()
            java.util.UUID r11 = UUID_NOTIFY6
            java.lang.String r21 = r11.toString()
            r0 = r21
            boolean r6 = r12.equalsIgnoreCase(r0)
            if (r6 == 0) goto L_0x01ad
            r14 = 4
            if (r15 != r14) goto L_0x019e
            r0 = r22
            org.cocos2dx.Actors.BluetoothLeService r0 = r0.bluetoothLeService
            r17 = r0
            r14 = 5
            r0 = r17
            r0.SetAppType(r14)
        L_0x019e:
            r0 = r16
            r1 = r19
            r0.add(r1)
            java.lang.String r12 = LOGTAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            goto L_0x0157
        L_0x01ad:
            r0 = r19
            java.util.UUID r11 = r0.getUuid()
            java.lang.String r12 = r11.toString()
            java.util.UUID r11 = UUID_Read3
            java.lang.String r21 = r11.toString()
            r0 = r21
            boolean r6 = r12.equalsIgnoreCase(r0)
            if (r6 == 0) goto L_0x01cf
            r0 = r16
            r1 = r19
            r0.add(r1)
            goto L_0x027f
        L_0x01cf:
            r0 = r19
            java.util.UUID r11 = r0.getUuid()
            java.lang.String r12 = r11.toString()
            java.util.UUID r11 = UUID_Read4
            java.lang.String r21 = r11.toString()
            r0 = r21
            boolean r6 = r12.equalsIgnoreCase(r0)
            if (r6 == 0) goto L_0x0212
            r0 = r16
            r1 = r19
            r0.add(r1)
            java.lang.String r12 = LOGTAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r21 = "UUID_Read4:"
        L_0x01f7:
            r0 = r21
            r10.append(r0)
            r0 = r19
            java.util.UUID r11 = r0.getUuid()
            java.lang.String r21 = r11.toString()
            r0 = r21
            r10.append(r0)
            java.lang.String r21 = r10.toString()
            goto L_0x016f
        L_0x0212:
            r0 = r19
            java.util.UUID r11 = r0.getUuid()
            java.lang.String r12 = r11.toString()
            java.util.UUID r11 = UUID_Read5
            java.lang.String r21 = r11.toString()
            r0 = r21
            boolean r6 = r12.equalsIgnoreCase(r0)
            if (r6 == 0) goto L_0x023b
            r0 = r16
            r1 = r19
            r0.add(r1)
            java.lang.String r12 = LOGTAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r21 = "UUID_Read5:"
            goto L_0x01f7
        L_0x023b:
            r0 = r19
            java.util.UUID r11 = r0.getUuid()
            java.lang.String r12 = r11.toString()
            java.util.UUID r11 = UUID_Read6
            java.lang.String r21 = r11.toString()
            r0 = r21
            boolean r6 = r12.equalsIgnoreCase(r0)
            if (r6 == 0) goto L_0x0273
            r14 = 4
            if (r15 != r14) goto L_0x0262
            r0 = r22
            org.cocos2dx.Actors.BluetoothLeService r0 = r0.bluetoothLeService
            r17 = r0
            r14 = 5
            r0 = r17
            r0.SetAppType(r14)
        L_0x0262:
            r0 = r16
            r1 = r19
            r0.add(r1)
            java.lang.String r12 = LOGTAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r21 = "UUID_Read6:"
            goto L_0x01f7
        L_0x0273:
            java.lang.String r12 = LOGTAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r21 = "UUID_NOTIFY="
            goto L_0x01f7
        L_0x027f:
            r0 = r16
            r1 = r19
            r0.add(r1)
            java.lang.String r12 = LOGTAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r4 = "特征值:"
            r10.append(r4)
            r0 = r19
            java.util.UUID r11 = r0.getUuid()
            java.lang.String r21 = r11.toString()
            r0 = r21
            r10.append(r0)
            java.lang.String r21 = r10.toString()
            r0 = r21
            android.util.Log.d(r12, r0)
            goto L_0x0115
        L_0x02ad:
            r0 = r22
            java.util.HashMap<android.bluetooth.BluetoothGattService, java.util.ArrayList<android.bluetooth.BluetoothGattCharacteristic>> r2 = r0.gattServiceData
            r0 = r16
            r2.put(r8, r0)
            goto L_0x0019
        L_0x02b9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.Actors.BluetoothHolder.searchGattServices(java.util.List):void");
    }

    public void bindService() {
        if (!this.bindService) {
            Log.d(LOGTAG, "绑定服务");
            this.bindService = this.context.bindService(new Intent(this.context, BluetoothLeService.class), this.serviceConnection, 1);
            if (this.bindService) {
                Log.d(LOGTAG, "绑定成功");
            } else {
                Log.e(LOGTAG, "绑定失败");
            }
        }
    }

    public boolean connect(String str, String str2) {
        Log.e(LOGTAG, "connect");
        if (this.bluetoothLeService != null) {
            this.deviceName = str;
            this.deviceAddress = str2;
            AppSaveData.getInstance().setDataForString("deviceAddress", this.deviceAddress);
            return this.bluetoothLeService.connect(this.deviceAddress);
        }
        Log.e(LOGTAG, "bluetoothLeService == null");
        return false;
    }

    public void disconnect() {
        this.characteristics.clear();
        if (this.bluetoothLeService != null) {
            Log.d(LOGTAG, "断开连接");
            this.bluetoothLeService.disconnect();
        }
    }

    public void enableTXNotification() {
        BluetoothLeService $r1;
        if (_isSmallType != 0 && ($r1 = this.bluetoothLeService) != null) {
            $r1.enableTXNotification();
        }
    }

    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public boolean initBluetootAdaper() {
        if (isSupportedBLE()) {
            this.bluetoothManager = (BluetoothManager) this.context.getSystemService("bluetooth");
            this.bluetoothAdapter = this.bluetoothManager.getAdapter();
            if (this.bluetoothAdapter != null) {
                return true;
            }
            Log.e(LOGTAG, "bluetoothAdapter == null");
            return true;
        }
        Log.d(LOGTAG, "不支持蓝牙");
        return false;
    }

    public boolean isBindService() {
        return this.bindService;
    }

    public boolean isConnected() {
        BluetoothLeService $r1 = this.bluetoothLeService;
        return $r1 != null && $r1.getConnectionState() > 0;
    }

    public boolean isOpen() {
        BluetoothAdapter $r1 = this.bluetoothAdapter;
        if ($r1 != null) {
            return $r1.isEnabled();
        }
        return false;
    }

    public boolean isScanning() {
        return this.scanning;
    }

    public boolean isSupportedBLE() {
        return this.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public boolean openBluetooth() {
        if (this.bluetoothAdapter == null || isOpen()) {
            return false;
        }
        Log.d(LOGTAG, "打开蓝牙");
        return this.bluetoothAdapter.enable();
    }

    public boolean reConnect() {
        Log.d(LOGTAG, "reConnect");
        if (this.bluetoothLeService != null) {
            this.deviceAddress = AppSaveData.getInstance().getDataForString("deviceAddress", this.deviceAddress);
            String $r1 = LOGTAG;
            Log.d($r1, "reConnect:deviceAddress" + this.deviceAddress);
            return this.bluetoothLeService.connect(this.deviceAddress);
        }
        Log.e(LOGTAG, "bluetoothLeService == null");
        return false;
    }

    public void readCharacteristic() {
        ArrayList $r5;
        HashMap $r1 = this.gattServiceData;
        if ($r1 != null && !$r1.isEmpty()) {
            Iterator $r3 = this.gattServiceData.values().iterator();
            while ($r3.hasNext() && ($r5 = $r3.next()) != null) {
                Iterator $r6 = $r5.iterator();
                while ($r6.hasNext()) {
                    BluetoothGattCharacteristic $r7 = (BluetoothGattCharacteristic) $r6.next();
                    int $i1 = $r7.getProperties();
                    if (($i1 | 2) > 0) {
                        BluetoothGattCharacteristic $r8 = this.notifyCharacteristic;
                        if ($r8 != null) {
                            this.bluetoothLeService.setCharacteristicNotification($r8, false, _isSmallType);
                            this.notifyCharacteristic = null;
                        }
                        this.bluetoothLeService.readCharacteristic($r7);
                    }
                    if (($i1 | 16) > 0) {
                        this.notifyCharacteristic = $r7;
                        this.bluetoothLeService.setCharacteristicNotification(this.notifyCharacteristic, true, _isSmallType);
                    }
                }
            }
        }
    }

    public void registerReceiver(BroadcastCallback broadcastCallback2) {
        Log.e(LOGTAG, "已经注册广播接收者");
        this.broadcastCallback = broadcastCallback2;
        this.context.registerReceiver(this.broadcastReceiver, makeGattUpdateIntentFilter());
        if (this.bluetoothLeService != null && !TextUtils.isEmpty(this.deviceAddress)) {
            boolean $z0 = this.bluetoothLeService.connect(this.deviceAddress);
            String $r2 = LOGTAG;
            Log.d($r2, "连接请求的结果：" + $z0);
        }
    }

    public void scanDevice(boolean z, BluetoothAdapter.LeScanCallback leScanCallback) {
        if (this.bluetoothAdapter != null) {
            this.scanning = z;
            if (this.scanning) {
                Log.d(LOGTAG, "开始扫描蓝牙");
                this.bluetoothAdapter.startLeScan((UUID[]) null, leScanCallback);
                return;
            }
            Log.d(LOGTAG, "结束扫描蓝牙");
            this.bluetoothAdapter.stopLeScan(leScanCallback);
            return;
        }
        Log.d(LOGTAG, "bluetoothAdapter == null");
    }

    public void unbindService(boolean z) {
        if (this.bindService) {
            Log.d(LOGTAG, "解除绑定服务");
            this.context.unbindService(this.serviceConnection);
        }
        BluetoothLeService $r4 = this.bluetoothLeService;
        if ($r4 != null) {
            $r4.close();
        }
        this.bindService = false;
        this.bluetoothLeService = null;
    }

    public void unregisterReceiver(boolean z) {
        this.context.unregisterReceiver(this.broadcastReceiver);
        this.broadcastCallback = null;
        if (z) {
            this.deviceAddress = null;
        }
    }

    public void writeCharacteristic(byte[] bArr) {
        BluetoothLeService $r2 = this.bluetoothLeService;
        if ($r2 == null) {
            Log.e(LOGTAG, "bluetoothLeService == null");
        } else if (_isSmallType == 1) {
            $r2.writeCharacteristic(bArr);
        } else {
            HashMap $r4 = this.gattServiceData;
            if ($r4 != null && !$r4.isEmpty()) {
                for (ArrayList<BluetoothGattCharacteristic> $r8 : this.gattServiceData.values()) {
                    Iterator $r9 = $r8.iterator();
                    while ($r9.hasNext()) {
                        BluetoothGattCharacteristic $r10 = (BluetoothGattCharacteristic) $r9.next();
                        int $i1 = $r10.getProperties();
                        this.notifyCharacteristic = null;
                        if (($i1 | 16) > 0) {
                            this.notifyCharacteristic = $r10;
                            BluetoothLeService $r22 = this.bluetoothLeService;
                            if ($r22 != null) {
                                $r22.setCharacteristicNotification($r10, true, _isSmallType);
                            }
                        }
                        if (($i1 | 8) > 0) {
                            $r10.setValue(bArr);
                            if (this.notifyCharacteristic != null) {
                                this.bluetoothLeService.writeCharacteristic($r10);
                            }
                        }
                    }
                }
            }
        }
    }
}

package org.cocos2dx.cpp;

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

public class BluetoothHolder {
    /* access modifiers changed from: private */
    public static final String TAG = "BluetoothHolder";
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
    private final BroadcastReceiver broadcastReceiver = new C0904l(this);
    private ArrayList<BluetoothGattCharacteristic> characteristics = new ArrayList<>();
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
    private final ServiceConnection serviceConnection = new C0903k(this);

    private BluetoothHolder() {
    }

    public static int IsSmallType() {
        return _isSmallType;
    }

    public static void SetIsSmallType(int i) {
        _isSmallType = i;
    }

    public static BluetoothHolder getInstance() {
        BluetoothHolder bluetoothHolder = holder;
        if (bluetoothHolder.context != null) {
            return bluetoothHolder;
        }
        throw new NullPointerException("请在此之前先初始化：BluetoothHolder.init(context)");
    }

    public static boolean init(Context context2) {
        BluetoothHolder bluetoothHolder = holder;
        bluetoothHolder.context = context2;
        return bluetoothHolder.initBluetootAdaper();
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0109  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void searchGattServices(java.util.List<android.bluetooth.BluetoothGattService> r12) {
        /*
            r11 = this;
            if (r12 != 0) goto L_0x0003
            return
        L_0x0003:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r11.gattServiceData = r0
            java.lang.String r0 = TAG
            java.lang.String r1 = "开始遍历可用的服务"
            android.util.Log.d(r0, r1)
            java.util.Iterator r12 = r12.iterator()
        L_0x0015:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x0237
            java.lang.Object r0 = r12.next()
            android.bluetooth.BluetoothGattService r0 = (android.bluetooth.BluetoothGattService) r0
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Sevice uuid : "
            r2.append(r3)
            java.util.UUID r3 = r0.getUuid()
            java.lang.String r3 = r3.toString()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r1, r2)
            java.util.UUID r1 = r0.getUuid()
            java.lang.String r1 = r1.toString()
            java.util.UUID r2 = UUID_SERVICE
            java.lang.String r2 = r2.toString()
            boolean r1 = r1.equalsIgnoreCase(r2)
            r2 = 2
            r3 = 3
            r4 = 4
            r5 = 1
            if (r1 == 0) goto L_0x005f
            int r1 = _isSmallType
            if (r1 != r5) goto L_0x005d
        L_0x005b:
            r1 = 3
            goto L_0x00bd
        L_0x005d:
            r1 = 0
            goto L_0x00bd
        L_0x005f:
            java.util.UUID r1 = r0.getUuid()
            java.lang.String r1 = r1.toString()
            java.util.UUID r6 = UUID_SERVICE2
            java.lang.String r6 = r6.toString()
            boolean r1 = r1.equalsIgnoreCase(r6)
            if (r1 == 0) goto L_0x0077
            _isSmallType = r5
            r1 = 1
            goto L_0x00bd
        L_0x0077:
            java.util.UUID r1 = r0.getUuid()
            java.lang.String r1 = r1.toString()
            java.util.UUID r6 = UUID_SERVICE3
            java.lang.String r6 = r6.toString()
            boolean r1 = r1.equalsIgnoreCase(r6)
            if (r1 == 0) goto L_0x008f
            _isSmallType = r5
            r1 = 2
            goto L_0x00bd
        L_0x008f:
            java.util.UUID r1 = r0.getUuid()
            java.lang.String r1 = r1.toString()
            java.util.UUID r6 = UUID_SERVICE4
            java.lang.String r6 = r6.toString()
            boolean r1 = r1.equalsIgnoreCase(r6)
            if (r1 == 0) goto L_0x00a6
            _isSmallType = r5
            goto L_0x005b
        L_0x00a6:
            java.util.UUID r1 = r0.getUuid()
            java.lang.String r1 = r1.toString()
            java.util.UUID r6 = UUID_SERVICE5
            java.lang.String r6 = r6.toString()
            boolean r1 = r1.equalsIgnoreCase(r6)
            if (r1 == 0) goto L_0x0015
            _isSmallType = r5
            r1 = 4
        L_0x00bd:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            org.cocos2dx.cpp.BluetoothLeService r7 = r11.bluetoothLeService
            r7.SetAppType(r1)
            java.lang.String r7 = TAG
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "type:"
            r8.append(r9)
            r8.append(r1)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r7, r8)
            java.util.UUID r7 = UUID_NOTIFY2
            java.lang.String r7 = r7.toString()
            if (r1 != 0) goto L_0x00ec
            java.util.UUID r2 = UUID_NOTIFY
        L_0x00e7:
            java.lang.String r7 = r2.toString()
            goto L_0x00fb
        L_0x00ec:
            if (r1 != r2) goto L_0x00f1
            java.util.UUID r2 = UUID_NOTIFY3
            goto L_0x00e7
        L_0x00f1:
            if (r1 != r3) goto L_0x00f6
            java.util.UUID r2 = UUID_NOTIFY4
            goto L_0x00e7
        L_0x00f6:
            if (r1 != r4) goto L_0x00fb
            java.util.UUID r2 = UUID_NOTIFY5
            goto L_0x00e7
        L_0x00fb:
            java.util.List r2 = r0.getCharacteristics()
            java.util.Iterator r2 = r2.iterator()
        L_0x0103:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0230
            java.lang.Object r3 = r2.next()
            android.bluetooth.BluetoothGattCharacteristic r3 = (android.bluetooth.BluetoothGattCharacteristic) r3
            org.cocos2dx.cpp.BluetoothLeService r8 = r11.bluetoothLeService
            int r9 = _isSmallType
            r8.setCharacteristicNotification(r3, r5, r9)
            java.util.UUID r8 = r3.getUuid()
            java.lang.String r8 = r8.toString()
            boolean r8 = r8.equalsIgnoreCase(r7)
            java.lang.String r9 = "写特征:"
            if (r8 == 0) goto L_0x0147
            r6.add(r3)
            java.lang.String r8 = TAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
        L_0x0130:
            r10.append(r9)
            java.util.UUID r9 = r3.getUuid()
            java.lang.String r9 = r9.toString()
            r10.append(r9)
            java.lang.String r9 = r10.toString()
        L_0x0142:
            android.util.Log.d(r8, r9)
            goto L_0x020d
        L_0x0147:
            java.util.UUID r8 = r3.getUuid()
            java.lang.String r8 = r8.toString()
            java.util.UUID r10 = UUID_NOTIFY6
            java.lang.String r10 = r10.toString()
            boolean r8 = r8.equalsIgnoreCase(r10)
            r10 = 5
            if (r8 == 0) goto L_0x016e
            if (r1 != r4) goto L_0x0163
            org.cocos2dx.cpp.BluetoothLeService r8 = r11.bluetoothLeService
            r8.SetAppType(r10)
        L_0x0163:
            r6.add(r3)
            java.lang.String r8 = TAG
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            goto L_0x0130
        L_0x016e:
            java.util.UUID r8 = r3.getUuid()
            java.lang.String r8 = r8.toString()
            java.util.UUID r9 = UUID_Read3
            java.lang.String r9 = r9.toString()
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x0187
            r6.add(r3)
            goto L_0x020d
        L_0x0187:
            java.util.UUID r8 = r3.getUuid()
            java.lang.String r8 = r8.toString()
            java.util.UUID r9 = UUID_Read4
            java.lang.String r9 = r9.toString()
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x01ba
            r6.add(r3)
            java.lang.String r8 = TAG
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "UUID_Read4:"
        L_0x01a7:
            r9.append(r10)
            java.util.UUID r10 = r3.getUuid()
            java.lang.String r10 = r10.toString()
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            goto L_0x0142
        L_0x01ba:
            java.util.UUID r8 = r3.getUuid()
            java.lang.String r8 = r8.toString()
            java.util.UUID r9 = UUID_Read5
            java.lang.String r9 = r9.toString()
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x01db
            r6.add(r3)
            java.lang.String r8 = TAG
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "UUID_Read5:"
            goto L_0x01a7
        L_0x01db:
            java.util.UUID r8 = r3.getUuid()
            java.lang.String r8 = r8.toString()
            java.util.UUID r9 = UUID_Read6
            java.lang.String r9 = r9.toString()
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x0203
            if (r1 != r4) goto L_0x01f6
            org.cocos2dx.cpp.BluetoothLeService r8 = r11.bluetoothLeService
            r8.SetAppType(r10)
        L_0x01f6:
            r6.add(r3)
            java.lang.String r8 = TAG
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "UUID_Read6:"
            goto L_0x01a7
        L_0x0203:
            java.lang.String r8 = TAG
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "UUID_NOTIFY="
            goto L_0x01a7
        L_0x020d:
            r6.add(r3)
            java.lang.String r8 = TAG
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "特征值:"
            r9.append(r10)
            java.util.UUID r3 = r3.getUuid()
            java.lang.String r3 = r3.toString()
            r9.append(r3)
            java.lang.String r3 = r9.toString()
            android.util.Log.d(r8, r3)
            goto L_0x0103
        L_0x0230:
            java.util.HashMap<android.bluetooth.BluetoothGattService, java.util.ArrayList<android.bluetooth.BluetoothGattCharacteristic>> r1 = r11.gattServiceData
            r1.put(r0, r6)
            goto L_0x0015
        L_0x0237:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.cpp.BluetoothHolder.searchGattServices(java.util.List):void");
    }

    public void bindService() {
        if (!this.bindService) {
            Log.d(TAG, "绑定服务");
            this.bindService = this.context.bindService(new Intent(this.context, BluetoothLeService.class), this.serviceConnection, 1);
            if (this.bindService) {
                Log.d(TAG, "绑定成功");
            } else {
                Log.e(TAG, "绑定失败");
            }
        }
    }

    public boolean connect(String str, String str2) {
        Log.e(TAG, "connect");
        if (this.bluetoothLeService != null) {
            this.deviceName = str;
            this.deviceAddress = str2;
            AppSaveData.getInstance().setDataForString("deviceAddress", this.deviceAddress);
            return this.bluetoothLeService.connect(this.deviceAddress);
        }
        Log.e(TAG, "bluetoothLeService == null");
        return false;
    }

    public void disconnect() {
        this.characteristics.clear();
        if (this.bluetoothLeService != null) {
            Log.d(TAG, "断开连接");
            this.bluetoothLeService.disconnect();
        }
    }

    public void enableTXNotification() {
        BluetoothLeService bluetoothLeService2;
        if (_isSmallType != 0 && (bluetoothLeService2 = this.bluetoothLeService) != null) {
            bluetoothLeService2.enableTXNotification();
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
            Log.e(TAG, "bluetoothAdapter == null");
            return true;
        }
        Log.d(TAG, "不支持蓝牙");
        return false;
    }

    public boolean isBindService() {
        return this.bindService;
    }

    public boolean isConnected() {
        BluetoothLeService bluetoothLeService2 = this.bluetoothLeService;
        return bluetoothLeService2 != null && bluetoothLeService2.getConnectionState() > 0;
    }

    public boolean isOpen() {
        BluetoothAdapter bluetoothAdapter2 = this.bluetoothAdapter;
        if (bluetoothAdapter2 != null) {
            return bluetoothAdapter2.isEnabled();
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
        Log.d(TAG, "打开蓝牙");
        return this.bluetoothAdapter.enable();
    }

    public boolean reConnect() {
        Log.d(TAG, "reConnect");
        if (this.bluetoothLeService != null) {
            this.deviceAddress = AppSaveData.getInstance().getDataForString("deviceAddress", this.deviceAddress);
            String str = TAG;
            Log.d(str, "reConnect:deviceAddress" + this.deviceAddress);
            return this.bluetoothLeService.connect(this.deviceAddress);
        }
        Log.e(TAG, "bluetoothLeService == null");
        return false;
    }

    public void readCharacteristic() {
        ArrayList next;
        HashMap<BluetoothGattService, ArrayList<BluetoothGattCharacteristic>> hashMap = this.gattServiceData;
        if (hashMap != null && !hashMap.isEmpty()) {
            Iterator<ArrayList<BluetoothGattCharacteristic>> it = this.gattServiceData.values().iterator();
            while (it.hasNext() && (next = it.next()) != null) {
                Iterator it2 = next.iterator();
                while (it2.hasNext()) {
                    BluetoothGattCharacteristic bluetoothGattCharacteristic = (BluetoothGattCharacteristic) it2.next();
                    int properties = bluetoothGattCharacteristic.getProperties();
                    if ((properties | 2) > 0) {
                        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = this.notifyCharacteristic;
                        if (bluetoothGattCharacteristic2 != null) {
                            this.bluetoothLeService.setCharacteristicNotification(bluetoothGattCharacteristic2, false, _isSmallType);
                            this.notifyCharacteristic = null;
                        }
                        this.bluetoothLeService.readCharacteristic(bluetoothGattCharacteristic);
                    }
                    if ((properties | 16) > 0) {
                        this.notifyCharacteristic = bluetoothGattCharacteristic;
                        this.bluetoothLeService.setCharacteristicNotification(this.notifyCharacteristic, true, _isSmallType);
                    }
                }
            }
        }
    }

    public void registerReceiver(BroadcastCallback broadcastCallback2) {
        Log.e(TAG, "已经注册广播接收者");
        this.broadcastCallback = broadcastCallback2;
        this.context.registerReceiver(this.broadcastReceiver, makeGattUpdateIntentFilter());
        if (this.bluetoothLeService != null && !TextUtils.isEmpty(this.deviceAddress)) {
            boolean connect = this.bluetoothLeService.connect(this.deviceAddress);
            String str = TAG;
            Log.d(str, "连接请求的结果：" + connect);
        }
    }

    public void scanDevice(boolean z, BluetoothAdapter.LeScanCallback leScanCallback) {
        if (this.bluetoothAdapter != null) {
            this.scanning = z;
            if (this.scanning) {
                Log.d(TAG, "开始扫描蓝牙");
                this.bluetoothAdapter.startLeScan((UUID[]) null, leScanCallback);
                return;
            }
            Log.d(TAG, "结束扫描蓝牙");
            this.bluetoothAdapter.stopLeScan(leScanCallback);
            return;
        }
        Log.d(TAG, "bluetoothAdapter == null");
    }

    public void unbindService(boolean z) {
        if (this.bindService) {
            Log.d(TAG, "解除绑定服务");
            this.context.unbindService(this.serviceConnection);
        }
        BluetoothLeService bluetoothLeService2 = this.bluetoothLeService;
        if (bluetoothLeService2 != null) {
            bluetoothLeService2.close();
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
        BluetoothLeService bluetoothLeService2 = this.bluetoothLeService;
        if (bluetoothLeService2 == null) {
            Log.e(TAG, "bluetoothLeService == null");
        } else if (_isSmallType == 1) {
            bluetoothLeService2.writeCharacteristic(bArr);
        } else {
            HashMap<BluetoothGattService, ArrayList<BluetoothGattCharacteristic>> hashMap = this.gattServiceData;
            if (hashMap != null && !hashMap.isEmpty()) {
                for (ArrayList<BluetoothGattCharacteristic> it : this.gattServiceData.values()) {
                    Iterator it2 = it.iterator();
                    while (it2.hasNext()) {
                        BluetoothGattCharacteristic bluetoothGattCharacteristic = (BluetoothGattCharacteristic) it2.next();
                        int properties = bluetoothGattCharacteristic.getProperties();
                        this.notifyCharacteristic = null;
                        if ((properties | 16) > 0) {
                            this.notifyCharacteristic = bluetoothGattCharacteristic;
                            BluetoothLeService bluetoothLeService3 = this.bluetoothLeService;
                            if (bluetoothLeService3 != null) {
                                bluetoothLeService3.setCharacteristicNotification(bluetoothGattCharacteristic, true, _isSmallType);
                            }
                        }
                        if ((properties | 8) > 0) {
                            bluetoothGattCharacteristic.setValue(bArr);
                            if (this.notifyCharacteristic != null) {
                                this.bluetoothLeService.writeCharacteristic(bluetoothGattCharacteristic);
                            }
                        }
                    }
                }
            }
        }
    }
}

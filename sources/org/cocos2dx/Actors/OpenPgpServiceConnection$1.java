package org.cocos2dx.Actors;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import org.cocos2dx.Actors.BaseService;

class OpenPgpServiceConnection$1 implements ServiceConnection {
    final /* synthetic */ BluetoothHolder this$0;

    OpenPgpServiceConnection$1(BluetoothHolder bluetoothHolder) {
        this.this$0 = bluetoothHolder;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.e(BluetoothHolder.LOGTAG, "管理服务生命周期on");
        BluetoothLeService unused = this.this$0.bluetoothLeService = (BluetoothLeService) ((BaseService.LocalBinder) iBinder).getService();
        if (!this.this$0.bluetoothLeService.initialize()) {
            Log.e(BluetoothHolder.LOGTAG, "无法初始化蓝牙");
        }
        if (!TextUtils.isEmpty(this.this$0.deviceAddress) && !TextUtils.isEmpty(this.this$0.deviceName)) {
            Log.e(BluetoothHolder.LOGTAG, "初始化成功启动自动连接到设备");
            BluetoothHolder $r4 = this.this$0;
            $r4.connect($r4.deviceName, this.this$0.deviceAddress);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Log.e(BluetoothHolder.LOGTAG, "管理服务生命周期off");
        BluetoothLeService unused = this.this$0.bluetoothLeService = null;
        boolean unused2 = this.this$0.connected = false;
    }
}

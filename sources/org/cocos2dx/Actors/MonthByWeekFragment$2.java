package org.cocos2dx.Actors;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import java.util.Iterator;

class MonthByWeekFragment$2 implements Runnable {
    final /* synthetic */ BluetoothDevice this$0;
    final /* synthetic */ UploadActivity$2 this$1;
    final /* synthetic */ byte[] val$userList;

    MonthByWeekFragment$2(UploadActivity$2 uploadActivity$2, BluetoothDevice bluetoothDevice, byte[] bArr) {
        this.this$1 = uploadActivity$2;
        this.this$0 = bluetoothDevice;
        this.val$userList = bArr;
    }

    public void run() {
        BluetoothDevice $r1 = this.this$0;
        if ($r1 != null && $r1.getName() != null) {
            MonthByWeekFragment$2 monthByWeekFragment$2 = this;
            if (this.this$0.getName().length() >= 2) {
                Iterator $r4 = AppActivity.devices.iterator();
                while ($r4.hasNext()) {
                    MonthByWeekFragment$2 monthByWeekFragment$22 = monthByWeekFragment$2;
                    monthByWeekFragment$2 = monthByWeekFragment$22;
                    if (((BluetoothDevice) $r4.next()).getName().equals(monthByWeekFragment$22.this$0.getName())) {
                        return;
                    }
                }
                AppActivity._msgList.add(monthByWeekFragment$2.val$userList);
                MonthByWeekFragment$2 monthByWeekFragment$23 = monthByWeekFragment$2;
                AppActivity.devices.add(monthByWeekFragment$23.this$0);
                StringBuilder $r8 = new StringBuilder();
                $r8.append(AppActivity._DevicName);
                MonthByWeekFragment$2 monthByWeekFragment$24 = monthByWeekFragment$23;
                MonthByWeekFragment$2 monthByWeekFragment$25 = monthByWeekFragment$24;
                $r8.append(monthByWeekFragment$24.this$0.getName());
                $r8.append("*");
                String unused = AppActivity._DevicName = $r8.toString();
                if (AppActivity._isAutoConnect) {
                    MonthByWeekFragment$2 monthByWeekFragment$26 = monthByWeekFragment$25;
                    monthByWeekFragment$25 = monthByWeekFragment$26;
                    if (AppActivity._defaultDeviceName.equals(monthByWeekFragment$26.this$0.getName())) {
                        boolean unused2 = AppActivity._isAutoConnect = false;
                        AppActivity.ReadMsg2(monthByWeekFragment$25.val$userList);
                        monthByWeekFragment$25.this$1.this$0.FinshScanBLE();
                        MonthByWeekFragment$2 monthByWeekFragment$27 = monthByWeekFragment$25;
                        MonthByWeekFragment$2 monthByWeekFragment$28 = monthByWeekFragment$27;
                        monthByWeekFragment$25 = monthByWeekFragment$28;
                        BluetoothHolder.getInstance().connect(monthByWeekFragment$27.this$0.getName(), monthByWeekFragment$28.this$0.getAddress());
                    }
                }
                StringBuilder $r82 = new StringBuilder();
                $r82.append("add device name:");
                MonthByWeekFragment$2 monthByWeekFragment$29 = monthByWeekFragment$25;
                MonthByWeekFragment$2 monthByWeekFragment$210 = monthByWeekFragment$29;
                $r82.append(monthByWeekFragment$29.this$0.getName());
                Log.d("AppActivity", $r82.toString());
            }
        }
    }
}

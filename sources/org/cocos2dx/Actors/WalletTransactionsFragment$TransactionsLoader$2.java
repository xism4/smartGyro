package org.cocos2dx.Actors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class WalletTransactionsFragment$TransactionsLoader$2 extends BroadcastReceiver {
    final /* synthetic */ BluetoothHolder this$0;

    WalletTransactionsFragment$TransactionsLoader$2(BluetoothHolder bluetoothHolder) {
        this.this$0 = bluetoothHolder;
    }

    public void onReceive(Context context, Intent intent) {
        String $r3 = intent.getAction();
        if (BluetoothService.ACTION_GATT_CONNECTED.equals($r3)) {
            if (this.this$0.broadcastCallback != null) {
                this.this$0.broadcastCallback.connected(intent);
                boolean unused = this.this$0.connected = true;
            }
        } else if (BluetoothService.ACTION_GATT_DISCONNECTED.equals($r3)) {
            boolean unused2 = this.this$0.connected = false;
            if (this.this$0.broadcastCallback != null) {
                this.this$0.broadcastCallback.disconnected(intent);
            }
        } else if (BluetoothService.ACTION_GATT_SERVICES_DISCOVERED.equals($r3)) {
            BluetoothHolder $r5 = this.this$0;
            $r5.searchGattServices($r5.bluetoothLeService.getSupportedGattServices());
            if (this.this$0.broadcastCallback != null) {
                this.this$0.broadcastCallback.discovered(intent);
            }
        } else if (!BluetoothService.ACTION_DATA_AVAILABLE.equals($r3)) {
            boolean unused3 = this.this$0.connected = false;
        } else if (intent != null && intent.getExtras() != null) {
            byte[] $r10 = intent.getExtras().getByteArray(BluetoothService.EXTRA_DATA);
            if (this.this$0.broadcastCallback == null || $r10 == null) {
                if (this.this$0.broadcastCallback == null) {
                    Log.d("onReceive data", "(broadcastCallback == null");
                }
                if ($r10 == null) {
                    Log.d("onReceive data", "(data == null");
                    return;
                }
                return;
            }
            this.this$0.broadcastCallback.data(intent, $r10);
        }
    }
}

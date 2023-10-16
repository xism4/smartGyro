package android.support.v7.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class ConnectionsManager$2 extends BroadcastReceiver {
    final /* synthetic */ ClassWriter b;

    ConnectionsManager$2(ClassWriter classWriter) {
        this.b = classWriter;
    }

    public void onReceive(Context context, Intent intent) {
        this.b.b();
    }
}

package com.org.android.manager;

import android.os.Handler;
import android.os.Message;

class SnackbarManager$1 implements Handler.Callback {
    final /* synthetic */ Connection this$0;

    SnackbarManager$1(Connection connection) {
        this.this$0 = connection;
    }

    public boolean handleMessage(Message message) {
        int $i0 = message.what;
        if ($i0 == 0) {
            this.this$0.close();
            return true;
        } else if ($i0 != 1) {
            return true;
        } else {
            this.this$0.close((Runnable) message.obj);
            return true;
        }
    }
}

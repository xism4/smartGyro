package com.org.android.manager;

import android.os.Handler;
import java.util.concurrent.Callable;

class Callables$3 implements Runnable {
    final /* synthetic */ c d;
    final /* synthetic */ Handler val$handler;
    final /* synthetic */ Connection val$nameSupplier;
    final /* synthetic */ Callable val$task;

    Callables$3(Connection connection, Callable callable, Handler handler, c cVar) {
        this.val$nameSupplier = connection;
        this.val$task = callable;
        this.val$handler = handler;
        this.d = cVar;
    }

    public void run() {
        Object $r3;
        try {
            $r3 = this.val$task.call();
        } catch (Exception e) {
            $r3 = null;
        }
        this.val$handler.post(new SplashScreen$5$1(this, $r3));
    }
}

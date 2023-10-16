package com.org.android.manager;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class PeerGroup$10 implements Runnable {
    final /* synthetic */ Condition condition;
    final /* synthetic */ AtomicBoolean keys;
    final /* synthetic */ ReentrantLock this$0;
    final /* synthetic */ Connection val$peer;
    final /* synthetic */ AtomicReference val$sync;
    final /* synthetic */ Callable val$task;

    PeerGroup$10(Connection connection, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition2) {
        this.val$peer = connection;
        this.val$sync = atomicReference;
        this.val$task = callable;
        this.this$0 = reentrantLock;
        this.keys = atomicBoolean;
        this.condition = condition2;
    }

    public void run() {
        try {
            this.val$sync.set(this.val$task.call());
        } catch (Exception e) {
        }
        this.this$0.lock();
        try {
            this.keys.set(false);
            this.condition.signal();
        } finally {
            this.this$0.unlock();
        }
    }
}

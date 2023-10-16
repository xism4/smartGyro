package org.cocos2dx.lib;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import javax.net.ssl.SSLException;
import p026b.p033c.p034a.p035a.C0465d;

public class Cocos2dxDownloader {
    /* access modifiers changed from: private */
    public static HashMap<String, Boolean> _resumingSupport = new HashMap<>();
    private int _countOfMaxProcessingTasks;
    /* access modifiers changed from: private */
    public C0465d _httpClient = new C0465d();
    /* access modifiers changed from: private */
    public int _id;
    private int _runningTaskCount = 0;
    /* access modifiers changed from: private */
    public HashMap _taskMap = new HashMap();
    private Queue<Runnable> _taskQueue = new LinkedList();
    /* access modifiers changed from: private */
    public String _tempFileNameSufix;

    public static void cancelAllRequests(Cocos2dxDownloader cocos2dxDownloader) {
        Cocos2dxHelper.getActivity().runOnUiThread(new C0988l(cocos2dxDownloader));
    }

    public static Cocos2dxDownloader createDownloader(int i, int i2, String str, int i3) {
        Cocos2dxDownloader cocos2dxDownloader = new Cocos2dxDownloader();
        cocos2dxDownloader._id = i;
        cocos2dxDownloader._httpClient.mo2361a(true);
        if (i2 > 0) {
            cocos2dxDownloader._httpClient.mo2366c(i2 * 1000);
        }
        C0465d dVar = cocos2dxDownloader._httpClient;
        C0465d.m2004a((Class<?>) SSLException.class);
        cocos2dxDownloader._tempFileNameSufix = str;
        cocos2dxDownloader._countOfMaxProcessingTasks = i3;
        return cocos2dxDownloader;
    }

    public static void createTask(Cocos2dxDownloader cocos2dxDownloader, int i, String str, String str2) {
        cocos2dxDownloader.enqueueTask(new C0986k(str2, cocos2dxDownloader, i, str));
    }

    public static void setResumingSupport(String str, Boolean bool) {
        _resumingSupport.put(str, bool);
    }

    public void enqueueTask(Runnable runnable) {
        synchronized (this._taskQueue) {
            if (this._runningTaskCount < this._countOfMaxProcessingTasks) {
                Cocos2dxHelper.getActivity().runOnUiThread(runnable);
                this._runningTaskCount++;
            } else {
                this._taskQueue.add(runnable);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public native void nativeOnFinish(int i, int i2, int i3, String str, byte[] bArr);

    /* access modifiers changed from: package-private */
    public native void nativeOnProgress(int i, int i2, long j, long j2, long j3);

    public void onFinish(int i, int i2, String str, byte[] bArr) {
        if (((C0949Ra) this._taskMap.get(Integer.valueOf(i))) != null) {
            this._taskMap.remove(Integer.valueOf(i));
            Cocos2dxHelper.runOnGLThread(new C0982i(this, i, i2, str, bArr));
        }
    }

    /* access modifiers changed from: package-private */
    public void onProgress(int i, long j, long j2, long j3) {
        C0949Ra ra = (C0949Ra) this._taskMap.get(Integer.valueOf(i));
        if (ra != null) {
            ra.f2576c = j;
            ra.f2577d = j2;
            ra.f2578e = j3;
        } else {
            long j4 = j;
            long j5 = j2;
            long j6 = j3;
        }
        Cocos2dxHelper.runOnGLThread(new C0980h(this, i, j, j2, j3));
    }

    public void onStart(int i) {
        C0949Ra ra = (C0949Ra) this._taskMap.get(Integer.valueOf(i));
        if (ra != null) {
            ra.mo3659a();
        }
    }

    public void runNextTaskIfExists() {
        synchronized (this._taskQueue) {
            Runnable poll = this._taskQueue.poll();
            if (poll != null) {
                Cocos2dxHelper.getActivity().runOnUiThread(poll);
            } else {
                this._runningTaskCount--;
            }
        }
    }
}

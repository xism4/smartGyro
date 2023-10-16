package org.cocos2dx.package_1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import javax.net.ssl.SSLException;
import lombok.eclipse.handlers.http.AsyncHttpClient;

public class Cocos2dxDownloader {
    /* access modifiers changed from: private */
    public static HashMap<String, Boolean> _resumingSupport = new HashMap();
    private int _countOfMaxProcessingTasks;
    /* access modifiers changed from: private */
    public AsyncHttpClient _httpClient = new AsyncHttpClient();
    private int _runningTaskCount = 0;
    /* access modifiers changed from: private */
    public HashMap _taskMap = new HashMap();
    private Queue<Runnable> _taskQueue = new LinkedList();
    /* access modifiers changed from: private */
    public String _tempFileNameSufix;
    /* access modifiers changed from: private */
    public int events;

    public static void cancelAllRequests(Cocos2dxDownloader cocos2dxDownloader) {
        Cocos2dxHelper.getActivity().runOnUiThread(new SpiceManager$7(cocos2dxDownloader));
    }

    public static Cocos2dxDownloader createDownloader(int i, int i2, String str, int i3) {
        Cocos2dxDownloader $r1 = new Cocos2dxDownloader();
        $r1.events = i;
        $r1._httpClient.setEnableRedirects(true);
        if (i2 > 0) {
            $r1._httpClient.setTimeout(i2 * 1000);
        }
        AsyncHttpClient $r2 = $r1._httpClient;
        AsyncHttpClient.allowRetryExceptionClass(SSLException.class);
        $r1._tempFileNameSufix = str;
        $r1._countOfMaxProcessingTasks = i3;
        return $r1;
    }

    public static void createTask(Cocos2dxDownloader cocos2dxDownloader, int i, String str, String str2) {
        cocos2dxDownloader.enqueueTask(new Channel(str2, cocos2dxDownloader, i, str));
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
        if (((Page) this._taskMap.get(Integer.valueOf(i))) != null) {
            this._taskMap.remove(Integer.valueOf(i));
            Cocos2dxHelper.runOnGLThread(new Download(this, i, i2, str, bArr));
        }
    }

    /* access modifiers changed from: package-private */
    public void onProgress(int i, long j, long j2, long j3) {
        Page $r5 = (Page) this._taskMap.get(Integer.valueOf(i));
        if ($r5 != null) {
            $r5.b = j;
            $r5.base = j2;
            $r5.index = j3;
        }
        Cocos2dxHelper.runOnGLThread(new IonRequestBuilder$2$1(this, i, j, j2, j3));
    }

    public void onStart(int i) {
        Page $r4 = (Page) this._taskMap.get(Integer.valueOf(i));
        if ($r4 != null) {
            $r4.reset();
        }
    }

    public void runNextTaskIfExists() {
        synchronized (this._taskQueue) {
            Runnable $r4 = this._taskQueue.poll();
            if ($r4 != null) {
                Cocos2dxHelper.getActivity().runOnUiThread($r4);
            } else {
                this._runningTaskCount--;
            }
        }
    }
}

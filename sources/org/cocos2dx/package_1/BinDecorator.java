package org.cocos2dx.package_1;

import android.util.Log;
import cz.msebera.android.http.Header;
import lombok.eclipse.handlers.http.BinaryHttpResponseHandler;

class BinDecorator extends BinaryHttpResponseHandler {
    int file;
    private long size = 0;
    private Cocos2dxDownloader this$0;

    public BinDecorator(Cocos2dxDownloader cocos2dxDownloader, int i) {
        super(new String[]{".*"});
        this.this$0 = cocos2dxDownloader;
        this.file = i;
    }

    public void close(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        write("onFailure(i:" + i + " headers:" + headerArr + " throwable:" + th);
        this.this$0.onFinish(this.file, i, th != null ? th.toString() : "", (byte[]) null);
    }

    public void read() {
        this.this$0.runNextTaskIfExists();
    }

    public void read(int i, Header[] headerArr, byte[] bArr) {
        write("onSuccess(i:" + i + " headers:" + headerArr);
        this.this$0.onFinish(this.file, 0, (String) null, bArr);
    }

    public void write() {
        this.this$0.onStart(this.file);
    }

    public void write(long j, long j2) {
        this.this$0.onProgress(this.file, j - this.size, j, j2);
        this.size = j;
    }

    /* access modifiers changed from: package-private */
    public void write(String str) {
        Log.d("Cocos2dxDownloader", str);
    }
}

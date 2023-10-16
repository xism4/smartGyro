package org.cocos2dx.package_1;

import android.util.Log;
import cz.msebera.android.http.Header;
import java.io.File;
import lombok.eclipse.handlers.http.FileAsyncHttpResponseHandler;

class ByteVector extends FileAsyncHttpResponseHandler {
    private Cocos2dxDownloader a;
    int b;
    private long data = 0;
    File f;
    private long size = getTargetFile().length();

    public ByteVector(Cocos2dxDownloader cocos2dxDownloader, int i, File file, File file2) {
        super(file, true);
        this.f = file2;
        this.a = cocos2dxDownloader;
        this.b = i;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    public void b(int i, Header[] headerArr, File file) {
        String $r4;
        StringBuilder $r3;
        String $r42;
        a("onSuccess(i:" + i + " headers:" + headerArr + " file:" + file);
        if (this.f.exists()) {
            if (this.f.isDirectory()) {
                $r3 = new StringBuilder();
                $r42 = "Dest file is directory:";
            } else if (!this.f.delete()) {
                $r3 = new StringBuilder();
                $r42 = "Can't remove old file:";
            }
            $r3.append($r42);
            $r3.append(this.f.getAbsolutePath());
            $r4 = $r3.toString();
            this.a.onFinish(this.b, 0, $r4, (byte[]) null);
        }
        getTargetFile().renameTo(this.f);
        $r4 = null;
        this.a.onFinish(this.b, 0, $r4, (byte[]) null);
    }

    public void read() {
        this.a.runNextTaskIfExists();
    }

    public void write() {
        this.a.onStart(this.b);
    }

    public void write(int i, Header[] headerArr, Throwable th, File file) {
        a("onFailure(i:" + i + " headers:" + headerArr + " throwable:" + th + " file:" + file);
        this.a.onFinish(this.b, i, th != null ? th.toString() : "", (byte[]) null);
    }

    public void write(long $l1, long $l12) {
        long $l5 = this.size;
        Cocos2dxDownloader $r1 = this.a;
        int $i2 = this.b;
        $r1.onProgress($i2, $l1 - this.data, $l1 + $l5, $l12 + $l5);
        this.data = $l1;
    }
}

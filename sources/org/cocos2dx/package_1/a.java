package org.cocos2dx.package_1;

import android.util.Log;
import cz.msebera.android.http.Header;
import lombok.eclipse.handlers.http.AsyncHttpResponseHandler;

class a extends AsyncHttpResponseHandler {
    String a;
    String b;
    int c;
    String i;
    private Cocos2dxDownloader s;

    public a(Cocos2dxDownloader cocos2dxDownloader, int i2, String str, String str2, String str3) {
        this.s = cocos2dxDownloader;
        this.c = i2;
        this.i = str;
        this.a = str2;
        this.b = str3;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    public void close(int i2, Header[] headerArr, byte[] bArr, Throwable th) {
        a("onFailure(code:" + i2 + " headers:" + headerArr + " throwable:" + th + " id:" + this.c);
        this.s.onFinish(this.c, i2, th != null ? th.toString() : "", (byte[]) null);
    }

    public void read() {
        this.s.runNextTaskIfExists();
    }

    public void read(int i2, Header[] headerArr, byte[] bArr) {
        int $i0 = 0;
        boolean $r3 = false;
        while (true) {
            if ($i0 >= headerArr.length) {
                break;
            }
            Header $r4 = headerArr[$i0];
            if ($r4.getName().equals("Accept-Ranges")) {
                $r3 = Boolean.valueOf($r4.getValue().equals("bytes"));
                break;
            }
            $i0++;
        }
        Cocos2dxDownloader.setResumingSupport(this.i, $r3);
        Cocos2dxDownloader.createTask(this.s, this.c, this.a, this.b);
    }
}

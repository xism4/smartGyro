package org.cocos2dx.package_1;

import lombok.eclipse.handlers.http.AsyncHttpResponseHandler;
import lombok.eclipse.handlers.http.RequestHandle;

class Page {
    long b;
    long base;
    long index;
    AsyncHttpResponseHandler name = null;
    RequestHandle status = null;
    byte[] type;

    Page() {
        reset();
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.b = 0;
        this.base = 0;
        this.index = 0;
        this.type = null;
    }
}

package com.org.android.manager;

import android.net.Uri;
import com.org.android.util.m;

public class Label {
    private final Uri a;
    private final int b;
    private final boolean e;
    private final int f;
    private final int g;

    public Label(Uri $r1, int i, int i2, boolean z, int i3) {
        m.a($r1);
        this.a = $r1;
        this.f = i;
        this.g = i2;
        this.e = z;
        this.b = i3;
    }

    public boolean a() {
        return this.e;
    }

    public int b() {
        return this.g;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.f;
    }

    public Uri getValue() {
        return this.a;
    }
}

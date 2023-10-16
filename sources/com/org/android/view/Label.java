package com.org.android.view;

import a.a.c.g.D;
import android.os.Build;
import android.view.WindowInsets;

public class Label {
    private final Object b;

    private Label(Object obj) {
        this.b = obj;
    }

    static Label a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new Label(obj);
    }

    static Object b(Label label) {
        if (label == null) {
            return null;
        }
        return label.b;
    }

    public int a() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.b).getSystemWindowInsetBottom();
        }
        return 0;
    }

    public Label a(int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 20) {
            return new Label(((WindowInsets) this.b).replaceSystemWindowInsets(i, i2, i3, i4));
        }
        return null;
    }

    public int b() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.b).getSystemWindowInsetLeft();
        }
        return 0;
    }

    public int c() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.b).getSystemWindowInsetRight();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || D.class != obj.getClass()) {
            return false;
        }
        Label $r3 = (Label) obj;
        Object $r1 = this.b;
        return $r1 == null ? $r3.b == null : $r1.equals($r3.b);
    }

    public int getColor() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.b).getSystemWindowInsetTop();
        }
        return 0;
    }

    public int hashCode() {
        Object $r1 = this.b;
        if ($r1 == null) {
            return 0;
        }
        return $r1.hashCode();
    }
}

package com.org.android.core;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.style.MetricAffectingSpan;
import java.util.concurrent.Executor;

public class Segment implements Spannable {
    private static final Object owner = new Object();
    private static Executor shared = null;
    private final Label b;
    private final Spannable editable;
    private final PrecomputedText mText;

    public PrecomputedText append() {
        Spannable $r1 = this.editable;
        if ($r1 instanceof PrecomputedText) {
            return (PrecomputedText) $r1;
        }
        return null;
    }

    public char charAt(int i) {
        return this.editable.charAt(i);
    }

    public int getSpanEnd(Object obj) {
        return this.editable.getSpanEnd(obj);
    }

    public int getSpanFlags(Object obj) {
        return this.editable.getSpanFlags(obj);
    }

    public int getSpanStart(Object obj) {
        return this.editable.getSpanStart(obj);
    }

    public Object[] getSpans(int i, int i2, Class cls) {
        return Build.VERSION.SDK_INT >= 28 ? this.mText.getSpans(i, i2, cls) : this.editable.getSpans(i, i2, cls);
    }

    public Label getValue() {
        return this.b;
    }

    public int length() {
        return this.editable.length();
    }

    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.editable.nextSpanTransition(i, i2, cls);
    }

    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.mText.removeSpan(obj);
        } else {
            this.editable.removeSpan(obj);
        }
    }

    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.mText.setSpan(obj, i, i2, i3);
        } else {
            this.editable.setSpan(obj, i, i2, i3);
        }
    }

    public CharSequence subSequence(int i, int i2) {
        return this.editable.subSequence(i, i2);
    }

    public String toString() {
        return this.editable.toString();
    }
}

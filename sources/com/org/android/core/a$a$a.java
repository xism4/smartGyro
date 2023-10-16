package com.org.android.core;

import android.os.Build;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;

public class a$a$a {
    private final TextPaint a;
    private TextDirectionHeuristic b;
    private int c;
    private int f;

    public a$a$a(TextPaint textPaint) {
        this.a = textPaint;
        if (Build.VERSION.SDK_INT >= 23) {
            this.c = 1;
            this.f = 1;
        } else {
            this.f = 0;
            this.c = 0;
        }
        this.b = Build.VERSION.SDK_INT >= 18 ? TextDirectionHeuristics.FIRSTSTRONG_LTR : null;
    }

    public Label a() {
        return new Label(this.a, this.b, this.c, this.f);
    }

    public a$a$a a(int i) {
        this.f = i;
        return this;
    }

    public a$a$a b(int i) {
        this.c = i;
        return this;
    }

    public a$a$a b(TextDirectionHeuristic textDirectionHeuristic) {
        this.b = textDirectionHeuristic;
        return this;
    }
}

package com.org.android.view;

import android.view.View;
import android.view.WindowInsets;

final class ViewCompatLollipop$1 implements View.OnApplyWindowInsetsListener {
    final /* synthetic */ OnApplyWindowInsetsListener val$listener;

    ViewCompatLollipop$1(OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        this.val$listener = onApplyWindowInsetsListener;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        return (WindowInsets) Label.b(this.val$listener.a(view, Label.a(windowInsets)));
    }
}

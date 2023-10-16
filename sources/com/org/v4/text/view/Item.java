package com.org.v4.text.view;

import android.content.res.ColorStateList;
import android.content.res.Configuration;

class Item {
    final ColorStateList c;
    final Configuration d;

    Item(ColorStateList colorStateList, Configuration configuration) {
        this.c = colorStateList;
        this.d = configuration;
    }
}

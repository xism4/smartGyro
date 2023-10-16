package android.support.v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public abstract class AppCompatDelegate {
    private static int sDefaultNightMode;

    AppCompatDelegate() {
    }

    public static AppCompatDelegate create(Dialog dialog, AppCompatCallback appCompatCallback) {
        return new AppCompatDelegateImplV7(dialog.getContext(), dialog.getWindow(), appCompatCallback);
    }

    public static int getDefaultNightMode() {
        return sDefaultNightMode;
    }

    public abstract boolean a();

    public abstract void addContentView(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void c();

    public abstract View findViewById(int i);

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    public abstract void onCreate(Bundle bundle);

    public abstract void onTitleChanged(CharSequence charSequence);

    public abstract boolean requestWindowFeature(int i);

    public abstract void setContentView(int i);

    public abstract void setContentView(View view);

    public abstract void setContentView(View view, ViewGroup.LayoutParams layoutParams);
}

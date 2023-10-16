package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.org.android.view.i;
import com.org.android.view.x;
import com.org.v4.util.R$attr;
import com.org.v4.view.ActionMode;

public class AppCompatDialog extends Dialog implements AppCompatCallback {
    private final x b = new d(this);
    private AppCompatDelegate mDelegate;

    public AppCompatDialog(Context context, int i) {
        super(context, getThemeResId(context, i));
        getDelegate().onCreate((Bundle) null);
        getDelegate().a();
    }

    private static int getThemeResId(Context context, int $i0) {
        if ($i0 != 0) {
            return $i0;
        }
        TypedValue $r2 = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.dialogTheme, $r2, true);
        return $r2.resourceId;
    }

    /* access modifiers changed from: package-private */
    public boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().addContentView(view, layoutParams);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return i.a(this.b, getWindow().getDecorView(), this, keyEvent);
    }

    public View findViewById(int i) {
        return getDelegate().findViewById(i);
    }

    public AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create(this, this);
        }
        return this.mDelegate;
    }

    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        getDelegate().installViewFactory();
        super.onCreate(bundle);
        getDelegate().onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        getDelegate().c();
    }

    public void onSupportActionModeFinished(ActionMode actionMode) {
    }

    public void onSupportActionModeStarted(ActionMode actionMode) {
    }

    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

    public void setContentView(int i) {
        getDelegate().setContentView(i);
    }

    public void setContentView(View view) {
        getDelegate().setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().setContentView(view, layoutParams);
    }

    public void setTitle(int i) {
        super.setTitle(i);
        getDelegate().onTitleChanged(getContext().getString(i));
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        getDelegate().onTitleChanged(charSequence);
    }

    public boolean supportRequestWindowFeature(int i) {
        return getDelegate().requestWindowFeature(i);
    }
}

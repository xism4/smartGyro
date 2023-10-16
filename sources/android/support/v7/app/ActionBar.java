package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.org.v4.util.R$styleable;
import com.org.v4.view.ActionMode;

public abstract class ActionBar {

    public class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = 0;
            this.gravity = 8388627;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = 0;
            TypedArray $r4 = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBarLayout);
            this.gravity = $r4.getInt(R$styleable.ActionBarLayout_android_layout_gravity, 0);
            $r4.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
        }
    }

    public interface OnMenuVisibilityListener {
        void onMenuVisibilityChanged(boolean z);
    }

    @Deprecated
    public abstract class Tab {
        public abstract CharSequence getContentDescription();

        public abstract View getCustomView();

        public abstract Drawable getIcon();

        public abstract CharSequence getText();

        public abstract void select();
    }

    public abstract boolean collapseActionView();

    public abstract void dispatchMenuVisibilityChanged(boolean z);

    public abstract Context getThemedContext();

    public boolean invalidateOptionsMenu() {
        return false;
    }

    public abstract boolean onKeyShortcut(int i, KeyEvent keyEvent);

    public abstract void setDefaultDisplayHomeAsUpEnabled(boolean z);

    public abstract void setShowHideAnimationEnabled(boolean z);

    public abstract void setWindowTitle(CharSequence charSequence);

    public abstract ActionMode startActionMode(ActionMode.Callback callback);
}

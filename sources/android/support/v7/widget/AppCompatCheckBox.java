package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TintableCompoundButton;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.org.v4.text.view.Resources;
import com.org.v4.util.R$attr;

public class AppCompatCheckBox extends CheckBox implements TintableCompoundButton {
    private final AppCompatCompoundButtonHelper mCompoundButtonHelper;

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        this.mCompoundButtonHelper = new AppCompatCompoundButtonHelper(this);
        this.mCompoundButtonHelper.loadFromAttributes(attributeSet, i);
    }

    public int getCompoundPaddingLeft() {
        int $i0 = super.getCompoundPaddingLeft();
        AppCompatCompoundButtonHelper $r1 = this.mCompoundButtonHelper;
        return $r1 != null ? $r1.getCompoundPaddingLeft($i0) : $i0;
    }

    public ColorStateList getSupportButtonTintList() {
        AppCompatCompoundButtonHelper $r2 = this.mCompoundButtonHelper;
        if ($r2 != null) {
            return $r2.getSupportButtonTintList();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        AppCompatCompoundButtonHelper $r2 = this.mCompoundButtonHelper;
        if ($r2 != null) {
            return $r2.getSupportButtonTintMode();
        }
        return null;
    }

    public void setButtonDrawable(int i) {
        setButtonDrawable(Resources.getDrawable(getContext(), i));
    }

    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        AppCompatCompoundButtonHelper $r2 = this.mCompoundButtonHelper;
        if ($r2 != null) {
            $r2.onSetButtonDrawable();
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        AppCompatCompoundButtonHelper $r2 = this.mCompoundButtonHelper;
        if ($r2 != null) {
            $r2.setSupportButtonTintList(colorStateList);
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        AppCompatCompoundButtonHelper $r2 = this.mCompoundButtonHelper;
        if ($r2 != null) {
            $r2.setSupportButtonTintMode(mode);
        }
    }
}

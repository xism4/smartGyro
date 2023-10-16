package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.widget.TextView;
import android.util.AttributeSet;
import android.widget.ImageButton;
import com.org.android.view.TintableBackgroundView;
import com.org.v4.util.R$attr;

public class AppCompatImageButton extends ImageButton implements TintableBackgroundView, TextView {
    private final MethodWriter D;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;

    public AppCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.imageButtonStyle);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper.loadFromAttributes(attributeSet, i);
        this.D = new MethodWriter(this);
        this.D.loadFromAttributes(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper $r1 = this.mBackgroundTintHelper;
        if ($r1 != null) {
            $r1.applySupportBackgroundTint();
        }
        MethodWriter $r2 = this.D;
        if ($r2 != null) {
            $r2.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            return $r2.getSupportBackgroundTintList();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            return $r2.getSupportBackgroundTintMode();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        MethodWriter $r2 = this.D;
        if ($r2 != null) {
            return $r2.getSupportBackgroundTintList();
        }
        return null;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        MethodWriter $r2 = this.D;
        if ($r2 != null) {
            return $r2.getSupportBackgroundTintMode();
        }
        return null;
    }

    public boolean hasOverlappingRendering() {
        return this.D.getSize() && super.hasOverlappingRendering();
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            $r2.onSetBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        AppCompatBackgroundHelper $r1 = this.mBackgroundTintHelper;
        if ($r1 != null) {
            $r1.onSetBackgroundResource(i);
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        MethodWriter $r2 = this.D;
        if ($r2 != null) {
            $r2.a();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        MethodWriter $r2 = this.D;
        if ($r2 != null) {
            $r2.a();
        }
    }

    public void setImageResource(int i) {
        this.D.a(i);
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        MethodWriter $r2 = this.D;
        if ($r2 != null) {
            $r2.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            $r2.setSupportBackgroundTintList(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            $r2.setSupportBackgroundTintMode(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        MethodWriter $r2 = this.D;
        if ($r2 != null) {
            $r2.a(colorStateList);
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        MethodWriter $r2 = this.D;
        if ($r2 != null) {
            $r2.a(mode);
        }
    }
}

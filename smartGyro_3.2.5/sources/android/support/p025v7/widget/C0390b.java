package android.support.p025v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

/* renamed from: android.support.v7.widget.b */
class C0390b extends Drawable {

    /* renamed from: a */
    final ActionBarContainer f1509a;

    public C0390b(ActionBarContainer actionBarContainer) {
        this.f1509a = actionBarContainer;
    }

    public void draw(Canvas canvas) {
        ActionBarContainer actionBarContainer = this.f1509a;
        if (actionBarContainer.f1109h) {
            Drawable drawable = actionBarContainer.f1108g;
            if (drawable != null) {
                drawable.draw(canvas);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.f1106e;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        ActionBarContainer actionBarContainer2 = this.f1509a;
        Drawable drawable3 = actionBarContainer2.f1107f;
        if (drawable3 != null && actionBarContainer2.f1110i) {
            drawable3.draw(canvas);
        }
    }

    public int getOpacity() {
        return 0;
    }

    public void getOutline(Outline outline) {
        Drawable drawable;
        ActionBarContainer actionBarContainer = this.f1509a;
        if (actionBarContainer.f1109h) {
            drawable = actionBarContainer.f1108g;
            if (drawable == null) {
                return;
            }
        } else {
            drawable = actionBarContainer.f1106e;
            if (drawable == null) {
                return;
            }
        }
        drawable.getOutline(outline);
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}

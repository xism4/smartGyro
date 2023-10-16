package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

class CircularBorderDrawable extends Drawable {
    final ActionBarContainer mContainer;

    public CircularBorderDrawable(ActionBarContainer actionBarContainer) {
        this.mContainer = actionBarContainer;
    }

    public void draw(Canvas canvas) {
        ActionBarContainer $r2 = this.mContainer;
        if ($r2.mIsSplit) {
            Drawable $r3 = $r2.mSplitBackground;
            if ($r3 != null) {
                $r3.draw(canvas);
                return;
            }
            return;
        }
        Drawable $r32 = $r2.mBackground;
        if ($r32 != null) {
            $r32.draw(canvas);
        }
        ActionBarContainer $r22 = this.mContainer;
        Drawable $r33 = $r22.mStackedBackground;
        if ($r33 != null && $r22.mIsStacked) {
            $r33.draw(canvas);
        }
    }

    public int getOpacity() {
        return 0;
    }

    public void getOutline(Outline outline) {
        Drawable $r3;
        ActionBarContainer $r2 = this.mContainer;
        if ($r2.mIsSplit) {
            $r3 = $r2.mSplitBackground;
            if ($r3 == null) {
                return;
            }
        } else {
            $r3 = $r2.mBackground;
            if ($r3 == null) {
                return;
            }
        }
        $r3.getOutline(outline);
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}

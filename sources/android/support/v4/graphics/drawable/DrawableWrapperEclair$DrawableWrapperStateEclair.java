package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableWrapperDonut;

class DrawableWrapperEclair$DrawableWrapperStateEclair extends DrawableWrapperDonut.DrawableWrapperState {
    DrawableWrapperEclair$DrawableWrapperStateEclair(DrawableWrapperDonut.DrawableWrapperState drawableWrapperState, Resources resources) {
        super(drawableWrapperState, resources);
    }

    public Drawable newDrawable(Resources resources) {
        return new DrawableWrapperDonut(this, resources);
    }
}

package android.support.v4.graphics.drawable;

public interface Drawable {
    android.graphics.drawable.Drawable getWrappedDrawable();

    void setWrappedDrawable(android.graphics.drawable.Drawable drawable);
}

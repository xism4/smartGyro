package android.support.v4.graphics.drawable;

public abstract interface Drawable
{
  public abstract android.graphics.drawable.Drawable getWrappedDrawable();
  
  public abstract void setWrappedDrawable(android.graphics.drawable.Drawable paramDrawable);
}

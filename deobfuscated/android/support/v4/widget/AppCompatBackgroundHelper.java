package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.ImageView;

public class AppCompatBackgroundHelper
{
  public static ColorStateList b(ImageView paramImageView)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramImageView.getImageTintList();
    }
    if ((paramImageView instanceof TextView)) {
      return ((TextView)paramImageView).getSupportImageTintList();
    }
    return null;
  }
  
  public static PorterDuff.Mode createView(ImageView paramImageView)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramImageView.getImageTintMode();
    }
    if ((paramImageView instanceof TextView)) {
      return ((TextView)paramImageView).getSupportImageTintMode();
    }
    return null;
  }
  
  public static void loadFromAttributes(ImageView paramImageView, ColorStateList paramColorStateList)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramImageView.setImageTintList(paramColorStateList);
      if (Build.VERSION.SDK_INT == 21)
      {
        paramColorStateList = paramImageView.getDrawable();
        int i;
        if ((paramImageView.getImageTintList() != null) && (paramImageView.getImageTintMode() != null)) {
          i = 1;
        } else {
          i = 0;
        }
        if ((paramColorStateList != null) && (i != 0))
        {
          if (paramColorStateList.isStateful()) {
            paramColorStateList.setState(paramImageView.getDrawableState());
          }
          paramImageView.setImageDrawable(paramColorStateList);
        }
      }
    }
    else if ((paramImageView instanceof TextView))
    {
      ((TextView)paramImageView).setSupportImageTintList(paramColorStateList);
    }
  }
  
  public static void loadFromAttributes(ImageView paramImageView, PorterDuff.Mode paramMode)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramImageView.setImageTintMode(paramMode);
      if (Build.VERSION.SDK_INT == 21)
      {
        paramMode = paramImageView.getDrawable();
        int i;
        if ((paramImageView.getImageTintList() != null) && (paramImageView.getImageTintMode() != null)) {
          i = 1;
        } else {
          i = 0;
        }
        if ((paramMode != null) && (i != 0))
        {
          if (paramMode.isStateful()) {
            paramMode.setState(paramImageView.getDrawableState());
          }
          paramImageView.setImageDrawable(paramMode);
        }
      }
    }
    else if ((paramImageView instanceof TextView))
    {
      ((TextView)paramImageView).setSupportImageTintMode(paramMode);
    }
  }
}

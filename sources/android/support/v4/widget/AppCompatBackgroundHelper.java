package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

public class AppCompatBackgroundHelper {
    public static ColorStateList b(ImageView imageView) {
        if (Build.VERSION.SDK_INT >= 21) {
            return imageView.getImageTintList();
        }
        if (imageView instanceof TextView) {
            return ((TextView) imageView).getSupportImageTintList();
        }
        return null;
    }

    public static PorterDuff.Mode createView(ImageView imageView) {
        if (Build.VERSION.SDK_INT >= 21) {
            return imageView.getImageTintMode();
        }
        if (imageView instanceof TextView) {
            return ((TextView) imageView).getSupportImageTintMode();
        }
        return null;
    }

    public static void loadFromAttributes(ImageView imageView, ColorStateList $r1) {
        if (Build.VERSION.SDK_INT >= 21) {
            imageView.setImageTintList($r1);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable $r2 = imageView.getDrawable();
                boolean $z0 = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
                if ($r2 != null && $z0) {
                    if ($r2.isStateful()) {
                        $r2.setState(imageView.getDrawableState());
                    }
                    imageView.setImageDrawable($r2);
                }
            }
        } else if (imageView instanceof TextView) {
            ((TextView) imageView).setSupportImageTintList($r1);
        }
    }

    public static void loadFromAttributes(ImageView imageView, PorterDuff.Mode $r1) {
        if (Build.VERSION.SDK_INT >= 21) {
            imageView.setImageTintMode($r1);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable $r2 = imageView.getDrawable();
                boolean $z0 = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
                if ($r2 != null && $z0) {
                    if ($r2.isStateful()) {
                        $r2.setState(imageView.getDrawableState());
                    }
                    imageView.setImageDrawable($r2);
                }
            }
        } else if (imageView instanceof TextView) {
            ((TextView) imageView).setSupportImageTintMode($r1);
        }
    }
}

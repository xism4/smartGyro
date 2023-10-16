package android.support.v4.graphics.drawable;

import androidx.versionedparcelable.f;

public final class IconCompatParcelizer
  extends androidx.core.graphics.drawable.IconCompatParcelizer
{
  public IconCompatParcelizer() {}
  
  public static IconCompat read(f paramF)
  {
    return androidx.core.graphics.drawable.IconCompatParcelizer.read(paramF);
  }
  
  public static void write(IconCompat paramIconCompat, f paramF)
  {
    androidx.core.graphics.drawable.IconCompatParcelizer.write(paramIconCompat, paramF);
  }
}

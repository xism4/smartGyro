package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.f;

public class IconCompatParcelizer
{
  public IconCompatParcelizer() {}
  
  public static IconCompat read(f paramF)
  {
    IconCompat localIconCompat = new IconCompat();
    c = paramF.add(c, 1);
    s = paramF.a(s, 2);
    b = paramF.add(b, 3);
    r = paramF.add(r, 4);
    p = paramF.add(p, 5);
    i = ((ColorStateList)paramF.add(i, 6));
    x = paramF.a(x, 7);
    localIconCompat.init();
    return localIconCompat;
  }
  
  public static void write(IconCompat paramIconCompat, f paramF)
  {
    paramF.a(true, true);
    paramIconCompat.encode(paramF.processBytes());
    paramF.a(c, 1);
    paramF.clear(s, 2);
    paramF.a(b, 3);
    paramF.a(r, 4);
    paramF.a(p, 5);
    paramF.a(i, 6);
    paramF.b(x, 7);
  }
}

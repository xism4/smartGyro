package com.org.android.manager;

import a.a.c.d.f.c;
import android.content.Context;
import android.graphics.Typeface;
import com.org.android.util.LruCache;
import java.util.concurrent.Callable;

final class a
  implements Callable<f.c>
{
  a(Context paramContext, h paramH, int paramInt, String paramString) {}
  
  public f call()
  {
    f localF = i.a(i, e, p);
    Typeface localTypeface = t;
    if (localTypeface != null) {
      i.t.put(t, localTypeface);
    }
    return localF;
  }
}

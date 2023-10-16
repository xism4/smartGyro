package com.org.android.manager;

import a.a.c.d.f.c;
import a.a.c.d.k.a;
import android.os.Handler;
import com.org.android.ui.asm.k;

final class d
  implements k.a<f.c>
{
  d(k paramK, Handler paramHandler) {}
  
  public void a(f paramF)
  {
    int i;
    if (paramF == null)
    {
      paramF = b;
      i = 1;
    }
    for (;;)
    {
      paramF.a(i, f);
      return;
      i = f;
      if (i == 0)
      {
        b.a(t, f);
        return;
      }
      paramF = b;
    }
  }
}

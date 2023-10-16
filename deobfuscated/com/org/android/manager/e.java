package com.org.android.manager;

import a.a.c.d.f.c;
import a.a.c.d.k.a;
import com.org.android.util.SimpleArrayMap;
import java.util.ArrayList;

final class e
  implements k.a<f.c>
{
  e(String paramString) {}
  
  public void a(f paramF)
  {
    Object localObject = i.c;
    try
    {
      ArrayList localArrayList = (ArrayList)i.b.get(a);
      if (localArrayList == null) {
        return;
      }
      i.b.remove(a);
      int i = 0;
      while (i < localArrayList.size())
      {
        ((c)localArrayList.get(i)).a(paramF);
        i += 1;
      }
      return;
    }
    catch (Throwable paramF)
    {
      throw paramF;
    }
  }
}

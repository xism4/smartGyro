package android.support.v7.view.menu;

import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import java.util.List;

class c
  implements android.support.v7.widget.Object
{
  c(f paramF) {}
  
  public void a(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    Handler localHandler = c.B;
    e localE = null;
    localHandler.removeCallbacksAndMessages(null);
    int j = c.c.size();
    int i = 0;
    while (i < j)
    {
      if (paramMenuBuilder == c.c.get(i)).c) {
        break label75;
      }
      i += 1;
    }
    i = -1;
    label75:
    if (i == -1) {
      return;
    }
    i += 1;
    if (i < c.c.size()) {
      localE = (e)c.c.get(i);
    }
    paramMenuItem = new Plot.a(this, localE, paramMenuItem, paramMenuBuilder);
    long l = SystemClock.uptimeMillis();
    c.B.postAtTime(paramMenuItem, paramMenuBuilder, l + 200L);
  }
  
  public void b(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    c.B.removeCallbacksAndMessages(paramMenuBuilder);
  }
}

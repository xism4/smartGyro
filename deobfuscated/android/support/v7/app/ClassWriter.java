package android.support.v7.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

final class ClassWriter
{
  private IntentFilter b;
  private TwilightManager f;
  private boolean h;
  private BroadcastReceiver x;
  
  ClassWriter(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7, TwilightManager paramTwilightManager)
  {
    f = paramTwilightManager;
    h = paramTwilightManager.isNight();
  }
  
  int a()
  {
    h = f.isNight();
    if (h) {
      return 2;
    }
    return 1;
  }
  
  void b()
  {
    boolean bool = f.isNight();
    if (bool != h)
    {
      h = bool;
      a.a();
    }
  }
  
  void c()
  {
    BroadcastReceiver localBroadcastReceiver = x;
    if (localBroadcastReceiver != null)
    {
      a.mContext.unregisterReceiver(localBroadcastReceiver);
      x = null;
    }
  }
  
  void init()
  {
    c();
    if (x == null) {
      x = new ConnectionsManager.2(this);
    }
    if (b == null)
    {
      b = new IntentFilter();
      b.addAction("android.intent.action.TIME_SET");
      b.addAction("android.intent.action.TIMEZONE_CHANGED");
      b.addAction("android.intent.action.TIME_TICK");
    }
    a.mContext.registerReceiver(x, b);
  }
}

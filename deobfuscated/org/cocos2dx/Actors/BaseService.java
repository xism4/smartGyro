package org.cocos2dx.Actors;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BaseService
  extends Service
{
  private final IBinder mBinder = new LocalBinder();
  
  public BaseService() {}
  
  public IBinder onBind(Intent paramIntent)
  {
    return mBinder;
  }
  
  public class LocalBinder
    extends Binder
  {
    public LocalBinder() {}
    
    public BaseService getService()
    {
      return BaseService.this;
    }
  }
}

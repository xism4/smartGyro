package org.cocos2dx.Actors;

import android.content.Intent;

public abstract interface BroadcastCallback
{
  public abstract void connected(Intent paramIntent);
  
  public abstract void data(Intent paramIntent, byte[] paramArrayOfByte);
  
  public abstract void disconnected(Intent paramIntent);
  
  public abstract void discovered(Intent paramIntent);
}

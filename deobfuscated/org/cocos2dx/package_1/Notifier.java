package org.cocos2dx.package_1;

class Notifier
  implements Runnable
{
  Notifier(CopyOnWriteArrayList paramCopyOnWriteArrayList) {}
  
  public void run()
  {
    Cocos2dxAudioFocusManager.a(3);
    Cocos2dxHelper.setAudioFocus(false);
  }
}

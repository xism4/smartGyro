package org.cocos2dx.package_1;

class Continuation
  implements Runnable
{
  Continuation(CopyOnWriteArrayList paramCopyOnWriteArrayList) {}
  
  public void run()
  {
    Cocos2dxAudioFocusManager.a(0);
    Cocos2dxHelper.setAudioFocus(true);
  }
}

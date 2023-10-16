package org.cocos2dx.package_1;

final class Roster
  implements Runnable
{
  Roster() {}
  
  public void run()
  {
    Cocos2dxHelper.setAudioFocus(true);
    Cocos2dxAudioFocusManager.a(0);
  }
}

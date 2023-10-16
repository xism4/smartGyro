package org.cocos2dx.package_1;

class SelectAction
  implements Runnable
{
  SelectAction(Channel paramChannel, String paramString) {}
  
  public void run()
  {
    Cocos2dxDownloader localCocos2dxDownloader = c.this$0;
    localCocos2dxDownloader.nativeOnFinish(Cocos2dxDownloader.access$000(localCocos2dxDownloader), c.d, 0, t, null);
  }
}

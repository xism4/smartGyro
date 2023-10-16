package org.cocos2dx.package_1;

class Download
  implements Runnable
{
  Download(Cocos2dxDownloader paramCocos2dxDownloader, int paramInt1, int paramInt2, String paramString, byte[] paramArrayOfByte) {}
  
  public void run()
  {
    Cocos2dxDownloader localCocos2dxDownloader = path;
    localCocos2dxDownloader.nativeOnFinish(Cocos2dxDownloader.access$000(localCocos2dxDownloader), size, progress, url, name);
  }
}

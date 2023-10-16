package org.cocos2dx.package_1;

import java.util.concurrent.CountDownLatch;

class l
  implements Runnable
{
  private CountDownLatch a;
  private final String b;
  private boolean[] c;
  private final int l;
  
  l(CountDownLatch paramCountDownLatch, boolean[] paramArrayOfBoolean, int paramInt, String paramString)
  {
    a = paramCountDownLatch;
    c = paramArrayOfBoolean;
    l = paramInt;
    b = paramString;
  }
  
  public void run()
  {
    c[0] = Cocos2dxWebViewHelper._shouldStartLoading(l, b);
    a.countDown();
  }
}

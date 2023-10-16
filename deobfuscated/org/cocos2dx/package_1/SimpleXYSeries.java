package org.cocos2dx.package_1;

final class SimpleXYSeries
  implements Runnable
{
  SimpleXYSeries(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong) {}
  
  public void run()
  {
    Cocos2dxAccelerometer.onSensorChanged(a, c, b, d);
  }
}

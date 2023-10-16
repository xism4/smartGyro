package org.cocos2dx.package_1;

class Replay
  implements Runnable
{
  Replay(Cocos2dxGLSurfaceView paramCocos2dxGLSurfaceView) {}
  
  public void run()
  {
    Cocos2dxGLSurfaceView.access$300(count).handleOnPause();
  }
}

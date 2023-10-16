package org.cocos2dx.package_1;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import java.lang.ref.WeakReference;

public class Cocos2dxVideoHelper
{
  static final int KeyEventBack = 1000;
  private static final int VideoTaskCreate = 0;
  private static final int VideoTaskFullScreen = 12;
  private static final int VideoTaskKeepRatio = 11;
  private static final int VideoTaskPause = 5;
  private static final int VideoTaskRemove = 1;
  private static final int VideoTaskRestart = 10;
  private static final int VideoTaskResume = 6;
  private static final int VideoTaskSeek = 8;
  private static final int VideoTaskSetRect = 3;
  private static final int VideoTaskSetSource = 2;
  private static final int VideoTaskSetVisible = 9;
  private static final int VideoTaskStart = 4;
  private static final int VideoTaskStop = 7;
  static b mVideoHandler;
  private static int videoTag;
  private Cocos2dxActivity mActivity = null;
  private FrameLayout mLayout = null;
  private SparseArray<org.cocos2dx.lib.Cocos2dxVideoView> sVideoViews = null;
  Cocos2dxVideoView.OnVideoEventListener videoEventListener = new LogActivity.1(this);
  
  Cocos2dxVideoHelper(Cocos2dxActivity paramCocos2dxActivity, FrameLayout paramFrameLayout)
  {
    mActivity = paramCocos2dxActivity;
    mLayout = paramFrameLayout;
    mVideoHandler = new b();
    sVideoViews = new SparseArray();
  }
  
  private void _createVideoView(int paramInt)
  {
    Cocos2dxVideoView localCocos2dxVideoView = new Cocos2dxVideoView(mActivity, paramInt);
    sVideoViews.put(paramInt, localCocos2dxVideoView);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    mLayout.addView(localCocos2dxVideoView, localLayoutParams);
    localCocos2dxVideoView.setZOrderOnTop(true);
    localCocos2dxVideoView.setOnCompletionListener(videoEventListener);
  }
  
  private void _pauseVideo(int paramInt)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt);
    if (localCocos2dxVideoView != null) {
      localCocos2dxVideoView.pause();
    }
  }
  
  private void _removeVideoView(int paramInt)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt);
    if (localCocos2dxVideoView != null)
    {
      localCocos2dxVideoView.stopPlayback();
      sVideoViews.remove(paramInt);
      mLayout.removeView(localCocos2dxVideoView);
    }
  }
  
  private void _restartVideo(int paramInt)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt);
    if (localCocos2dxVideoView != null) {
      localCocos2dxVideoView.restart();
    }
  }
  
  private void _resumeVideo(int paramInt)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt);
    if (localCocos2dxVideoView != null) {
      localCocos2dxVideoView.resume();
    }
  }
  
  private void _seekVideoTo(int paramInt1, int paramInt2)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt1);
    if (localCocos2dxVideoView != null) {
      localCocos2dxVideoView.seekTo(paramInt2);
    }
  }
  
  private void _setFullScreenEnabled(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt1);
    if (localCocos2dxVideoView != null) {
      localCocos2dxVideoView.setFullScreenEnabled(paramBoolean, paramInt2, paramInt3);
    }
  }
  
  private void _setVideoKeepRatio(int paramInt, boolean paramBoolean)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt);
    if (localCocos2dxVideoView != null) {
      localCocos2dxVideoView.setKeepRatio(paramBoolean);
    }
  }
  
  private void _setVideoRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt1);
    if (localCocos2dxVideoView != null) {
      localCocos2dxVideoView.setVideoRect(paramInt2, paramInt3, paramInt4, paramInt5);
    }
  }
  
  private void _setVideoURL(int paramInt1, int paramInt2, String paramString)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt1);
    if (localCocos2dxVideoView != null)
    {
      if (paramInt2 != 0)
      {
        if (paramInt2 != 1) {
          return;
        }
        localCocos2dxVideoView.setVideoURL(paramString);
        return;
      }
      localCocos2dxVideoView.setVideoFileName(paramString);
    }
  }
  
  private void _setVideoVisible(int paramInt, boolean paramBoolean)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt);
    if (localCocos2dxVideoView != null)
    {
      if (paramBoolean)
      {
        localCocos2dxVideoView.fixSize();
        paramInt = 0;
      }
      else
      {
        paramInt = 4;
      }
      localCocos2dxVideoView.setVisibility(paramInt);
    }
  }
  
  private void _startVideo(int paramInt, float paramFloat)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt);
    if (localCocos2dxVideoView != null) {
      localCocos2dxVideoView.start(paramFloat);
    }
  }
  
  private void _stopVideo(int paramInt)
  {
    Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(paramInt);
    if (localCocos2dxVideoView != null) {
      localCocos2dxVideoView.stop();
    }
  }
  
  public static int createVideoWidget()
  {
    Message localMessage = new Message();
    what = 0;
    arg1 = videoTag;
    mVideoHandler.sendMessage(localMessage);
    int i = videoTag;
    videoTag = i + 1;
    return i;
  }
  
  public static native void nativeExecuteVideoCallback(int paramInt1, int paramInt2);
  
  private void onBackKeyEvent()
  {
    int j = sVideoViews.size();
    int i = 0;
    while (i < j)
    {
      int k = sVideoViews.keyAt(i);
      Cocos2dxVideoView localCocos2dxVideoView = (Cocos2dxVideoView)sVideoViews.get(k);
      if (localCocos2dxVideoView != null)
      {
        localCocos2dxVideoView.setFullScreenEnabled(false, 0, 0);
        mActivity.runOnGLThread(new a(k, 1000));
      }
      i += 1;
    }
  }
  
  public static void pauseVideo(int paramInt)
  {
    Message localMessage = new Message();
    what = 5;
    arg1 = paramInt;
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void removeVideoWidget(int paramInt)
  {
    Message localMessage = new Message();
    what = 1;
    arg1 = paramInt;
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void restartVideo(int paramInt)
  {
    Message localMessage = new Message();
    what = 10;
    arg1 = paramInt;
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void resumeVideo(int paramInt)
  {
    Message localMessage = new Message();
    what = 6;
    arg1 = paramInt;
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void seekVideoTo(int paramInt1, int paramInt2)
  {
    Message localMessage = new Message();
    what = 8;
    arg1 = paramInt1;
    arg2 = paramInt2;
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void setFullScreenEnabled(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3)
  {
    Message localMessage = new Message();
    what = 12;
    arg1 = paramInt1;
    if (paramBoolean) {
      arg2 = 1;
    } else {
      arg2 = 0;
    }
    obj = new Rect(0, 0, paramInt2, paramInt3);
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void setPlaySpeed(float paramFloat) {}
  
  public static void setVideoKeepRatioEnabled(int paramInt, boolean paramBoolean)
  {
    Message localMessage = new Message();
    what = 11;
    arg1 = paramInt;
    if (paramBoolean) {
      paramInt = 1;
    } else {
      paramInt = 0;
    }
    arg2 = paramInt;
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void setVideoRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    Message localMessage = new Message();
    what = 3;
    arg1 = paramInt1;
    obj = new Rect(paramInt2, paramInt3, paramInt4, paramInt5);
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void setVideoUrl(int paramInt1, int paramInt2, String paramString)
  {
    Message localMessage = new Message();
    what = 2;
    arg1 = paramInt1;
    arg2 = paramInt2;
    obj = paramString;
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void setVideoVisible(int paramInt, boolean paramBoolean)
  {
    Message localMessage = new Message();
    what = 9;
    arg1 = paramInt;
    if (paramBoolean) {
      paramInt = 1;
    } else {
      paramInt = 0;
    }
    arg2 = paramInt;
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void startVideo(int paramInt1, int paramInt2)
  {
    Message localMessage = new Message();
    what = 4;
    arg1 = paramInt1;
    arg2 = paramInt2;
    mVideoHandler.sendMessage(localMessage);
  }
  
  public static void stopVideo(int paramInt)
  {
    Message localMessage = new Message();
    what = 7;
    arg1 = paramInt;
    mVideoHandler.sendMessage(localMessage);
  }
  
  class a
    implements Runnable
  {
    private int is;
    private int l;
    
    public a(int paramInt1, int paramInt2)
    {
      l = paramInt1;
      is = paramInt2;
    }
    
    public void run()
    {
      Cocos2dxVideoHelper.nativeExecuteVideoCallback(l, is);
    }
  }
  
  class b
    extends Handler
  {
    WeakReference<org.cocos2dx.lib.Cocos2dxVideoHelper> this$0;
    
    b()
    {
      this$0 = new WeakReference(this$1);
    }
    
    public void handleMessage(Message paramMessage)
    {
      int i = what;
      if (i != 1000)
      {
        Cocos2dxVideoHelper localCocos2dxVideoHelper;
        Rect localRect;
        switch (i)
        {
        default: 
          break;
        case 12: 
          localCocos2dxVideoHelper = (Cocos2dxVideoHelper)this$0.get();
          localRect = (Rect)obj;
          if (arg2 == 1) {
            localCocos2dxVideoHelper._setFullScreenEnabled(arg1, true, right, bottom);
          } else {
            localCocos2dxVideoHelper._setFullScreenEnabled(arg1, false, right, bottom);
          }
          break;
        case 11: 
          localCocos2dxVideoHelper = (Cocos2dxVideoHelper)this$0.get();
          if (arg2 == 1) {
            localCocos2dxVideoHelper._setVideoKeepRatio(arg1, true);
          } else {
            localCocos2dxVideoHelper._setVideoKeepRatio(arg1, false);
          }
          break;
        case 10: 
          ((Cocos2dxVideoHelper)this$0.get())._restartVideo(arg1);
          break;
        case 9: 
          localCocos2dxVideoHelper = (Cocos2dxVideoHelper)this$0.get();
          if (arg2 == 1) {
            localCocos2dxVideoHelper._setVideoVisible(arg1, true);
          } else {
            localCocos2dxVideoHelper._setVideoVisible(arg1, false);
          }
          break;
        case 8: 
          ((Cocos2dxVideoHelper)this$0.get())._seekVideoTo(arg1, arg2);
          break;
        case 7: 
          ((Cocos2dxVideoHelper)this$0.get())._stopVideo(arg1);
          break;
        case 6: 
          ((Cocos2dxVideoHelper)this$0.get())._resumeVideo(arg1);
          break;
        case 5: 
          ((Cocos2dxVideoHelper)this$0.get())._pauseVideo(arg1);
          break;
        case 4: 
          ((Cocos2dxVideoHelper)this$0.get())._startVideo(arg1, arg2 / 100.0F);
          break;
        case 3: 
          localCocos2dxVideoHelper = (Cocos2dxVideoHelper)this$0.get();
          localRect = (Rect)obj;
          localCocos2dxVideoHelper._setVideoRect(arg1, left, top, right, bottom);
          break;
        case 2: 
          ((Cocos2dxVideoHelper)this$0.get())._setVideoURL(arg1, arg2, (String)obj);
          break;
        case 1: 
          ((Cocos2dxVideoHelper)this$0.get())._removeVideoView(arg1);
          break;
        case 0: 
          ((Cocos2dxVideoHelper)this$0.get())._createVideoView(arg1);
          break;
        }
      }
      else
      {
        ((Cocos2dxVideoHelper)this$0.get()).onBackKeyEvent();
      }
      super.handleMessage(paramMessage);
    }
  }
}

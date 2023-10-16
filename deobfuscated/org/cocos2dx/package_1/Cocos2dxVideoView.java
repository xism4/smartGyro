package org.cocos2dx.package_1;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController.MediaPlayerControl;
import java.io.IOException;
import java.util.Map;

public class Cocos2dxVideoView
  extends SurfaceView
  implements MediaController.MediaPlayerControl
{
  private static final String AssetResourceRoot = "assets/";
  private static final int EVENT_COMPLETED = 3;
  private static final int EVENT_PAUSED = 1;
  private static final int EVENT_PLAYING = 0;
  private static final int EVENT_STOPPED = 2;
  private static final int STATE_ERROR = -1;
  private static final int STATE_IDLE = 0;
  private static final int STATE_PAUSED = 4;
  private static final int STATE_PLAYBACK_COMPLETED = 5;
  private static final int STATE_PLAYING = 3;
  private static final int STATE_PREPARED = 2;
  private static final int STATE_PREPARING = 1;
  private String TAG = "Cocos2dxVideoView";
  protected float _playSpeed = 1.0F;
  private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new DownloadServiceImpl.2(this);
  protected Cocos2dxActivity mCocos2dxActivity = null;
  private MediaPlayer.OnCompletionListener mCompletionListener = new AudioClip.1(this);
  private int mCurrentBufferPercentage;
  private int mCurrentState = 0;
  private int mDuration;
  private MediaPlayer.OnErrorListener mErrorListener = new AudioPlayer(this);
  protected boolean mFullScreenEnabled = false;
  protected int mFullScreenHeight = 0;
  protected int mFullScreenWidth = 0;
  private boolean mIsAssetRouse = false;
  private boolean mKeepRatio = false;
  private MediaPlayer mMediaPlayer = null;
  private boolean mNeedResume = false;
  private MediaPlayer.OnErrorListener mOnErrorListener;
  private MediaPlayer.OnPreparedListener mOnPreparedListener;
  private OnVideoEventListener mOnVideoEventListener;
  MediaPlayer.OnPreparedListener mPreparedListener = new MainActivity.8(this);
  SurfaceHolder.Callback mSHCallback = new CameraPreview.1(this);
  private int mSeekWhenPrepared;
  protected MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener = new AllInOneActivity.3(this);
  private SurfaceHolder mSurfaceHolder = null;
  private int mTargetState = 0;
  private String mVideoFilePath = null;
  private int mVideoHeight = 0;
  private Uri mVideoUri;
  private int mVideoWidth = 0;
  protected int mViewHeight = 0;
  protected int mViewLeft = 0;
  private int mViewTag = 0;
  protected int mViewTop = 0;
  protected int mViewWidth = 0;
  protected int mVisibleHeight = 0;
  protected int mVisibleLeft = 0;
  protected int mVisibleTop = 0;
  protected int mVisibleWidth = 0;
  
  public Cocos2dxVideoView(Cocos2dxActivity paramCocos2dxActivity, int paramInt)
  {
    super(paramCocos2dxActivity);
    mViewTag = paramInt;
    mCocos2dxActivity = paramCocos2dxActivity;
    initVideoView();
  }
  
  private void initVideoView()
  {
    mVideoWidth = 0;
    mVideoHeight = 0;
    getHolder().addCallback(mSHCallback);
    getHolder().setType(3);
    setFocusable(true);
    setFocusableInTouchMode(true);
    mCurrentState = 0;
    mTargetState = 0;
  }
  
  private void openVideo()
  {
    if (mSurfaceHolder == null) {
      return;
    }
    if (mIsAssetRouse)
    {
      if (mVideoFilePath != null) {}
    }
    else if (mVideoUri == null) {
      return;
    }
    Object localObject1 = new Intent("com.android.music.musicservicecommand");
    ((Intent)localObject1).putExtra("command", "pause");
    mCocos2dxActivity.sendBroadcast((Intent)localObject1);
    release(false);
    try
    {
      localObject1 = new MediaPlayer();
      mMediaPlayer = ((MediaPlayer)localObject1);
      localObject1 = mMediaPlayer;
      Object localObject2 = mPreparedListener;
      ((MediaPlayer)localObject1).setOnPreparedListener((MediaPlayer.OnPreparedListener)localObject2);
      localObject1 = mMediaPlayer;
      localObject2 = mSizeChangedListener;
      ((MediaPlayer)localObject1).setOnVideoSizeChangedListener((MediaPlayer.OnVideoSizeChangedListener)localObject2);
      localObject1 = mMediaPlayer;
      localObject2 = mCompletionListener;
      ((MediaPlayer)localObject1).setOnCompletionListener((MediaPlayer.OnCompletionListener)localObject2);
      localObject1 = mMediaPlayer;
      localObject2 = mErrorListener;
      ((MediaPlayer)localObject1).setOnErrorListener((MediaPlayer.OnErrorListener)localObject2);
      localObject1 = mMediaPlayer;
      localObject2 = mBufferingUpdateListener;
      ((MediaPlayer)localObject1).setOnBufferingUpdateListener((MediaPlayer.OnBufferingUpdateListener)localObject2);
      localObject1 = mMediaPlayer;
      localObject2 = mSurfaceHolder;
      ((MediaPlayer)localObject1).setDisplay((SurfaceHolder)localObject2);
      localObject1 = mMediaPlayer;
      ((MediaPlayer)localObject1).setAudioStreamType(3);
      localObject1 = mMediaPlayer;
      ((MediaPlayer)localObject1).setScreenOnWhilePlaying(true);
      mDuration = -1;
      mCurrentBufferPercentage = 0;
      if (mIsAssetRouse)
      {
        localObject1 = mCocos2dxActivity;
        localObject1 = ((ContextWrapper)localObject1).getAssets();
        localObject2 = mVideoFilePath;
        localObject1 = ((AssetManager)localObject1).openFd((String)localObject2);
        localObject2 = mMediaPlayer;
        ((MediaPlayer)localObject2).setDataSource(((AssetFileDescriptor)localObject1).getFileDescriptor(), ((AssetFileDescriptor)localObject1).getStartOffset(), ((AssetFileDescriptor)localObject1).getLength());
      }
      else
      {
        localObject1 = mMediaPlayer;
        localObject2 = mCocos2dxActivity;
        localObject3 = mVideoUri;
        ((MediaPlayer)localObject1).setDataSource((Context)localObject2, (Uri)localObject3);
      }
      localObject1 = mMediaPlayer;
      ((MediaPlayer)localObject1).prepareAsync();
      mCurrentState = 1;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localObject1 = TAG;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Unable to open content: ");
      ((StringBuilder)localObject3).append(mVideoUri);
      Log.w((String)localObject1, ((StringBuilder)localObject3).toString(), localIllegalArgumentException);
      mCurrentState = -1;
      mTargetState = -1;
      mErrorListener.onError(mMediaPlayer, 1, 0);
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localObject1 = TAG;
        Object localObject3 = new StringBuilder();
      }
    }
  }
  
  private void release(boolean paramBoolean)
  {
    MediaPlayer localMediaPlayer = mMediaPlayer;
    if (localMediaPlayer != null)
    {
      localMediaPlayer.reset();
      mMediaPlayer.release();
      mMediaPlayer = null;
      mCurrentState = 0;
      if (paramBoolean) {
        mTargetState = 0;
      }
    }
  }
  
  private void setVideoURI(Uri paramUri, Map paramMap)
  {
    mVideoUri = paramUri;
    mSeekWhenPrepared = 0;
    mVideoWidth = 0;
    mVideoHeight = 0;
    openVideo();
    requestLayout();
    invalidate();
  }
  
  public boolean canPause()
  {
    return true;
  }
  
  public boolean canSeekBackward()
  {
    return true;
  }
  
  public boolean canSeekForward()
  {
    return true;
  }
  
  public void fixSize()
  {
    if (mFullScreenEnabled)
    {
      fixSize(0, 0, mFullScreenWidth, mFullScreenHeight);
      return;
    }
    fixSize(mViewLeft, mViewTop, mViewWidth, mViewHeight);
  }
  
  public void fixSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = mVideoWidth;
    if (i != 0)
    {
      int j = mVideoHeight;
      if (j != 0) {
        if ((paramInt3 != 0) && (paramInt4 != 0))
        {
          if (mKeepRatio)
          {
            if (i * paramInt4 > paramInt3 * j)
            {
              mVisibleWidth = paramInt3;
              mVisibleHeight = (j * paramInt3 / i);
            }
            else if (i * paramInt4 < paramInt3 * j)
            {
              mVisibleWidth = (i * paramInt4 / j);
              mVisibleHeight = paramInt4;
            }
            mVisibleLeft = (paramInt1 + (paramInt3 - mVisibleWidth) / 2);
            mVisibleTop = (paramInt2 + (paramInt4 - mVisibleHeight) / 2);
            break label184;
          }
        }
        else
        {
          mVisibleLeft = paramInt1;
          mVisibleTop = paramInt2;
          mVisibleWidth = mVideoWidth;
          mVisibleHeight = mVideoHeight;
          break label184;
        }
      }
    }
    mVisibleLeft = paramInt1;
    mVisibleTop = paramInt2;
    mVisibleWidth = paramInt3;
    mVisibleHeight = paramInt4;
    label184:
    getHolder().setFixedSize(mVisibleWidth, mVisibleHeight);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    leftMargin = mVisibleLeft;
    topMargin = mVisibleTop;
    gravity = 51;
    setLayoutParams(localLayoutParams);
  }
  
  public int getAudioSessionId()
  {
    return mMediaPlayer.getAudioSessionId();
  }
  
  public int getBufferPercentage()
  {
    if (mMediaPlayer != null) {
      return mCurrentBufferPercentage;
    }
    return 0;
  }
  
  public int getCurrentPosition()
  {
    if (isInPlaybackState()) {
      return mMediaPlayer.getCurrentPosition();
    }
    return 0;
  }
  
  public int getDuration()
  {
    if (isInPlaybackState())
    {
      i = mDuration;
      if (i > 0) {
        return i;
      }
    }
    for (int i = mMediaPlayer.getDuration();; i = -1)
    {
      mDuration = i;
      return mDuration;
    }
  }
  
  public boolean isInPlaybackState()
  {
    if (mMediaPlayer != null)
    {
      int i = mCurrentState;
      if ((i != -1) && (i != 0) && (i != 1)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isPlaying()
  {
    return (isInPlaybackState()) && (mMediaPlayer.isPlaying());
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    String str;
    StringBuilder localStringBuilder;
    if ((mVideoWidth != 0) && (mVideoHeight != 0))
    {
      setMeasuredDimension(mVisibleWidth, mVisibleHeight);
      str = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(mVisibleWidth);
      localStringBuilder.append(":");
      paramInt1 = mVisibleHeight;
    }
    else
    {
      setMeasuredDimension(mViewWidth, mViewHeight);
      str = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(mViewWidth);
      localStringBuilder.append(":");
      paramInt1 = mViewHeight;
    }
    localStringBuilder.append(paramInt1);
    Log.i(str, localStringBuilder.toString());
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() & 0xFF) == 1)
    {
      if (isPlaying())
      {
        pause();
        return true;
      }
      if (mCurrentState == 4) {
        resume();
      }
    }
    return true;
  }
  
  public void pause()
  {
    if ((isInPlaybackState()) && (mMediaPlayer.isPlaying()))
    {
      mMediaPlayer.pause();
      mCurrentState = 4;
      OnVideoEventListener localOnVideoEventListener = mOnVideoEventListener;
      if (localOnVideoEventListener != null) {
        localOnVideoEventListener.onVideoEvent(mViewTag, 1);
      }
    }
    mTargetState = 4;
  }
  
  public int resolveAdjustedSize(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt2);
    if (i != Integer.MIN_VALUE)
    {
      paramInt2 = paramInt1;
      if (i != 0)
      {
        if (i != 1073741824) {
          return paramInt1;
        }
        return j;
      }
    }
    else
    {
      paramInt2 = Math.min(paramInt1, j);
    }
    return paramInt2;
  }
  
  public void restart()
  {
    if (isInPlaybackState())
    {
      mMediaPlayer.seekTo(0);
      mMediaPlayer.start();
      mCurrentState = 3;
      mTargetState = 3;
    }
  }
  
  public void resume()
  {
    if ((isInPlaybackState()) && (mCurrentState == 4))
    {
      mMediaPlayer.start();
      mCurrentState = 3;
      OnVideoEventListener localOnVideoEventListener = mOnVideoEventListener;
      if (localOnVideoEventListener != null) {
        localOnVideoEventListener.onVideoEvent(mViewTag, 0);
      }
    }
  }
  
  public void seekTo(int paramInt)
  {
    int i = paramInt;
    if (isInPlaybackState())
    {
      mMediaPlayer.seekTo(paramInt);
      i = 0;
    }
    mSeekWhenPrepared = i;
  }
  
  public void setFullScreenEnabled(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (mFullScreenEnabled != paramBoolean)
    {
      mFullScreenEnabled = paramBoolean;
      if ((paramInt1 != 0) && (paramInt2 != 0))
      {
        mFullScreenWidth = paramInt1;
        mFullScreenHeight = paramInt2;
      }
      fixSize();
    }
  }
  
  public void setKeepRatio(boolean paramBoolean)
  {
    mKeepRatio = paramBoolean;
    fixSize();
  }
  
  public void setOnCompletionListener(OnVideoEventListener paramOnVideoEventListener)
  {
    mOnVideoEventListener = paramOnVideoEventListener;
  }
  
  public void setOnErrorListener(MediaPlayer.OnErrorListener paramOnErrorListener)
  {
    mOnErrorListener = paramOnErrorListener;
  }
  
  public void setOnPreparedListener(MediaPlayer.OnPreparedListener paramOnPreparedListener)
  {
    mOnPreparedListener = paramOnPreparedListener;
  }
  
  public void setPlaySpeed(float paramFloat)
  {
    Object localObject = mMediaPlayer;
    if (localObject == null) {}
    for (;;)
    {
      _playSpeed = paramFloat;
      return;
      if (Build.VERSION.SDK_INT < 23) {
        break;
      }
      localObject = ((MediaPlayer)localObject).getPlaybackParams();
      ((PlaybackParams)localObject).setSpeed(paramFloat);
      mMediaPlayer.setPlaybackParams((PlaybackParams)localObject);
    }
  }
  
  public void setVideoFileName(String paramString)
  {
    String str = paramString;
    if (paramString.startsWith("assets/")) {
      str = paramString.substring(7);
    }
    boolean bool;
    if (str.startsWith("/"))
    {
      bool = false;
    }
    else
    {
      mVideoFilePath = str;
      bool = true;
    }
    mIsAssetRouse = bool;
    setVideoURI(Uri.parse(str), null);
  }
  
  public void setVideoRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mViewLeft = paramInt1;
    mViewTop = paramInt2;
    mViewWidth = paramInt3;
    mViewHeight = paramInt4;
    fixSize(mViewLeft, mViewTop, mViewWidth, mViewHeight);
  }
  
  public void setVideoURL(String paramString)
  {
    mIsAssetRouse = false;
    setVideoURI(Uri.parse(paramString), null);
  }
  
  public void setVisibility(int paramInt)
  {
    if (paramInt == 4)
    {
      mNeedResume = isPlaying();
      if (mNeedResume) {
        mSeekWhenPrepared = getCurrentPosition();
      }
    }
    else if (mNeedResume)
    {
      start();
      mNeedResume = false;
    }
    super.setVisibility(paramInt);
  }
  
  public void start()
  {
    if (isInPlaybackState())
    {
      mMediaPlayer.start();
      setPlaySpeed(_playSpeed);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("??0=");
      ((StringBuilder)localObject).append(_playSpeed);
      Log.d("????", ((StringBuilder)localObject).toString());
      mCurrentState = 3;
      localObject = mOnVideoEventListener;
      if (localObject != null) {
        ((OnVideoEventListener)localObject).onVideoEvent(mViewTag, 0);
      }
    }
    mTargetState = 3;
  }
  
  public void start(float paramFloat)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("??1=");
    ((StringBuilder)localObject).append(paramFloat);
    Log.d("????", ((StringBuilder)localObject).toString());
    float f = paramFloat;
    if (paramFloat < 0.1D) {
      f = 0.1F;
    }
    if (isInPlaybackState())
    {
      mMediaPlayer.start();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("??=");
      ((StringBuilder)localObject).append(f);
      Log.d("????", ((StringBuilder)localObject).toString());
      mCurrentState = 3;
      localObject = mOnVideoEventListener;
      if (localObject != null) {
        ((OnVideoEventListener)localObject).onVideoEvent(mViewTag, 0);
      }
    }
    setPlaySpeed(f);
    mTargetState = 3;
  }
  
  public void stop()
  {
    if ((isInPlaybackState()) && (mMediaPlayer.isPlaying()))
    {
      stopPlayback();
      OnVideoEventListener localOnVideoEventListener = mOnVideoEventListener;
      if (localOnVideoEventListener != null) {
        localOnVideoEventListener.onVideoEvent(mViewTag, 2);
      }
    }
  }
  
  public void stopPlayback()
  {
    MediaPlayer localMediaPlayer = mMediaPlayer;
    if (localMediaPlayer != null)
    {
      localMediaPlayer.stop();
      mMediaPlayer.release();
      mMediaPlayer = null;
      mCurrentState = 0;
      mTargetState = 0;
    }
  }
  
  public void suspend()
  {
    release(false);
  }
  
  public abstract interface OnVideoEventListener
  {
    public abstract void onVideoEvent(int paramInt1, int paramInt2);
  }
}

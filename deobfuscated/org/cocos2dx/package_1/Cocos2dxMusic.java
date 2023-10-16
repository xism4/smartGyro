package org.cocos2dx.package_1;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import java.io.FileInputStream;
import lombok.libs.org.objectweb.asm.ByteVector;

public class Cocos2dxMusic
{
  private static final String Debug = "Cocos2dxMusic";
  private MediaPlayer mBackgroundMediaPlayer;
  private final Context mContext;
  private String mCurrentPath;
  private boolean mIsAudioFocus = true;
  private boolean mIsLoop = false;
  private float mLeftVolume;
  private boolean mManualPaused = false;
  private boolean mPaused;
  private float mRightVolume;
  
  public Cocos2dxMusic(Context paramContext)
  {
    mContext = paramContext;
    initData();
  }
  
  private MediaPlayer createMediaPlayer(String paramString)
  {
    Object localObject1 = new MediaPlayer();
    try
    {
      boolean bool = paramString.startsWith("/");
      if (bool)
      {
        paramString = new FileInputStream(paramString);
        ((MediaPlayer)localObject1).setDataSource(paramString.getFD());
        paramString.close();
      }
      else
      {
        localObject2 = Cocos2dxHelper.getObbFile();
        long l1;
        if (localObject2 != null)
        {
          localObject2 = Cocos2dxHelper.getObbFile().a(paramString);
          paramString = ((AssetFileDescriptor)localObject2).getFileDescriptor();
          l1 = ((AssetFileDescriptor)localObject2).getStartOffset();
        }
        for (long l2 = ((AssetFileDescriptor)localObject2).getLength();; l2 = ((AssetFileDescriptor)localObject2).getLength())
        {
          ((MediaPlayer)localObject1).setDataSource(paramString, l1, l2);
          break;
          localObject2 = mContext;
          localObject2 = ((Context)localObject2).getAssets().openFd(paramString);
          paramString = ((AssetFileDescriptor)localObject2).getFileDescriptor();
          l1 = ((AssetFileDescriptor)localObject2).getStartOffset();
        }
      }
      ((MediaPlayer)localObject1).prepare();
      float f1 = mLeftVolume;
      float f2 = mRightVolume;
      ((MediaPlayer)localObject1).setVolume(f1, f2);
      return localObject1;
    }
    catch (Exception paramString)
    {
      localObject1 = Debug;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("error: ");
      ((StringBuilder)localObject2).append(paramString.getMessage());
      Log.e((String)localObject1, ((StringBuilder)localObject2).toString(), paramString);
    }
    return null;
  }
  
  private void initData()
  {
    mLeftVolume = 0.5F;
    mRightVolume = 0.5F;
    mBackgroundMediaPlayer = null;
    mPaused = false;
    mCurrentPath = null;
  }
  
  public void fullReset()
  {
    MediaPlayer localMediaPlayer = mBackgroundMediaPlayer;
    if (localMediaPlayer != null) {
      localMediaPlayer.release();
    }
    initData();
  }
  
  public float getBackgroundVolume()
  {
    if (mBackgroundMediaPlayer != null) {
      return (mLeftVolume + mRightVolume) / 2.0F;
    }
    return 0.0F;
  }
  
  public boolean isBackgroundMusicPlaying()
  {
    if (mBackgroundMediaPlayer == null) {
      return false;
    }
    MediaPlayer localMediaPlayer = mBackgroundMediaPlayer;
    try
    {
      boolean bool = localMediaPlayer.isPlaying();
      return bool;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
    Log.e(Debug, "isBackgroundMusicPlaying, IllegalStateException was triggered!");
    return false;
  }
  
  public void onEnterBackground()
  {
    if (mBackgroundMediaPlayer != null)
    {
      MediaPlayer localMediaPlayer = mBackgroundMediaPlayer;
      try
      {
        boolean bool = localMediaPlayer.isPlaying();
        if (!bool) {
          return;
        }
        localMediaPlayer = mBackgroundMediaPlayer;
        localMediaPlayer.pause();
        mPaused = true;
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;) {}
      }
      Log.e(Debug, "onEnterBackground, IllegalStateException was triggered!");
      return;
    }
  }
  
  public void onEnterForeground()
  {
    if ((!mManualPaused) && (mBackgroundMediaPlayer != null) && (mPaused))
    {
      MediaPlayer localMediaPlayer = mBackgroundMediaPlayer;
      try
      {
        localMediaPlayer.start();
        mPaused = false;
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;) {}
      }
      Log.e(Debug, "onEnterForeground, IllegalStateException was triggered!");
      return;
    }
  }
  
  public void pauseBackgroundMusic()
  {
    if (mBackgroundMediaPlayer != null)
    {
      MediaPlayer localMediaPlayer = mBackgroundMediaPlayer;
      try
      {
        boolean bool = localMediaPlayer.isPlaying();
        if (!bool) {
          return;
        }
        localMediaPlayer = mBackgroundMediaPlayer;
        localMediaPlayer.pause();
        mPaused = true;
        mManualPaused = true;
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;) {}
      }
      Log.e(Debug, "pauseBackgroundMusic, IllegalStateException was triggered!");
      return;
    }
  }
  
  public void playBackgroundMusic(String paramString, boolean paramBoolean)
  {
    Object localObject = mCurrentPath;
    if (localObject == null) {}
    for (;;)
    {
      mBackgroundMediaPlayer = createMediaPlayer(paramString);
      mCurrentPath = paramString;
      break;
      if (((String)localObject).equals(paramString)) {
        break;
      }
      localObject = mBackgroundMediaPlayer;
      if (localObject != null) {
        ((MediaPlayer)localObject).release();
      }
    }
    paramString = mBackgroundMediaPlayer;
    if (paramString == null) {
      localObject = Debug;
    }
    for (paramString = "playBackgroundMusic: background media player is null";; paramString = "playBackgroundMusic: error state")
    {
      Log.e((String)localObject, paramString);
      return;
      if (mPaused) {}
      try
      {
        paramString.seekTo(0);
        boolean bool;
        do
        {
          paramString = mBackgroundMediaPlayer;
          paramString.start();
          break;
          bool = paramString.isPlaying();
        } while (!bool);
        paramString = mBackgroundMediaPlayer;
        paramString.seekTo(0);
        paramString = mBackgroundMediaPlayer;
        paramString.setLooping(paramBoolean);
        mPaused = false;
        mIsLoop = paramBoolean;
        return;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      localObject = Debug;
    }
  }
  
  public void preloadBackgroundMusic(String paramString)
  {
    Object localObject = mCurrentPath;
    if ((localObject == null) || (!((String)localObject).equals(paramString)))
    {
      localObject = mBackgroundMediaPlayer;
      if (localObject != null) {
        ((MediaPlayer)localObject).release();
      }
      mBackgroundMediaPlayer = createMediaPlayer(paramString);
      mCurrentPath = paramString;
    }
  }
  
  public void resumeBackgroundMusic()
  {
    if ((mBackgroundMediaPlayer != null) && (mPaused))
    {
      MediaPlayer localMediaPlayer = mBackgroundMediaPlayer;
      try
      {
        localMediaPlayer.start();
        mPaused = false;
        mManualPaused = false;
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;) {}
      }
      Log.e(Debug, "resumeBackgroundMusic, IllegalStateException was triggered!");
      return;
    }
  }
  
  public void rewindBackgroundMusic()
  {
    if (mBackgroundMediaPlayer != null) {
      playBackgroundMusic(mCurrentPath, mIsLoop);
    }
  }
  
  void setAudioFocus(boolean paramBoolean)
  {
    mIsAudioFocus = paramBoolean;
    if (mBackgroundMediaPlayer != null)
    {
      paramBoolean = mIsAudioFocus;
      float f2 = 0.0F;
      float f1;
      if (paramBoolean) {
        f1 = mLeftVolume;
      } else {
        f1 = 0.0F;
      }
      if (mIsAudioFocus) {
        f2 = mRightVolume;
      }
      mBackgroundMediaPlayer.setVolume(f1, f2);
    }
  }
  
  public void setBackgroundVolume(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat < 0.0F) {
      f = 0.0F;
    }
    paramFloat = f;
    if (f > 1.0F) {
      paramFloat = 1.0F;
    }
    mRightVolume = paramFloat;
    mLeftVolume = paramFloat;
    MediaPlayer localMediaPlayer = mBackgroundMediaPlayer;
    if ((localMediaPlayer != null) && (mIsAudioFocus)) {
      localMediaPlayer.setVolume(mLeftVolume, mRightVolume);
    }
  }
  
  public void stopBackgroundMusic()
  {
    MediaPlayer localMediaPlayer = mBackgroundMediaPlayer;
    if (localMediaPlayer != null)
    {
      localMediaPlayer.release();
      mBackgroundMediaPlayer = createMediaPlayer(mCurrentPath);
      mPaused = false;
    }
  }
  
  public boolean willPlayBackgroundMusic()
  {
    return ((AudioManager)mContext.getSystemService("audio")).isMusicActive() ^ true;
  }
}

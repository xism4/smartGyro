package org.cocos2dx.package_1;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.libs.org.objectweb.asm.ByteVector;

public class Cocos2dxSound
{
  private static final int INVALID_SOUND_ID = -1;
  private static final int INVALID_STREAM_ID = -1;
  private static final int LOAD_TIME_OUT = 500;
  private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 5;
  private static final int MAX_SIMULTANEOUS_STREAMS_I9100 = 3;
  private static final String PAGE_KEY = "Cocos2dxSound";
  private static final int SOUND_PRIORITY = 1;
  private static final int SOUND_QUALITY = 5;
  private static final float SOUND_RATE = 1.0F;
  private final Context mContext;
  private boolean mIsAudioFocus = true;
  private float mLeftVolume;
  private final Object mLockPathStreamIDsMap = new Object();
  private final HashMap<String, Integer> mPathSoundIDMap = new HashMap();
  private final HashMap<String, ArrayList<Integer>> mPathStreamIDsMap = new HashMap();
  private ConcurrentHashMap<Integer, org.cocos2dx.lib.Cocos2dxSound.a> mPlayWhenLoadedEffects = new ConcurrentHashMap();
  private float mRightVolume;
  private SoundPool mSoundPool;
  
  public Cocos2dxSound(Context paramContext)
  {
    mContext = paramContext;
    initData();
  }
  
  private float clamp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.max(paramFloat2, Math.min(paramFloat1, paramFloat3));
  }
  
  private int createSoundIDFromAsset(String paramString)
  {
    try
    {
      boolean bool = paramString.startsWith("/");
      Object localObject1;
      int i;
      if (bool)
      {
        localObject1 = mSoundPool;
        i = ((SoundPool)localObject1).load(paramString, 0);
      }
      else
      {
        localObject1 = Cocos2dxHelper.getObbFile();
        if (localObject1 != null)
        {
          localObject1 = Cocos2dxHelper.getObbFile().a(paramString);
          paramString = mSoundPool;
        }
        for (;;)
        {
          i = paramString.load((AssetFileDescriptor)localObject1, 0);
          break;
          localObject1 = mSoundPool;
          Object localObject2 = mContext;
          localObject2 = ((Context)localObject2).getAssets().openFd(paramString);
          paramString = (String)localObject1;
          localObject1 = localObject2;
        }
      }
      return i;
    }
    catch (Exception paramString)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("error: ");
      ((StringBuilder)localObject1).append(paramString.getMessage());
      Log.e("Cocos2dxSound", ((StringBuilder)localObject1).toString(), paramString);
      i = -1;
      if (i == 0) {
        return -1;
      }
    }
  }
  
  private int doPlayEffect(String paramString, int paramInt, boolean paramBoolean, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    for (;;)
    {
      try
      {
        float f2 = mLeftVolume;
        float f3 = clamp(paramFloat2, 0.0F, 1.0F);
        float f1 = mRightVolume;
        paramFloat2 = clamp(-paramFloat2, 0.0F, 1.0F);
        paramFloat1 = clamp(paramFloat1 * 1.0F, 0.5F, 2.0F);
        Object localObject1 = mSoundPool;
        f2 = clamp(f2 * paramFloat3 * (1.0F - f3), 0.0F, 1.0F);
        paramFloat2 = clamp(f1 * paramFloat3 * (1.0F - paramFloat2), 0.0F, 1.0F);
        if (paramBoolean)
        {
          i = -1;
          paramInt = ((SoundPool)localObject1).play(paramInt, f2, paramFloat2, 1, i, paramFloat1);
          Object localObject2 = mLockPathStreamIDsMap;
          try
          {
            ArrayList localArrayList = (ArrayList)mPathStreamIDsMap.get(paramString);
            localObject1 = localArrayList;
            if (localArrayList == null)
            {
              localObject1 = new ArrayList();
              mPathStreamIDsMap.put(paramString, localObject1);
            }
            ((ArrayList)localObject1).add(Integer.valueOf(paramInt));
            return paramInt;
          }
          catch (Throwable paramString)
          {
            throw paramString;
          }
        }
        int i = 0;
      }
      catch (Throwable paramString)
      {
        throw paramString;
      }
    }
  }
  
  private void initData()
  {
    SoundPool localSoundPool;
    if (Cocos2dxHelper.getDeviceModel().contains("GT-I9100")) {
      localSoundPool = new SoundPool(3, 3, 5);
    } else {
      localSoundPool = new SoundPool(5, 3, 5);
    }
    mSoundPool = localSoundPool;
    mSoundPool.setOnLoadCompleteListener(new OnLoadCompletedListener());
    mLeftVolume = 0.5F;
    mRightVolume = 0.5F;
  }
  
  private void setEffectsVolumeInternal(float paramFloat1, float paramFloat2)
  {
    Object localObject = mLockPathStreamIDsMap;
    try
    {
      if (!mPathStreamIDsMap.isEmpty())
      {
        Iterator localIterator1 = mPathStreamIDsMap.entrySet().iterator();
        while (localIterator1.hasNext())
        {
          Iterator localIterator2 = ((ArrayList)((Map.Entry)localIterator1.next()).getValue()).iterator();
          while (localIterator2.hasNext())
          {
            int i = ((Integer)localIterator2.next()).intValue();
            mSoundPool.setVolume(i, paramFloat1, paramFloat2);
          }
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public float getEffectsVolume()
  {
    return (mLeftVolume + mRightVolume) / 2.0F;
  }
  
  public void onEnterBackground()
  {
    mSoundPool.autoPause();
  }
  
  public void onEnterForeground()
  {
    mSoundPool.autoResume();
  }
  
  public void pauseAllEffects()
  {
    Object localObject = mLockPathStreamIDsMap;
    try
    {
      if (!mPathStreamIDsMap.isEmpty())
      {
        Iterator localIterator1 = mPathStreamIDsMap.entrySet().iterator();
        while (localIterator1.hasNext())
        {
          Iterator localIterator2 = ((ArrayList)((Map.Entry)localIterator1.next()).getValue()).iterator();
          while (localIterator2.hasNext())
          {
            int i = ((Integer)localIterator2.next()).intValue();
            mSoundPool.pause(i);
          }
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void pauseEffect(int paramInt)
  {
    mSoundPool.pause(paramInt);
  }
  
  public int playEffect(String paramString, boolean paramBoolean, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    Integer localInteger = (Integer)mPathSoundIDMap.get(paramString);
    if (localInteger != null) {
      return doPlayEffect(paramString, localInteger.intValue(), paramBoolean, paramFloat1, paramFloat2, paramFloat3);
    }
    localInteger = Integer.valueOf(preloadEffect(paramString));
    if (localInteger.intValue() == -1) {
      return -1;
    }
    paramString = new a(paramString, paramBoolean, paramFloat1, paramFloat2, paramFloat3);
    mPlayWhenLoadedEffects.putIfAbsent(localInteger, paramString);
    try
    {
      paramString.wait(500L);
    }
    catch (Throwable localThrowable)
    {
      break label128;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    int i = j;
    mPlayWhenLoadedEffects.remove(localThrowable);
    return i;
    label128:
    throw localThrowable;
  }
  
  public void playSound()
  {
    mSoundPool.release();
    Object localObject = mLockPathStreamIDsMap;
    try
    {
      mPathStreamIDsMap.clear();
      mPathSoundIDMap.clear();
      mPlayWhenLoadedEffects.clear();
      mLeftVolume = 0.5F;
      mRightVolume = 0.5F;
      initData();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public int preloadEffect(String paramString)
  {
    Object localObject2 = (Integer)mPathSoundIDMap.get(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      Integer localInteger = Integer.valueOf(createSoundIDFromAsset(paramString));
      localObject2 = localInteger;
      localObject1 = localObject2;
      if (localInteger.intValue() != -1)
      {
        mPathSoundIDMap.put(paramString, localInteger);
        localObject1 = localObject2;
      }
    }
    return localObject1.intValue();
  }
  
  public void resumeAllEffects()
  {
    Object localObject = mLockPathStreamIDsMap;
    try
    {
      if (!mPathStreamIDsMap.isEmpty())
      {
        Iterator localIterator1 = mPathStreamIDsMap.entrySet().iterator();
        while (localIterator1.hasNext())
        {
          Iterator localIterator2 = ((ArrayList)((Map.Entry)localIterator1.next()).getValue()).iterator();
          while (localIterator2.hasNext())
          {
            int i = ((Integer)localIterator2.next()).intValue();
            mSoundPool.resume(i);
          }
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void resumeEffect(int paramInt)
  {
    mSoundPool.resume(paramInt);
  }
  
  void setAudioFocus(boolean paramBoolean)
  {
    mIsAudioFocus = paramBoolean;
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
    setEffectsVolumeInternal(f1, f2);
  }
  
  public void setEffectsVolume(float paramFloat)
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
    if (!mIsAudioFocus) {
      return;
    }
    setEffectsVolumeInternal(mLeftVolume, mRightVolume);
  }
  
  public void stopAllEffects()
  {
    Object localObject = mLockPathStreamIDsMap;
    try
    {
      if (!mPathStreamIDsMap.isEmpty())
      {
        Iterator localIterator1 = mPathStreamIDsMap.entrySet().iterator();
        while (localIterator1.hasNext())
        {
          Iterator localIterator2 = ((ArrayList)((Map.Entry)localIterator1.next()).getValue()).iterator();
          while (localIterator2.hasNext())
          {
            int i = ((Integer)localIterator2.next()).intValue();
            mSoundPool.stop(i);
          }
        }
      }
      mPathStreamIDsMap.clear();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void stopEffect(int paramInt)
  {
    mSoundPool.stop(paramInt);
    Object localObject = mLockPathStreamIDsMap;
    try
    {
      Iterator localIterator = mPathStreamIDsMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (((ArrayList)mPathStreamIDsMap.get(str)).contains(Integer.valueOf(paramInt))) {
          ((ArrayList)mPathStreamIDsMap.get(str)).remove(((ArrayList)mPathStreamIDsMap.get(str)).indexOf(Integer.valueOf(paramInt)));
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void unloadEffect(String paramString)
  {
    Object localObject1 = mLockPathStreamIDsMap;
    try
    {
      Object localObject2 = (ArrayList)mPathStreamIDsMap.get(paramString);
      if (localObject2 != null)
      {
        localObject2 = ((ArrayList)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Integer localInteger = (Integer)((Iterator)localObject2).next();
          mSoundPool.stop(localInteger.intValue());
        }
      }
      mPathStreamIDsMap.remove(paramString);
      localObject1 = (Integer)mPathSoundIDMap.get(paramString);
      if (localObject1 != null)
      {
        mSoundPool.unload(((Integer)localObject1).intValue());
        mPathSoundIDMap.remove(paramString);
        return;
      }
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public class OnLoadCompletedListener
    implements SoundPool.OnLoadCompleteListener
  {
    public OnLoadCompletedListener() {}
    
    public void onLoadComplete(SoundPool paramSoundPool, int paramInt1, int paramInt2)
    {
      if (paramInt2 == 0)
      {
        paramSoundPool = (Cocos2dxSound.a)mPlayWhenLoadedEffects.get(Integer.valueOf(paramInt1));
        if (paramSoundPool != null)
        {
          j = Cocos2dxSound.this.doPlayEffect(this$0, paramInt1, h, k, v, d);
          try
          {
            paramSoundPool.notifyAll();
            return;
          }
          catch (Throwable localThrowable)
          {
            throw localThrowable;
          }
        }
      }
    }
  }
  
  class a
  {
    float d;
    boolean h;
    int j;
    float k;
    String this$0;
    float v;
    
    a(String paramString, boolean paramBoolean, float paramFloat1, float paramFloat2, float paramFloat3)
    {
      this$0 = paramString;
      h = paramBoolean;
      k = paramFloat1;
      v = paramFloat2;
      d = paramFloat3;
      j = -1;
    }
  }
}

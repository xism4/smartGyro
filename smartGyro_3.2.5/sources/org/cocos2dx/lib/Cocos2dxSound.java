package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.SoundPool;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cocos2dxSound {
    private static final int INVALID_SOUND_ID = -1;
    private static final int INVALID_STREAM_ID = -1;
    private static final int LOAD_TIME_OUT = 500;
    private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 5;
    private static final int MAX_SIMULTANEOUS_STREAMS_I9100 = 3;
    private static final int SOUND_PRIORITY = 1;
    private static final int SOUND_QUALITY = 5;
    private static final float SOUND_RATE = 1.0f;
    private static final String TAG = "Cocos2dxSound";
    private final Context mContext;
    private boolean mIsAudioFocus = true;
    private float mLeftVolume;
    private final Object mLockPathStreamIDsMap = new Object();
    private final HashMap<String, Integer> mPathSoundIDMap = new HashMap<>();
    private final HashMap<String, ArrayList<Integer>> mPathStreamIDsMap = new HashMap<>();
    /* access modifiers changed from: private */
    public ConcurrentHashMap<Integer, C0916a> mPlayWhenLoadedEffects = new ConcurrentHashMap<>();
    private float mRightVolume;
    private SoundPool mSoundPool;

    public class OnLoadCompletedListener implements SoundPool.OnLoadCompleteListener {
        public OnLoadCompletedListener() {
        }

        public void onLoadComplete(SoundPool soundPool, int i, int i2) {
            C0916a aVar;
            if (i2 == 0 && (aVar = (C0916a) Cocos2dxSound.this.mPlayWhenLoadedEffects.get(Integer.valueOf(i))) != null) {
                aVar.f2499f = Cocos2dxSound.this.doPlayEffect(aVar.f2498e, i, aVar.f2494a, aVar.f2495b, aVar.f2496c, aVar.f2497d);
                synchronized (aVar) {
                    aVar.notifyAll();
                }
            }
        }
    }

    /* renamed from: org.cocos2dx.lib.Cocos2dxSound$a */
    private class C0916a {

        /* renamed from: a */
        boolean f2494a;

        /* renamed from: b */
        float f2495b;

        /* renamed from: c */
        float f2496c;

        /* renamed from: d */
        float f2497d;

        /* renamed from: e */
        String f2498e;

        /* renamed from: f */
        int f2499f = -1;

        C0916a(String str, boolean z, float f, float f2, float f3) {
            this.f2498e = str;
            this.f2494a = z;
            this.f2495b = f;
            this.f2496c = f2;
            this.f2497d = f3;
        }
    }

    public Cocos2dxSound(Context context) {
        this.mContext = context;
        initData();
    }

    private float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f, f3));
    }

    private int createSoundIDFromAsset(String str) {
        int i;
        SoundPool soundPool;
        AssetFileDescriptor openFd;
        try {
            if (str.startsWith("/")) {
                i = this.mSoundPool.load(str, 0);
            } else {
                if (Cocos2dxHelper.getObbFile() != null) {
                    openFd = Cocos2dxHelper.getObbFile().mo2342b(str);
                    soundPool = this.mSoundPool;
                } else {
                    soundPool = this.mSoundPool;
                    openFd = this.mContext.getAssets().openFd(str);
                }
                i = soundPool.load(openFd, 0);
            }
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            i = -1;
        }
        if (i == 0) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public synchronized int doPlayEffect(String str, int i, boolean z, float f, float f2, float f3) {
        int play;
        String str2 = str;
        float f4 = f2;
        synchronized (this) {
            float clamp = this.mLeftVolume * f3 * (SOUND_RATE - clamp(f4, 0.0f, SOUND_RATE));
            float clamp2 = this.mRightVolume * f3 * (SOUND_RATE - clamp(-f4, 0.0f, SOUND_RATE));
            play = this.mSoundPool.play(i, clamp(clamp, 0.0f, SOUND_RATE), clamp(clamp2, 0.0f, SOUND_RATE), 1, z ? -1 : 0, clamp(f * SOUND_RATE, 0.5f, 2.0f));
            synchronized (this.mLockPathStreamIDsMap) {
                ArrayList arrayList = this.mPathStreamIDsMap.get(str2);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.mPathStreamIDsMap.put(str2, arrayList);
                }
                arrayList.add(Integer.valueOf(play));
            }
        }
        return play;
    }

    private void initData() {
        this.mSoundPool = Cocos2dxHelper.getDeviceModel().contains("GT-I9100") ? new SoundPool(3, 3, 5) : new SoundPool(5, 3, 5);
        this.mSoundPool.setOnLoadCompleteListener(new OnLoadCompletedListener());
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
    }

    private void setEffectsVolumeInternal(float f, float f2) {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                for (Map.Entry<String, ArrayList<Integer>> value : this.mPathStreamIDsMap.entrySet()) {
                    Iterator it = ((ArrayList) value.getValue()).iterator();
                    while (it.hasNext()) {
                        this.mSoundPool.setVolume(((Integer) it.next()).intValue(), f, f2);
                    }
                }
            }
        }
    }

    public void end() {
        this.mSoundPool.release();
        synchronized (this.mLockPathStreamIDsMap) {
            this.mPathStreamIDsMap.clear();
        }
        this.mPathSoundIDMap.clear();
        this.mPlayWhenLoadedEffects.clear();
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        initData();
    }

    public float getEffectsVolume() {
        return (this.mLeftVolume + this.mRightVolume) / 2.0f;
    }

    public void onEnterBackground() {
        this.mSoundPool.autoPause();
    }

    public void onEnterForeground() {
        this.mSoundPool.autoResume();
    }

    public void pauseAllEffects() {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                for (Map.Entry<String, ArrayList<Integer>> value : this.mPathStreamIDsMap.entrySet()) {
                    Iterator it = ((ArrayList) value.getValue()).iterator();
                    while (it.hasNext()) {
                        this.mSoundPool.pause(((Integer) it.next()).intValue());
                    }
                }
            }
        }
    }

    public void pauseEffect(int i) {
        this.mSoundPool.pause(i);
    }

    public int playEffect(String str, boolean z, float f, float f2, float f3) {
        Integer num = this.mPathSoundIDMap.get(str);
        if (num != null) {
            return doPlayEffect(str, num.intValue(), z, f, f2, f3);
        }
        Integer valueOf = Integer.valueOf(preloadEffect(str));
        if (valueOf.intValue() == -1) {
            return -1;
        }
        C0916a aVar = new C0916a(str, z, f, f2, f3);
        this.mPlayWhenLoadedEffects.putIfAbsent(valueOf, aVar);
        synchronized (aVar) {
            try {
                aVar.wait(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int i = aVar.f2499f;
        this.mPlayWhenLoadedEffects.remove(valueOf);
        return i;
    }

    public int preloadEffect(String str) {
        Integer num = this.mPathSoundIDMap.get(str);
        if (num == null) {
            num = Integer.valueOf(createSoundIDFromAsset(str));
            if (num.intValue() != -1) {
                this.mPathSoundIDMap.put(str, num);
            }
        }
        return num.intValue();
    }

    public void resumeAllEffects() {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                for (Map.Entry<String, ArrayList<Integer>> value : this.mPathStreamIDsMap.entrySet()) {
                    Iterator it = ((ArrayList) value.getValue()).iterator();
                    while (it.hasNext()) {
                        this.mSoundPool.resume(((Integer) it.next()).intValue());
                    }
                }
            }
        }
    }

    public void resumeEffect(int i) {
        this.mSoundPool.resume(i);
    }

    /* access modifiers changed from: package-private */
    public void setAudioFocus(boolean z) {
        this.mIsAudioFocus = z;
        float f = 0.0f;
        float f2 = this.mIsAudioFocus ? this.mLeftVolume : 0.0f;
        if (this.mIsAudioFocus) {
            f = this.mRightVolume;
        }
        setEffectsVolumeInternal(f2, f);
    }

    public void setEffectsVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > SOUND_RATE) {
            f = SOUND_RATE;
        }
        this.mRightVolume = f;
        this.mLeftVolume = f;
        if (this.mIsAudioFocus) {
            setEffectsVolumeInternal(this.mLeftVolume, this.mRightVolume);
        }
    }

    public void stopAllEffects() {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                for (Map.Entry<String, ArrayList<Integer>> value : this.mPathStreamIDsMap.entrySet()) {
                    Iterator it = ((ArrayList) value.getValue()).iterator();
                    while (it.hasNext()) {
                        this.mSoundPool.stop(((Integer) it.next()).intValue());
                    }
                }
            }
            this.mPathStreamIDsMap.clear();
        }
    }

    public void stopEffect(int i) {
        this.mSoundPool.stop(i);
        synchronized (this.mLockPathStreamIDsMap) {
            Iterator<String> it = this.mPathStreamIDsMap.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (this.mPathStreamIDsMap.get(next).contains(Integer.valueOf(i))) {
                    this.mPathStreamIDsMap.get(next).remove(this.mPathStreamIDsMap.get(next).indexOf(Integer.valueOf(i)));
                    break;
                }
            }
        }
    }

    public void unloadEffect(String str) {
        synchronized (this.mLockPathStreamIDsMap) {
            ArrayList arrayList = this.mPathStreamIDsMap.get(str);
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.mSoundPool.stop(((Integer) it.next()).intValue());
                }
            }
            this.mPathStreamIDsMap.remove(str);
        }
        Integer num = this.mPathSoundIDMap.get(str);
        if (num != null) {
            this.mSoundPool.unload(num.intValue());
            this.mPathSoundIDMap.remove(str);
        }
    }
}

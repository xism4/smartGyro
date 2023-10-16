package org.cocos2dx.package_1;

import android.content.Context;
import android.media.SoundPool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.cocos2dx.lib.Cocos2dxSound;

public class Cocos2dxSound {
    private static final int INVALID_SOUND_ID = -1;
    private static final int INVALID_STREAM_ID = -1;
    private static final int LOAD_TIME_OUT = 500;
    private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 5;
    private static final int MAX_SIMULTANEOUS_STREAMS_I9100 = 3;
    private static final String PAGE_KEY = "Cocos2dxSound";
    private static final int SOUND_PRIORITY = 1;
    private static final int SOUND_QUALITY = 5;
    private static final float SOUND_RATE = 1.0f;
    private final Context mContext;
    private boolean mIsAudioFocus = true;
    private float mLeftVolume;
    private final Object mLockPathStreamIDsMap = new Object();
    private final HashMap<String, Integer> mPathSoundIDMap = new HashMap();
    private final HashMap<String, ArrayList<Integer>> mPathStreamIDsMap = new HashMap();
    /* access modifiers changed from: private */
    public ConcurrentHashMap<Integer, Cocos2dxSound.a> mPlayWhenLoadedEffects = new ConcurrentHashMap();
    private float mRightVolume;
    private SoundPool mSoundPool;

    public class OnLoadCompletedListener implements SoundPool.OnLoadCompleteListener {
        public OnLoadCompletedListener() {
        }

        public void onLoadComplete(SoundPool soundPool, int i, int i2) {
            a $r7;
            if (i2 == 0 && ($r7 = (a) Cocos2dxSound.this.mPlayWhenLoadedEffects.get(Integer.valueOf(i))) != null) {
                $r7.j = Cocos2dxSound.this.doPlayEffect($r7.this$0, i, $r7.h, $r7.k, $r7.v, $r7.d);
                synchronized ($r7) {
                    $r7.notifyAll();
                }
            }
        }
    }

    class a {
        float d;
        boolean h;
        int j = -1;
        float k;
        String this$0;
        float v;

        a(String str, boolean z, float f, float f2, float f3) {
            this.this$0 = str;
            this.h = z;
            this.k = f;
            this.v = f2;
            this.d = f3;
        }
    }

    public Cocos2dxSound(Context context) {
        this.mContext = context;
        initData();
    }

    private float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f, f3));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0051 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0053 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int createSoundIDFromAsset(java.lang.String r12) {
        /*
            r11 = this;
            java.lang.String r1 = "/"
            boolean r0 = r12.startsWith(r1)     // Catch:{ Exception -> 0x0033 }
            if (r0 == 0) goto L_0x0010
            android.media.SoundPool r2 = r11.mSoundPool
            r4 = 0
            int r3 = r2.load(r12, r4)     // Catch:{ Exception -> 0x0033 }
            goto L_0x004f
        L_0x0010:
            lombok.libs.org.objectweb.asm.ByteVector r5 = org.cocos2dx.package_1.Cocos2dxHelper.getObbFile()     // Catch:{ Exception -> 0x0033 }
            if (r5 == 0) goto L_0x0026
            lombok.libs.org.objectweb.asm.ByteVector r5 = org.cocos2dx.package_1.Cocos2dxHelper.getObbFile()     // Catch:{ Exception -> 0x0033 }
            android.content.res.AssetFileDescriptor r6 = r5.a(r12)     // Catch:{ Exception -> 0x0033 }
            android.media.SoundPool r2 = r11.mSoundPool
        L_0x0020:
            r4 = 0
            int r3 = r2.load(r6, r4)     // Catch:{ Exception -> 0x0033 }
            goto L_0x004f
        L_0x0026:
            android.media.SoundPool r2 = r11.mSoundPool
            android.content.Context r7 = r11.mContext
            android.content.res.AssetManager r8 = r7.getAssets()     // Catch:{ Exception -> 0x0033 }
            android.content.res.AssetFileDescriptor r6 = r8.openFd(r12)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0020
        L_0x0033:
            r9 = move-exception
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r1 = "error: "
            r10.append(r1)
            java.lang.String r12 = r9.getMessage()
            r10.append(r12)
            java.lang.String r12 = r10.toString()
            java.lang.String r1 = "Cocos2dxSound"
            android.util.Log.e(r1, r12, r9)
            r3 = -1
        L_0x004f:
            if (r3 != 0) goto L_0x0053
            r4 = -1
            return r4
        L_0x0053:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.package_1.Cocos2dxSound.createSoundIDFromAsset(java.lang.String):int");
    }

    /* access modifiers changed from: private */
    public synchronized int doPlayEffect(String str, int i, boolean z, float $f0, float $f1, float f) {
        int $i0;
        float $f3 = this.mLeftVolume * f * (SOUND_RATE - clamp($f1, 0.0f, SOUND_RATE));
        float $f12 = this.mRightVolume * f * (SOUND_RATE - clamp(-$f1, 0.0f, SOUND_RATE));
        $i0 = this.mSoundPool.play(i, clamp($f3, 0.0f, SOUND_RATE), clamp($f12, 0.0f, SOUND_RATE), 1, z ? (byte) -1 : 0, clamp($f0 * SOUND_RATE, 0.5f, 2.0f));
        synchronized (this.mLockPathStreamIDsMap) {
            ArrayList $r6 = this.mPathStreamIDsMap.get(str);
            if ($r6 == null) {
                $r6 = new ArrayList();
                this.mPathStreamIDsMap.put(str, $r6);
            }
            $r6.add(Integer.valueOf($i0));
        }
        return $i0;
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
                for (Map.Entry<String, ArrayList<Integer>> $r7 : this.mPathStreamIDsMap.entrySet()) {
                    Iterator $r9 = ((ArrayList) $r7.getValue()).iterator();
                    while ($r9.hasNext()) {
                        int $i0 = ((Integer) $r9.next()).intValue();
                        SoundPool $r2 = this.mSoundPool;
                        SoundPool soundPool = $r2;
                        $r2.setVolume($i0, f, f2);
                    }
                }
            }
        }
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
                for (Map.Entry<String, ArrayList<Integer>> $r7 : this.mPathStreamIDsMap.entrySet()) {
                    Iterator $r9 = ((ArrayList) $r7.getValue()).iterator();
                    while ($r9.hasNext()) {
                        this.mSoundPool.pause(((Integer) $r9.next()).intValue());
                    }
                }
            }
        }
    }

    public void pauseEffect(int i) {
        this.mSoundPool.pause(i);
    }

    public int playEffect(String str, boolean z, float f, float f2, float f3) {
        Integer $r4 = this.mPathSoundIDMap.get(str);
        if ($r4 != null) {
            return doPlayEffect(str, $r4.intValue(), z, f, f2, f3);
        }
        Integer $r42 = Integer.valueOf(preloadEffect(str));
        if ($r42.intValue() == -1) {
            return -1;
        }
        a aVar = new a(str, z, f, f2, f3);
        this.mPlayWhenLoadedEffects.putIfAbsent($r42, aVar);
        synchronized (aVar) {
            try {
                aVar.wait(500);
            } catch (Exception $r8) {
                $r8.printStackTrace();
            }
        }
        int $i0 = aVar.j;
        this.mPlayWhenLoadedEffects.remove($r42);
        return $i0;
    }

    public void playSound() {
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

    public int preloadEffect(String str) {
        Integer $r4 = this.mPathSoundIDMap.get(str);
        if ($r4 == null) {
            Integer $r5 = Integer.valueOf(createSoundIDFromAsset(str));
            $r4 = $r5;
            if ($r5.intValue() != -1) {
                this.mPathSoundIDMap.put(str, $r5);
            }
        }
        return $r4.intValue();
    }

    public void resumeAllEffects() {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                for (Map.Entry<String, ArrayList<Integer>> $r7 : this.mPathStreamIDsMap.entrySet()) {
                    Iterator $r9 = ((ArrayList) $r7.getValue()).iterator();
                    while ($r9.hasNext()) {
                        this.mSoundPool.resume(((Integer) $r9.next()).intValue());
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
        float $f0 = 0.0f;
        float $f1 = this.mIsAudioFocus ? this.mLeftVolume : 0.0f;
        if (this.mIsAudioFocus) {
            $f0 = this.mRightVolume;
        }
        setEffectsVolumeInternal($f1, $f0);
    }

    public void setEffectsVolume(float $f0) {
        if ($f0 < 0.0f) {
            $f0 = 0.0f;
        }
        if ($f0 > SOUND_RATE) {
            $f0 = SOUND_RATE;
        }
        this.mRightVolume = $f0;
        this.mLeftVolume = $f0;
        if (this.mIsAudioFocus) {
            setEffectsVolumeInternal(this.mLeftVolume, this.mRightVolume);
        }
    }

    public void stopAllEffects() {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                for (Map.Entry<String, ArrayList<Integer>> $r7 : this.mPathStreamIDsMap.entrySet()) {
                    Iterator $r9 = ((ArrayList) $r7.getValue()).iterator();
                    while ($r9.hasNext()) {
                        this.mSoundPool.stop(((Integer) $r9.next()).intValue());
                    }
                }
            }
            this.mPathStreamIDsMap.clear();
        }
    }

    public void stopEffect(int i) {
        this.mSoundPool.stop(i);
        synchronized (this.mLockPathStreamIDsMap) {
            Iterator $r5 = this.mPathStreamIDsMap.keySet().iterator();
            while (true) {
                if (!$r5.hasNext()) {
                    break;
                }
                String $r7 = $r5.next();
                if (this.mPathStreamIDsMap.get($r7).contains(Integer.valueOf(i))) {
                    this.mPathStreamIDsMap.get($r7).remove(this.mPathStreamIDsMap.get($r7).indexOf(Integer.valueOf(i)));
                    break;
                }
            }
        }
    }

    public void unloadEffect(String str) {
        synchronized (this.mLockPathStreamIDsMap) {
            ArrayList $r6 = this.mPathStreamIDsMap.get(str);
            if ($r6 != null) {
                Iterator $r7 = $r6.iterator();
                while ($r7.hasNext()) {
                    this.mSoundPool.stop(((Integer) $r7.next()).intValue());
                }
            }
            this.mPathStreamIDsMap.remove(str);
        }
        Integer $r8 = this.mPathSoundIDMap.get(str);
        if ($r8 != null) {
            this.mSoundPool.unload($r8.intValue());
            this.mPathSoundIDMap.remove(str);
        }
    }
}

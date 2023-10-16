package org.cocos2dx.package_1;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileInputStream;

public class Cocos2dxMusic {
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

    public Cocos2dxMusic(Context context) {
        this.mContext = context;
        initData();
    }

    private MediaPlayer createMediaPlayer(String str) {
        FileDescriptor $r4;
        long $l0;
        long $l1;
        MediaPlayer $r2 = new MediaPlayer();
        try {
            if (str.startsWith("/")) {
                FileInputStream $r3 = new FileInputStream(str);
                $r2.setDataSource($r3.getFD());
                $r3.close();
            } else {
                if (Cocos2dxHelper.getObbFile() != null) {
                    AssetFileDescriptor $r6 = Cocos2dxHelper.getObbFile().a(str);
                    $r4 = $r6.getFileDescriptor();
                    $l0 = $r6.getStartOffset();
                    $l1 = $r6.getLength();
                } else {
                    Context $r7 = this.mContext;
                    Context context = $r7;
                    AssetFileDescriptor $r62 = $r7.getAssets().openFd(str);
                    $r4 = $r62.getFileDescriptor();
                    $l0 = $r62.getStartOffset();
                    $l1 = $r62.getLength();
                }
                $r2.setDataSource($r4, $l0, $l1);
            }
            $r2.prepare();
            $r2.setVolume(this.mLeftVolume, this.mRightVolume);
            return $r2;
        } catch (Exception $r9) {
            String $r1 = Debug;
            Log.e($r1, "error: " + $r9.getMessage(), $r9);
            return null;
        }
    }

    private void initData() {
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        this.mBackgroundMediaPlayer = null;
        this.mPaused = false;
        this.mCurrentPath = null;
    }

    public void fullReset() {
        MediaPlayer $r1 = this.mBackgroundMediaPlayer;
        if ($r1 != null) {
            $r1.release();
        }
        initData();
    }

    public float getBackgroundVolume() {
        if (this.mBackgroundMediaPlayer != null) {
            return (this.mLeftVolume + this.mRightVolume) / 2.0f;
        }
        return 0.0f;
    }

    public boolean isBackgroundMusicPlaying() {
        if (this.mBackgroundMediaPlayer == null) {
            return false;
        }
        try {
            return this.mBackgroundMediaPlayer.isPlaying();
        } catch (IllegalStateException e) {
            Log.e(Debug, "isBackgroundMusicPlaying, IllegalStateException was triggered!");
            return false;
        }
    }

    public void onEnterBackground() {
        if (this.mBackgroundMediaPlayer != null) {
            try {
                if (this.mBackgroundMediaPlayer.isPlaying()) {
                    this.mBackgroundMediaPlayer.pause();
                    this.mPaused = true;
                }
            } catch (IllegalStateException e) {
                Log.e(Debug, "onEnterBackground, IllegalStateException was triggered!");
            }
        }
    }

    public void onEnterForeground() {
        if (!this.mManualPaused && this.mBackgroundMediaPlayer != null && this.mPaused) {
            try {
                this.mBackgroundMediaPlayer.start();
                this.mPaused = false;
            } catch (IllegalStateException e) {
                Log.e(Debug, "onEnterForeground, IllegalStateException was triggered!");
            }
        }
    }

    public void pauseBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            try {
                if (this.mBackgroundMediaPlayer.isPlaying()) {
                    this.mBackgroundMediaPlayer.pause();
                    this.mPaused = true;
                    this.mManualPaused = true;
                }
            } catch (IllegalStateException e) {
                Log.e(Debug, "pauseBackgroundMusic, IllegalStateException was triggered!");
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void playBackgroundMusic(java.lang.String r6, boolean r7) {
        /*
            r5 = this;
            java.lang.String r0 = r5.mCurrentPath
            if (r0 != 0) goto L_0x000d
        L_0x0004:
            android.media.MediaPlayer r1 = r5.createMediaPlayer(r6)
            r5.mBackgroundMediaPlayer = r1
            r5.mCurrentPath = r6
            goto L_0x001b
        L_0x000d:
            boolean r2 = r0.equals(r6)
            if (r2 != 0) goto L_0x001b
            android.media.MediaPlayer r1 = r5.mBackgroundMediaPlayer
            if (r1 == 0) goto L_0x0004
            r1.release()
            goto L_0x0004
        L_0x001b:
            android.media.MediaPlayer r1 = r5.mBackgroundMediaPlayer
            if (r1 != 0) goto L_0x0027
            java.lang.String r6 = Debug
            java.lang.String r0 = "playBackgroundMusic: background media player is null"
        L_0x0023:
            android.util.Log.e(r6, r0)
            return
        L_0x0027:
            boolean r2 = r5.mPaused
            if (r2 == 0) goto L_0x0035
            r3 = 0
            r1.seekTo(r3)     // Catch:{ Exception -> 0x0051 }
        L_0x002f:
            android.media.MediaPlayer r1 = r5.mBackgroundMediaPlayer
            r1.start()     // Catch:{ Exception -> 0x0051 }
            goto L_0x0041
        L_0x0035:
            boolean r2 = r1.isPlaying()     // Catch:{ Exception -> 0x0051 }
            if (r2 == 0) goto L_0x002f
            android.media.MediaPlayer r1 = r5.mBackgroundMediaPlayer
            r3 = 0
            r1.seekTo(r3)     // Catch:{ Exception -> 0x0051 }
        L_0x0041:
            android.media.MediaPlayer r1 = r5.mBackgroundMediaPlayer
            r1.setLooping(r7)     // Catch:{ Exception -> 0x0051 }
            r3 = 0
            r5.mPaused = r3
            r5.mIsLoop = r7
            return
        L_0x004c:
            java.lang.String r6 = Debug
            java.lang.String r0 = "playBackgroundMusic: error state"
            goto L_0x0023
        L_0x0051:
            r4 = move-exception
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.package_1.Cocos2dxMusic.playBackgroundMusic(java.lang.String, boolean):void");
    }

    public void preloadBackgroundMusic(String str) {
        String $r2 = this.mCurrentPath;
        if ($r2 == null || !$r2.equals(str)) {
            MediaPlayer $r3 = this.mBackgroundMediaPlayer;
            if ($r3 != null) {
                $r3.release();
            }
            this.mBackgroundMediaPlayer = createMediaPlayer(str);
            this.mCurrentPath = str;
        }
    }

    public void resumeBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null && this.mPaused) {
            try {
                this.mBackgroundMediaPlayer.start();
                this.mPaused = false;
                this.mManualPaused = false;
            } catch (IllegalStateException e) {
                Log.e(Debug, "resumeBackgroundMusic, IllegalStateException was triggered!");
            }
        }
    }

    public void rewindBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            playBackgroundMusic(this.mCurrentPath, this.mIsLoop);
        }
    }

    /* access modifiers changed from: package-private */
    public void setAudioFocus(boolean z) {
        this.mIsAudioFocus = z;
        if (this.mBackgroundMediaPlayer != null) {
            float $f0 = 0.0f;
            float $f1 = this.mIsAudioFocus ? this.mLeftVolume : 0.0f;
            if (this.mIsAudioFocus) {
                $f0 = this.mRightVolume;
            }
            this.mBackgroundMediaPlayer.setVolume($f1, $f0);
        }
    }

    public void setBackgroundVolume(float $f0) {
        if ($f0 < 0.0f) {
            $f0 = 0.0f;
        }
        if ($f0 > 1.0f) {
            $f0 = 1.0f;
        }
        this.mRightVolume = $f0;
        this.mLeftVolume = $f0;
        MediaPlayer $r1 = this.mBackgroundMediaPlayer;
        if ($r1 != null && this.mIsAudioFocus) {
            $r1.setVolume(this.mLeftVolume, this.mRightVolume);
        }
    }

    public void stopBackgroundMusic() {
        MediaPlayer $r1 = this.mBackgroundMediaPlayer;
        if ($r1 != null) {
            $r1.release();
            this.mBackgroundMediaPlayer = createMediaPlayer(this.mCurrentPath);
            this.mPaused = false;
        }
    }

    public boolean willPlayBackgroundMusic() {
        return !((AudioManager) this.mContext.getSystemService("audio")).isMusicActive();
    }
}

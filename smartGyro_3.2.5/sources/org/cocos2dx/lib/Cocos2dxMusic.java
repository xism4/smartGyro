package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileInputStream;

public class Cocos2dxMusic {
    private static final String TAG = "Cocos2dxMusic";
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
        FileDescriptor fileDescriptor;
        long startOffset;
        long length;
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            if (str.startsWith("/")) {
                FileInputStream fileInputStream = new FileInputStream(str);
                mediaPlayer.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
            } else {
                if (Cocos2dxHelper.getObbFile() != null) {
                    AssetFileDescriptor b = Cocos2dxHelper.getObbFile().mo2342b(str);
                    fileDescriptor = b.getFileDescriptor();
                    startOffset = b.getStartOffset();
                    length = b.getLength();
                } else {
                    AssetFileDescriptor openFd = this.mContext.getAssets().openFd(str);
                    fileDescriptor = openFd.getFileDescriptor();
                    startOffset = openFd.getStartOffset();
                    length = openFd.getLength();
                }
                mediaPlayer.setDataSource(fileDescriptor, startOffset, length);
            }
            mediaPlayer.prepare();
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
            return mediaPlayer;
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "error: " + e.getMessage(), e);
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

    public void end() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
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
        try {
            if (this.mBackgroundMediaPlayer == null) {
                return false;
            }
            return this.mBackgroundMediaPlayer.isPlaying();
        } catch (IllegalStateException unused) {
            Log.e(TAG, "isBackgroundMusicPlaying, IllegalStateException was triggered!");
            return false;
        }
    }

    public void onEnterBackground() {
        try {
            if (this.mBackgroundMediaPlayer != null && this.mBackgroundMediaPlayer.isPlaying()) {
                this.mBackgroundMediaPlayer.pause();
                this.mPaused = true;
            }
        } catch (IllegalStateException unused) {
            Log.e(TAG, "onEnterBackground, IllegalStateException was triggered!");
        }
    }

    public void onEnterForeground() {
        try {
            if (!this.mManualPaused && this.mBackgroundMediaPlayer != null && this.mPaused) {
                this.mBackgroundMediaPlayer.start();
                this.mPaused = false;
            }
        } catch (IllegalStateException unused) {
            Log.e(TAG, "onEnterForeground, IllegalStateException was triggered!");
        }
    }

    public void pauseBackgroundMusic() {
        try {
            if (this.mBackgroundMediaPlayer != null && this.mBackgroundMediaPlayer.isPlaying()) {
                this.mBackgroundMediaPlayer.pause();
                this.mPaused = true;
                this.mManualPaused = true;
            }
        } catch (IllegalStateException unused) {
            Log.e(TAG, "pauseBackgroundMusic, IllegalStateException was triggered!");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027 A[SYNTHETIC, Splitter:B:12:0x0027] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void playBackgroundMusic(java.lang.String r3, boolean r4) {
        /*
            r2 = this;
            java.lang.String r0 = r2.mCurrentPath
            if (r0 != 0) goto L_0x000d
        L_0x0004:
            android.media.MediaPlayer r0 = r2.createMediaPlayer(r3)
            r2.mBackgroundMediaPlayer = r0
            r2.mCurrentPath = r3
            goto L_0x001b
        L_0x000d:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x001b
            android.media.MediaPlayer r0 = r2.mBackgroundMediaPlayer
            if (r0 == 0) goto L_0x0004
            r0.release()
            goto L_0x0004
        L_0x001b:
            android.media.MediaPlayer r3 = r2.mBackgroundMediaPlayer
            if (r3 != 0) goto L_0x0027
            java.lang.String r3 = TAG
            java.lang.String r4 = "playBackgroundMusic: background media player is null"
        L_0x0023:
            android.util.Log.e(r3, r4)
            goto L_0x004f
        L_0x0027:
            boolean r0 = r2.mPaused     // Catch:{ Exception -> 0x004a }
            r1 = 0
            if (r0 == 0) goto L_0x0035
            r3.seekTo(r1)     // Catch:{ Exception -> 0x004a }
        L_0x002f:
            android.media.MediaPlayer r3 = r2.mBackgroundMediaPlayer     // Catch:{ Exception -> 0x004a }
            r3.start()     // Catch:{ Exception -> 0x004a }
            goto L_0x0040
        L_0x0035:
            boolean r3 = r3.isPlaying()     // Catch:{ Exception -> 0x004a }
            if (r3 == 0) goto L_0x002f
            android.media.MediaPlayer r3 = r2.mBackgroundMediaPlayer     // Catch:{ Exception -> 0x004a }
            r3.seekTo(r1)     // Catch:{ Exception -> 0x004a }
        L_0x0040:
            android.media.MediaPlayer r3 = r2.mBackgroundMediaPlayer     // Catch:{ Exception -> 0x004a }
            r3.setLooping(r4)     // Catch:{ Exception -> 0x004a }
            r2.mPaused = r1     // Catch:{ Exception -> 0x004a }
            r2.mIsLoop = r4     // Catch:{ Exception -> 0x004a }
            goto L_0x004f
        L_0x004a:
            java.lang.String r3 = TAG
            java.lang.String r4 = "playBackgroundMusic: error state"
            goto L_0x0023
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.Cocos2dxMusic.playBackgroundMusic(java.lang.String, boolean):void");
    }

    public void preloadBackgroundMusic(String str) {
        String str2 = this.mCurrentPath;
        if (str2 == null || !str2.equals(str)) {
            MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaPlayer(str);
            this.mCurrentPath = str;
        }
    }

    public void resumeBackgroundMusic() {
        try {
            if (this.mBackgroundMediaPlayer != null && this.mPaused) {
                this.mBackgroundMediaPlayer.start();
                this.mPaused = false;
                this.mManualPaused = false;
            }
        } catch (IllegalStateException unused) {
            Log.e(TAG, "resumeBackgroundMusic, IllegalStateException was triggered!");
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
            float f = 0.0f;
            float f2 = this.mIsAudioFocus ? this.mLeftVolume : 0.0f;
            if (this.mIsAudioFocus) {
                f = this.mRightVolume;
            }
            this.mBackgroundMediaPlayer.setVolume(f2, f);
        }
    }

    public void setBackgroundVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.mRightVolume = f;
        this.mLeftVolume = f;
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null && this.mIsAudioFocus) {
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        }
    }

    public void stopBackgroundMusic() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mBackgroundMediaPlayer = createMediaPlayer(this.mCurrentPath);
            this.mPaused = false;
        }
    }

    public boolean willPlayBackgroundMusic() {
        return !((AudioManager) this.mContext.getSystemService("audio")).isMusicActive();
    }
}

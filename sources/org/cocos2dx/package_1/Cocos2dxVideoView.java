package org.cocos2dx.package_1;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import java.io.IOException;
import java.util.Map;

public class Cocos2dxVideoView extends SurfaceView implements MediaController.MediaPlayerControl {
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
    /* access modifiers changed from: private */
    public String TAG = "Cocos2dxVideoView";
    protected float _playSpeed = 1.0f;
    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new DownloadServiceImpl$2(this);
    protected Cocos2dxActivity mCocos2dxActivity = null;
    private MediaPlayer.OnCompletionListener mCompletionListener = new AudioClip$1(this);
    /* access modifiers changed from: private */
    public int mCurrentBufferPercentage;
    /* access modifiers changed from: private */
    public int mCurrentState = 0;
    private int mDuration;
    private MediaPlayer.OnErrorListener mErrorListener = new AudioPlayer(this);
    protected boolean mFullScreenEnabled = false;
    protected int mFullScreenHeight = 0;
    protected int mFullScreenWidth = 0;
    private boolean mIsAssetRouse = false;
    private boolean mKeepRatio = false;
    /* access modifiers changed from: private */
    public MediaPlayer mMediaPlayer = null;
    private boolean mNeedResume = false;
    /* access modifiers changed from: private */
    public MediaPlayer.OnErrorListener mOnErrorListener;
    /* access modifiers changed from: private */
    public MediaPlayer.OnPreparedListener mOnPreparedListener;
    /* access modifiers changed from: private */
    public OnVideoEventListener mOnVideoEventListener;
    MediaPlayer.OnPreparedListener mPreparedListener = new MainActivity$8(this);
    SurfaceHolder.Callback mSHCallback = new CameraPreview$1(this);
    /* access modifiers changed from: private */
    public int mSeekWhenPrepared;
    protected MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener = new AllInOneActivity$3(this);
    /* access modifiers changed from: private */
    public SurfaceHolder mSurfaceHolder = null;
    /* access modifiers changed from: private */
    public int mTargetState = 0;
    private String mVideoFilePath = null;
    /* access modifiers changed from: private */
    public int mVideoHeight = 0;
    private Uri mVideoUri;
    /* access modifiers changed from: private */
    public int mVideoWidth = 0;
    protected int mViewHeight = 0;
    protected int mViewLeft = 0;
    /* access modifiers changed from: private */
    public int mViewTag = 0;
    protected int mViewTop = 0;
    protected int mViewWidth = 0;
    protected int mVisibleHeight = 0;
    protected int mVisibleLeft = 0;
    protected int mVisibleTop = 0;
    protected int mVisibleWidth = 0;

    public interface OnVideoEventListener {
        void onVideoEvent(int i, int i2);
    }

    public Cocos2dxVideoView(Cocos2dxActivity cocos2dxActivity, int i) {
        super(cocos2dxActivity);
        this.mViewTag = i;
        this.mCocos2dxActivity = cocos2dxActivity;
        initVideoView();
    }

    private void initVideoView() {
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        getHolder().addCallback(this.mSHCallback);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    /* access modifiers changed from: private */
    public void openVideo() {
        String $r2;
        StringBuilder $r16;
        if (this.mSurfaceHolder != null) {
            if (this.mIsAssetRouse) {
                if (this.mVideoFilePath == null) {
                    return;
                }
            } else if (this.mVideoUri == null) {
                return;
            }
            Intent $r4 = new Intent("com.android.music.musicservicecommand");
            $r4.putExtra("command", "pause");
            this.mCocos2dxActivity.sendBroadcast($r4);
            release(false);
            try {
                this.mMediaPlayer = new MediaPlayer();
                MediaPlayer $r6 = this.mMediaPlayer;
                MediaPlayer.OnPreparedListener $r7 = this.mPreparedListener;
                MediaPlayer.OnPreparedListener onPreparedListener = $r7;
                $r6.setOnPreparedListener($r7);
                MediaPlayer $r62 = this.mMediaPlayer;
                MediaPlayer.OnVideoSizeChangedListener $r8 = this.mSizeChangedListener;
                MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = $r8;
                $r62.setOnVideoSizeChangedListener($r8);
                MediaPlayer $r63 = this.mMediaPlayer;
                MediaPlayer.OnCompletionListener $r9 = this.mCompletionListener;
                MediaPlayer.OnCompletionListener onCompletionListener = $r9;
                $r63.setOnCompletionListener($r9);
                MediaPlayer $r64 = this.mMediaPlayer;
                MediaPlayer.OnErrorListener $r10 = this.mErrorListener;
                MediaPlayer.OnErrorListener onErrorListener = $r10;
                $r64.setOnErrorListener($r10);
                MediaPlayer $r65 = this.mMediaPlayer;
                MediaPlayer.OnBufferingUpdateListener $r11 = this.mBufferingUpdateListener;
                MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = $r11;
                $r65.setOnBufferingUpdateListener($r11);
                this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                this.mDuration = STATE_ERROR;
                this.mCurrentBufferPercentage = 0;
                if (this.mIsAssetRouse) {
                    AssetFileDescriptor $r13 = this.mCocos2dxActivity.getAssets().openFd(this.mVideoFilePath);
                    MediaPlayer $r66 = this.mMediaPlayer;
                    $r66.setDataSource($r13.getFileDescriptor(), $r13.getStartOffset(), $r13.getLength());
                } else {
                    this.mMediaPlayer.setDataSource(this.mCocos2dxActivity, this.mVideoUri);
                }
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = 1;
                return;
            } catch (IOException e) {
                $r15 = e;
                $r2 = this.TAG;
                $r16 = new StringBuilder();
            } catch (IllegalArgumentException e2) {
                $r15 = e2;
                $r2 = this.TAG;
                $r16 = new StringBuilder();
            }
        } else {
            return;
        }
        $r16.append("Unable to open content: ");
        $r16.append(this.mVideoUri);
        Log.w($r2, $r16.toString(), $r15);
        this.mCurrentState = STATE_ERROR;
        this.mTargetState = STATE_ERROR;
        this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
    }

    /* access modifiers changed from: private */
    public void release(boolean z) {
        MediaPlayer $r1 = this.mMediaPlayer;
        if ($r1 != null) {
            $r1.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
        }
    }

    private void setVideoURI(Uri uri, Map map) {
        this.mVideoUri = uri;
        this.mSeekWhenPrepared = 0;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public void fixSize() {
        if (this.mFullScreenEnabled) {
            fixSize(0, 0, this.mFullScreenWidth, this.mFullScreenHeight);
        } else {
            fixSize(this.mViewLeft, this.mViewTop, this.mViewWidth, this.mViewHeight);
        }
    }

    public void fixSize(int $i0, int i, int $i2, int i2) {
        int $i5;
        int $i4 = this.mVideoWidth;
        if (!($i4 == 0 || ($i5 = this.mVideoHeight) == 0)) {
            if ($i2 == 0 || i2 == 0) {
                this.mVisibleLeft = $i0;
                this.mVisibleTop = i;
                this.mVisibleWidth = this.mVideoWidth;
                this.mVisibleHeight = this.mVideoHeight;
                getHolder().setFixedSize(this.mVisibleWidth, this.mVisibleHeight);
                FrameLayout.LayoutParams $r2 = new FrameLayout.LayoutParams(-2, -2);
                $r2.leftMargin = this.mVisibleLeft;
                $r2.topMargin = this.mVisibleTop;
                $r2.gravity = 51;
                setLayoutParams($r2);
            } else if (this.mKeepRatio) {
                if ($i4 * i2 > $i2 * $i5) {
                    this.mVisibleWidth = $i2;
                    this.mVisibleHeight = ($i5 * $i2) / $i4;
                } else if ($i4 * i2 < $i2 * $i5) {
                    this.mVisibleWidth = ($i4 * i2) / $i5;
                    this.mVisibleHeight = i2;
                }
                this.mVisibleLeft = $i0 + (($i2 - this.mVisibleWidth) / 2);
                this.mVisibleTop = i + ((i2 - this.mVisibleHeight) / 2);
                getHolder().setFixedSize(this.mVisibleWidth, this.mVisibleHeight);
                FrameLayout.LayoutParams $r22 = new FrameLayout.LayoutParams(-2, -2);
                $r22.leftMargin = this.mVisibleLeft;
                $r22.topMargin = this.mVisibleTop;
                $r22.gravity = 51;
                setLayoutParams($r22);
            }
        }
        this.mVisibleLeft = $i0;
        this.mVisibleTop = i;
        this.mVisibleWidth = $i2;
        this.mVisibleHeight = i2;
        getHolder().setFixedSize(this.mVisibleWidth, this.mVisibleHeight);
        FrameLayout.LayoutParams $r222 = new FrameLayout.LayoutParams(-2, -2);
        $r222.leftMargin = this.mVisibleLeft;
        $r222.topMargin = this.mVisibleTop;
        $r222.gravity = 51;
        setLayoutParams($r222);
    }

    public int getAudioSessionId() {
        return this.mMediaPlayer.getAudioSessionId();
    }

    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        int $i0;
        if (isInPlaybackState()) {
            int $i02 = this.mDuration;
            if ($i02 > 0) {
                return $i02;
            }
            $i0 = this.mMediaPlayer.getDuration();
        } else {
            $i0 = STATE_ERROR;
        }
        this.mDuration = $i0;
        return this.mDuration;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r3.mCurrentState;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isInPlaybackState() {
        /*
            r3 = this;
            android.media.MediaPlayer r0 = r3.mMediaPlayer
            if (r0 == 0) goto L_0x0010
            int r1 = r3.mCurrentState
            r2 = -1
            if (r1 == r2) goto L_0x0010
            if (r1 == 0) goto L_0x0010
            r2 = 1
            if (r1 == r2) goto L_0x0010
            r2 = 1
            return r2
        L_0x0010:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.package_1.Cocos2dxVideoView.isInPlaybackState():boolean");
    }

    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int $i0;
        StringBuilder $r3;
        String $r2;
        if (this.mVideoWidth == 0 || this.mVideoHeight == 0) {
            setMeasuredDimension(this.mViewWidth, this.mViewHeight);
            $r2 = this.TAG;
            $r3 = new StringBuilder();
            $r3.append("");
            $r3.append(this.mViewWidth);
            $r3.append(":");
            $i0 = this.mViewHeight;
        } else {
            setMeasuredDimension(this.mVisibleWidth, this.mVisibleHeight);
            $r2 = this.TAG;
            $r3 = new StringBuilder();
            $r3.append("");
            $r3.append(this.mVisibleWidth);
            $r3.append(":");
            $i0 = this.mVisibleHeight;
        }
        $r3.append($i0);
        Log.i($r2, $r3.toString());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) != 1) {
            return true;
        }
        if (isPlaying()) {
            pause();
            return true;
        } else if (this.mCurrentState != 4) {
            return true;
        } else {
            resume();
            return true;
        }
    }

    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
            OnVideoEventListener $r2 = this.mOnVideoEventListener;
            if ($r2 != null) {
                $r2.onVideoEvent(this.mViewTag, 1);
            }
        }
        this.mTargetState = 4;
    }

    public int resolveAdjustedSize(int $i0, int i) {
        int $i2 = View.MeasureSpec.getMode(i);
        int $i1 = View.MeasureSpec.getSize(i);
        return $i2 != Integer.MIN_VALUE ? ($i2 == 0 || $i2 != 1073741824) ? $i0 : $i1 : Math.min($i0, $i1);
    }

    public void restart() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(0);
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            this.mTargetState = 3;
        }
    }

    public void resume() {
        if (isInPlaybackState() && this.mCurrentState == 4) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            OnVideoEventListener $r2 = this.mOnVideoEventListener;
            if ($r2 != null) {
                $r2.onVideoEvent(this.mViewTag, 0);
            }
        }
    }

    public void seekTo(int $i0) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo($i0);
            $i0 = 0;
        }
        this.mSeekWhenPrepared = $i0;
    }

    public void setFullScreenEnabled(boolean z, int i, int i2) {
        if (this.mFullScreenEnabled != z) {
            this.mFullScreenEnabled = z;
            if (!(i == 0 || i2 == 0)) {
                this.mFullScreenWidth = i;
                this.mFullScreenHeight = i2;
            }
            fixSize();
        }
    }

    public void setKeepRatio(boolean z) {
        this.mKeepRatio = z;
        fixSize();
    }

    public void setOnCompletionListener(OnVideoEventListener onVideoEventListener) {
        this.mOnVideoEventListener = onVideoEventListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setPlaySpeed(float f) {
        MediaPlayer $r2 = this.mMediaPlayer;
        if ($r2 != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                PlaybackParams $r1 = $r2.getPlaybackParams();
                $r1.setSpeed(f);
                this.mMediaPlayer.setPlaybackParams($r1);
            } else {
                return;
            }
        }
        this._playSpeed = f;
    }

    public void setVideoFileName(String $r1) {
        boolean $z0;
        if ($r1.startsWith(AssetResourceRoot)) {
            $r1 = $r1.substring(7);
        }
        if ($r1.startsWith("/")) {
            $z0 = false;
        } else {
            this.mVideoFilePath = $r1;
            $z0 = true;
        }
        this.mIsAssetRouse = $z0;
        setVideoURI(Uri.parse($r1), (Map) null);
    }

    public void setVideoRect(int i, int i2, int i3, int i4) {
        this.mViewLeft = i;
        this.mViewTop = i2;
        this.mViewWidth = i3;
        this.mViewHeight = i4;
        fixSize(this.mViewLeft, this.mViewTop, this.mViewWidth, this.mViewHeight);
    }

    public void setVideoURL(String str) {
        this.mIsAssetRouse = false;
        setVideoURI(Uri.parse(str), (Map) null);
    }

    public void setVisibility(int i) {
        if (i == 4) {
            this.mNeedResume = isPlaying();
            if (this.mNeedResume) {
                this.mSeekWhenPrepared = getCurrentPosition();
            }
        } else if (this.mNeedResume) {
            start();
            this.mNeedResume = false;
        }
        super.setVisibility(i);
    }

    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            setPlaySpeed(this._playSpeed);
            Log.d("播放视频", "速度0=" + this._playSpeed);
            this.mCurrentState = 3;
            OnVideoEventListener $r4 = this.mOnVideoEventListener;
            if ($r4 != null) {
                $r4.onVideoEvent(this.mViewTag, 0);
            }
        }
        this.mTargetState = 3;
    }

    public void start(float $f0) {
        Log.d("播放视频", "速度1=" + $f0);
        if (((double) $f0) < 0.1d) {
            $f0 = 0.1f;
        }
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            Log.d("播放视频", "速度=" + $f0);
            this.mCurrentState = 3;
            OnVideoEventListener $r4 = this.mOnVideoEventListener;
            if ($r4 != null) {
                $r4.onVideoEvent(this.mViewTag, 0);
            }
        }
        setPlaySpeed($f0);
        this.mTargetState = 3;
    }

    public void stop() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            stopPlayback();
            OnVideoEventListener $r2 = this.mOnVideoEventListener;
            if ($r2 != null) {
                $r2.onVideoEvent(this.mViewTag, 2);
            }
        }
    }

    public void stopPlayback() {
        MediaPlayer $r1 = this.mMediaPlayer;
        if ($r1 != null) {
            $r1.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            this.mTargetState = 0;
        }
    }

    public void suspend() {
        release(false);
    }
}

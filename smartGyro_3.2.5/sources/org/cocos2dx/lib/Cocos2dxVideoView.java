package org.cocos2dx.lib;

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
    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new C0999qa(this);
    protected Cocos2dxActivity mCocos2dxActivity = null;
    private MediaPlayer.OnCompletionListener mCompletionListener = new C0993na(this);
    /* access modifiers changed from: private */
    public int mCurrentBufferPercentage;
    /* access modifiers changed from: private */
    public int mCurrentState = 0;
    private int mDuration;
    private MediaPlayer.OnErrorListener mErrorListener = new C0997pa(this);
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
    MediaPlayer.OnPreparedListener mPreparedListener = new C0991ma(this);
    SurfaceHolder.Callback mSHCallback = new C1001ra(this);
    /* access modifiers changed from: private */
    public int mSeekWhenPrepared;
    protected MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener = new C0989la(this);
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
        String str;
        StringBuilder sb;
        if (this.mSurfaceHolder != null) {
            if (this.mIsAssetRouse) {
                if (this.mVideoFilePath == null) {
                    return;
                }
            } else if (this.mVideoUri == null) {
                return;
            }
            Intent intent = new Intent("com.android.music.musicservicecommand");
            intent.putExtra("command", "pause");
            this.mCocos2dxActivity.sendBroadcast(intent);
            release(false);
            try {
                this.mMediaPlayer = new MediaPlayer();
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                this.mDuration = STATE_ERROR;
                this.mCurrentBufferPercentage = 0;
                if (this.mIsAssetRouse) {
                    AssetFileDescriptor openFd = this.mCocos2dxActivity.getAssets().openFd(this.mVideoFilePath);
                    this.mMediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                } else {
                    this.mMediaPlayer.setDataSource(this.mCocos2dxActivity, this.mVideoUri);
                }
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = 1;
            } catch (IOException e) {
                e = e;
                str = this.TAG;
                sb = new StringBuilder();
                sb.append("Unable to open content: ");
                sb.append(this.mVideoUri);
                Log.w(str, sb.toString(), e);
                this.mCurrentState = STATE_ERROR;
                this.mTargetState = STATE_ERROR;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            } catch (IllegalArgumentException e2) {
                e = e2;
                str = this.TAG;
                sb = new StringBuilder();
                sb.append("Unable to open content: ");
                sb.append(this.mVideoUri);
                Log.w(str, sb.toString(), e);
                this.mCurrentState = STATE_ERROR;
                this.mTargetState = STATE_ERROR;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void release(boolean z) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
        }
    }

    private void setVideoURI(Uri uri, Map<String, String> map) {
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

    public void fixSize(int i, int i2, int i3, int i4) {
        int i5;
        int i6 = this.mVideoWidth;
        if (!(i6 == 0 || (i5 = this.mVideoHeight) == 0)) {
            if (i3 == 0 || i4 == 0) {
                this.mVisibleLeft = i;
                this.mVisibleTop = i2;
                this.mVisibleWidth = this.mVideoWidth;
                this.mVisibleHeight = this.mVideoHeight;
                getHolder().setFixedSize(this.mVisibleWidth, this.mVisibleHeight);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.leftMargin = this.mVisibleLeft;
                layoutParams.topMargin = this.mVisibleTop;
                layoutParams.gravity = 51;
                setLayoutParams(layoutParams);
            } else if (this.mKeepRatio) {
                if (i6 * i4 > i3 * i5) {
                    this.mVisibleWidth = i3;
                    this.mVisibleHeight = (i5 * i3) / i6;
                } else if (i6 * i4 < i3 * i5) {
                    this.mVisibleWidth = (i6 * i4) / i5;
                    this.mVisibleHeight = i4;
                }
                this.mVisibleLeft = i + ((i3 - this.mVisibleWidth) / 2);
                this.mVisibleTop = i2 + ((i4 - this.mVisibleHeight) / 2);
                getHolder().setFixedSize(this.mVisibleWidth, this.mVisibleHeight);
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
                layoutParams2.leftMargin = this.mVisibleLeft;
                layoutParams2.topMargin = this.mVisibleTop;
                layoutParams2.gravity = 51;
                setLayoutParams(layoutParams2);
            }
        }
        this.mVisibleLeft = i;
        this.mVisibleTop = i2;
        this.mVisibleWidth = i3;
        this.mVisibleHeight = i4;
        getHolder().setFixedSize(this.mVisibleWidth, this.mVisibleHeight);
        FrameLayout.LayoutParams layoutParams22 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams22.leftMargin = this.mVisibleLeft;
        layoutParams22.topMargin = this.mVisibleTop;
        layoutParams22.gravity = 51;
        setLayoutParams(layoutParams22);
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
        int i;
        if (isInPlaybackState()) {
            int i2 = this.mDuration;
            if (i2 > 0) {
                return i2;
            }
            i = this.mMediaPlayer.getDuration();
        } else {
            i = STATE_ERROR;
        }
        this.mDuration = i;
        return this.mDuration;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r3.mCurrentState;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isInPlaybackState() {
        /*
            r3 = this;
            android.media.MediaPlayer r0 = r3.mMediaPlayer
            r1 = 1
            if (r0 == 0) goto L_0x000f
            int r0 = r3.mCurrentState
            r2 = -1
            if (r0 == r2) goto L_0x000f
            if (r0 == 0) goto L_0x000f
            if (r0 == r1) goto L_0x000f
            goto L_0x0010
        L_0x000f:
            r1 = 0
        L_0x0010:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.Cocos2dxVideoView.isInPlaybackState():boolean");
    }

    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        String str;
        StringBuilder sb;
        if (this.mVideoWidth == 0 || this.mVideoHeight == 0) {
            setMeasuredDimension(this.mViewWidth, this.mViewHeight);
            str = this.TAG;
            sb = new StringBuilder();
            sb.append("");
            sb.append(this.mViewWidth);
            sb.append(":");
            i3 = this.mViewHeight;
        } else {
            setMeasuredDimension(this.mVisibleWidth, this.mVisibleHeight);
            str = this.TAG;
            sb = new StringBuilder();
            sb.append("");
            sb.append(this.mVisibleWidth);
            sb.append(":");
            i3 = this.mVisibleHeight;
        }
        sb.append(i3);
        Log.i(str, sb.toString());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 1) {
            if (isPlaying()) {
                pause();
            } else if (this.mCurrentState == 4) {
                resume();
            }
        }
        return true;
    }

    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
            OnVideoEventListener onVideoEventListener = this.mOnVideoEventListener;
            if (onVideoEventListener != null) {
                onVideoEventListener.onVideoEvent(this.mViewTag, 1);
            }
        }
        this.mTargetState = 4;
    }

    public int resolveAdjustedSize(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        return mode != Integer.MIN_VALUE ? (mode == 0 || mode != 1073741824) ? i : size : Math.min(i, size);
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
            OnVideoEventListener onVideoEventListener = this.mOnVideoEventListener;
            if (onVideoEventListener != null) {
                onVideoEventListener.onVideoEvent(this.mViewTag, 0);
            }
        }
    }

    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(i);
            i = 0;
        }
        this.mSeekWhenPrepared = i;
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
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                PlaybackParams playbackParams = mediaPlayer.getPlaybackParams();
                playbackParams.setSpeed(f);
                this.mMediaPlayer.setPlaybackParams(playbackParams);
            } else {
                return;
            }
        }
        this._playSpeed = f;
    }

    public void setVideoFileName(String str) {
        boolean z;
        if (str.startsWith(AssetResourceRoot)) {
            str = str.substring(7);
        }
        if (str.startsWith("/")) {
            z = false;
        } else {
            this.mVideoFilePath = str;
            z = true;
        }
        this.mIsAssetRouse = z;
        setVideoURI(Uri.parse(str), (Map<String, String>) null);
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
        setVideoURI(Uri.parse(str), (Map<String, String>) null);
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
            OnVideoEventListener onVideoEventListener = this.mOnVideoEventListener;
            if (onVideoEventListener != null) {
                onVideoEventListener.onVideoEvent(this.mViewTag, 0);
            }
        }
        this.mTargetState = 3;
    }

    public void start(float f) {
        Log.d("播放视频", "速度1=" + f);
        if (((double) f) < 0.1d) {
            f = 0.1f;
        }
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            Log.d("播放视频", "速度=" + f);
            this.mCurrentState = 3;
            OnVideoEventListener onVideoEventListener = this.mOnVideoEventListener;
            if (onVideoEventListener != null) {
                onVideoEventListener.onVideoEvent(this.mViewTag, 0);
            }
        }
        setPlaySpeed(f);
        this.mTargetState = 3;
    }

    public void stop() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            stopPlayback();
            OnVideoEventListener onVideoEventListener = this.mOnVideoEventListener;
            if (onVideoEventListener != null) {
                onVideoEventListener.onVideoEvent(this.mViewTag, 2);
            }
        }
    }

    public void stopPlayback() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
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

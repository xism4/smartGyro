package org.cocos2dx.package_1;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.widget.FrameLayout;
import java.lang.ref.WeakReference;
import org.cocos2dx.lib.Cocos2dxVideoView;
import org.cocos2dx.package_1.Cocos2dxVideoView;

public class Cocos2dxVideoHelper {
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
    /* access modifiers changed from: private */
    public Cocos2dxActivity mActivity = null;
    private FrameLayout mLayout = null;
    private SparseArray<Cocos2dxVideoView> sVideoViews = null;
    Cocos2dxVideoView.OnVideoEventListener videoEventListener = new LogActivity$1(this);

    class a implements Runnable {
        private int is;
        private int l;

        public a(int i, int i2) {
            this.l = i;
            this.is = i2;
        }

        public void run() {
            Cocos2dxVideoHelper.nativeExecuteVideoCallback(this.l, this.is);
        }
    }

    class b extends Handler {
        WeakReference<org.cocos2dx.lib.Cocos2dxVideoHelper> this$0;

        b(Cocos2dxVideoHelper cocos2dxVideoHelper) {
            this.this$0 = new WeakReference(cocos2dxVideoHelper);
        }

        public void handleMessage(Message message) {
            int $i0 = message.what;
            if ($i0 != 1000) {
                switch ($i0) {
                    case 0:
                        ((Cocos2dxVideoHelper) this.this$0.get())._createVideoView(message.arg1);
                        break;
                    case 1:
                        ((Cocos2dxVideoHelper) this.this$0.get())._removeVideoView(message.arg1);
                        break;
                    case 2:
                        ((Cocos2dxVideoHelper) this.this$0.get())._setVideoURL(message.arg1, message.arg2, (String) message.obj);
                        break;
                    case 3:
                        Rect $r5 = (Rect) message.obj;
                        ((Cocos2dxVideoHelper) this.this$0.get())._setVideoRect(message.arg1, $r5.left, $r5.top, $r5.right, $r5.bottom);
                        break;
                    case 4:
                        int $i02 = message.arg1;
                        float $f0 = ((float) message.arg2) / 100.0f;
                        float f = $f0;
                        ((Cocos2dxVideoHelper) this.this$0.get())._startVideo($i02, $f0);
                        break;
                    case 5:
                        ((Cocos2dxVideoHelper) this.this$0.get())._pauseVideo(message.arg1);
                        break;
                    case 6:
                        ((Cocos2dxVideoHelper) this.this$0.get())._resumeVideo(message.arg1);
                        break;
                    case 7:
                        ((Cocos2dxVideoHelper) this.this$0.get())._stopVideo(message.arg1);
                        break;
                    case 8:
                        ((Cocos2dxVideoHelper) this.this$0.get())._seekVideoTo(message.arg1, message.arg2);
                        break;
                    case 9:
                        Cocos2dxVideoHelper $r4 = (Cocos2dxVideoHelper) this.this$0.get();
                        if (message.arg2 != 1) {
                            $r4._setVideoVisible(message.arg1, false);
                            break;
                        } else {
                            $r4._setVideoVisible(message.arg1, true);
                            break;
                        }
                    case 10:
                        ((Cocos2dxVideoHelper) this.this$0.get())._restartVideo(message.arg1);
                        break;
                    case 11:
                        Cocos2dxVideoHelper $r42 = (Cocos2dxVideoHelper) this.this$0.get();
                        if (message.arg2 != 1) {
                            $r42._setVideoKeepRatio(message.arg1, false);
                            break;
                        } else {
                            $r42._setVideoKeepRatio(message.arg1, true);
                            break;
                        }
                    case 12:
                        Cocos2dxVideoHelper $r43 = (Cocos2dxVideoHelper) this.this$0.get();
                        Rect $r52 = (Rect) message.obj;
                        if (message.arg2 != 1) {
                            $r43._setFullScreenEnabled(message.arg1, false, $r52.right, $r52.bottom);
                            break;
                        } else {
                            $r43._setFullScreenEnabled(message.arg1, true, $r52.right, $r52.bottom);
                            break;
                        }
                }
            } else {
                ((Cocos2dxVideoHelper) this.this$0.get()).onBackKeyEvent();
            }
            super.handleMessage(message);
        }
    }

    Cocos2dxVideoHelper(Cocos2dxActivity cocos2dxActivity, FrameLayout frameLayout) {
        this.mActivity = cocos2dxActivity;
        this.mLayout = frameLayout;
        mVideoHandler = new b(this);
        this.sVideoViews = new SparseArray();
    }

    /* access modifiers changed from: private */
    public void _createVideoView(int i) {
        Cocos2dxVideoView $r1 = new Cocos2dxVideoView(this.mActivity, i);
        this.sVideoViews.put(i, $r1);
        this.mLayout.addView($r1, new FrameLayout.LayoutParams(-2, -2));
        $r1.setZOrderOnTop(true);
        $r1.setOnCompletionListener(this.videoEventListener);
    }

    /* access modifiers changed from: private */
    public void _pauseVideo(int i) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.pause();
        }
    }

    /* access modifiers changed from: private */
    public void _removeVideoView(int i) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.stopPlayback();
            this.sVideoViews.remove(i);
            this.mLayout.removeView($r3);
        }
    }

    /* access modifiers changed from: private */
    public void _restartVideo(int i) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.restart();
        }
    }

    /* access modifiers changed from: private */
    public void _resumeVideo(int i) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.resume();
        }
    }

    /* access modifiers changed from: private */
    public void _seekVideoTo(int i, int i2) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.seekTo(i2);
        }
    }

    /* access modifiers changed from: private */
    public void _setFullScreenEnabled(int i, boolean z, int i2, int i3) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.setFullScreenEnabled(z, i2, i3);
        }
    }

    /* access modifiers changed from: private */
    public void _setVideoKeepRatio(int i, boolean z) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.setKeepRatio(z);
        }
    }

    /* access modifiers changed from: private */
    public void _setVideoRect(int i, int i2, int i3, int i4, int i5) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.setVideoRect(i2, i3, i4, i5);
        }
    }

    /* access modifiers changed from: private */
    public void _setVideoURL(int i, int i2, String str) {
        Cocos2dxVideoView $r4 = this.sVideoViews.get(i);
        if ($r4 == null) {
            return;
        }
        if (i2 == 0) {
            $r4.setVideoFileName(str);
        } else if (i2 == 1) {
            $r4.setVideoURL(str);
        }
    }

    /* access modifiers changed from: private */
    public void _setVideoVisible(int i, boolean z) {
        byte $b1;
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            if (z) {
                $r3.fixSize();
                $b1 = 0;
            } else {
                $b1 = 4;
            }
            $r3.setVisibility($b1);
        }
    }

    /* access modifiers changed from: private */
    public void _startVideo(int i, float f) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.start(f);
        }
    }

    /* access modifiers changed from: private */
    public void _stopVideo(int i) {
        Cocos2dxVideoView $r3 = this.sVideoViews.get(i);
        if ($r3 != null) {
            $r3.stop();
        }
    }

    public static int createVideoWidget() {
        Message $r0 = new Message();
        $r0.what = 0;
        $r0.arg1 = videoTag;
        mVideoHandler.sendMessage($r0);
        int $i0 = videoTag;
        videoTag = $i0 + 1;
        return $i0;
    }

    public static native void nativeExecuteVideoCallback(int i, int i2);

    /* access modifiers changed from: private */
    public void onBackKeyEvent() {
        int $i0 = this.sVideoViews.size();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            int $i2 = this.sVideoViews.keyAt($i1);
            Cocos2dxVideoView $r4 = this.sVideoViews.get($i2);
            if ($r4 != null) {
                $r4.setFullScreenEnabled(false, 0, 0);
                this.mActivity.runOnGLThread(new a($i2, 1000));
            }
        }
    }

    public static void pauseVideo(int i) {
        Message $r0 = new Message();
        $r0.what = 5;
        $r0.arg1 = i;
        mVideoHandler.sendMessage($r0);
    }

    public static void removeVideoWidget(int i) {
        Message $r0 = new Message();
        $r0.what = 1;
        $r0.arg1 = i;
        mVideoHandler.sendMessage($r0);
    }

    public static void restartVideo(int i) {
        Message $r0 = new Message();
        $r0.what = 10;
        $r0.arg1 = i;
        mVideoHandler.sendMessage($r0);
    }

    public static void resumeVideo(int i) {
        Message $r0 = new Message();
        $r0.what = 6;
        $r0.arg1 = i;
        mVideoHandler.sendMessage($r0);
    }

    public static void seekVideoTo(int i, int i2) {
        Message $r0 = new Message();
        $r0.what = 8;
        $r0.arg1 = i;
        $r0.arg2 = i2;
        mVideoHandler.sendMessage($r0);
    }

    public static void setFullScreenEnabled(int i, boolean z, int i2, int i3) {
        Message $r0 = new Message();
        $r0.what = 12;
        $r0.arg1 = i;
        if (z) {
            $r0.arg2 = 1;
        } else {
            $r0.arg2 = 0;
        }
        $r0.obj = new Rect(0, 0, i2, i3);
        mVideoHandler.sendMessage($r0);
    }

    public static void setPlaySpeed(float f) {
    }

    public static void setVideoKeepRatioEnabled(int i, boolean z) {
        Message $r0 = new Message();
        $r0.what = 11;
        $r0.arg1 = i;
        $r0.arg2 = z ? (byte) 1 : 0;
        mVideoHandler.sendMessage($r0);
    }

    public static void setVideoRect(int i, int i2, int i3, int i4, int i5) {
        Message $r0 = new Message();
        $r0.what = 3;
        $r0.arg1 = i;
        $r0.obj = new Rect(i2, i3, i4, i5);
        mVideoHandler.sendMessage($r0);
    }

    public static void setVideoUrl(int i, int i2, String str) {
        Message $r1 = new Message();
        $r1.what = 2;
        $r1.arg1 = i;
        $r1.arg2 = i2;
        $r1.obj = str;
        mVideoHandler.sendMessage($r1);
    }

    public static void setVideoVisible(int i, boolean z) {
        Message $r0 = new Message();
        $r0.what = 9;
        $r0.arg1 = i;
        $r0.arg2 = z ? (byte) 1 : 0;
        mVideoHandler.sendMessage($r0);
    }

    public static void startVideo(int i, int i2) {
        Message $r0 = new Message();
        $r0.what = 4;
        $r0.arg1 = i;
        $r0.arg2 = i2;
        mVideoHandler.sendMessage($r0);
    }

    public static void stopVideo(int i) {
        Message $r0 = new Message();
        $r0.what = 7;
        $r0.arg1 = i;
        mVideoHandler.sendMessage($r0);
    }
}

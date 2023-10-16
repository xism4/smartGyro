package org.cocos2dx.package_1;

import java.util.ArrayList;

public class GameControllerAdapter {
    private static ArrayList<Runnable> sRunnableFrameStartList;

    public static void addRunnableToFrameStartList(Runnable runnable) {
        if (sRunnableFrameStartList == null) {
            sRunnableFrameStartList = new ArrayList();
        }
        sRunnableFrameStartList.add(runnable);
    }

    /* access modifiers changed from: private */
    public static native void nativeControllerAxisEvent(String str, int i, int i2, float f, boolean z);

    /* access modifiers changed from: private */
    public static native void nativeControllerButtonEvent(String str, int i, int i2, boolean z, float f, boolean z2);

    /* access modifiers changed from: private */
    public static native void nativeControllerConnected(String str, int i);

    /* access modifiers changed from: private */
    public static native void nativeControllerDisconnected(String str, int i);

    public static void onAxisEvent(String str, int i, int i2, float f, boolean z) {
        Cocos2dxHelper.runOnGLThread(new Notification$1(str, i, i2, f, z));
    }

    public static void onButtonEvent(String str, int i, int i2, boolean z, float f, boolean z2) {
        Cocos2dxHelper.runOnGLThread(new Item(str, i, i2, z, f, z2));
    }

    public static void onConnected(String str, int i) {
        Cocos2dxHelper.runOnGLThread(new Relay(str, i));
    }

    public static void onDisconnected(String str, int i) {
        Cocos2dxHelper.runOnGLThread(new Client(str, i));
    }

    public static void onDrawFrameStart() {
        ArrayList $r0 = sRunnableFrameStartList;
        if ($r0 != null) {
            int $i0 = $r0.size();
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                sRunnableFrameStartList.get($i1).run();
            }
        }
    }

    public static void removeRunnableFromFrameStartList(Runnable runnable) {
        ArrayList $r1 = sRunnableFrameStartList;
        if ($r1 != null) {
            $r1.remove(runnable);
        }
    }
}

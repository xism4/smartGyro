package org.cocos2dx.lib;

import java.util.ArrayList;

public class GameControllerAdapter {
    private static ArrayList<Runnable> sRunnableFrameStartList;

    public static void addRunnableToFrameStartList(Runnable runnable) {
        if (sRunnableFrameStartList == null) {
            sRunnableFrameStartList = new ArrayList<>();
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
        Cocos2dxHelper.runOnGLThread(new C0959Wa(str, i, i2, f, z));
    }

    public static void onButtonEvent(String str, int i, int i2, boolean z, float f, boolean z2) {
        Cocos2dxHelper.runOnGLThread(new C0957Va(str, i, i2, z, f, z2));
    }

    public static void onConnected(String str, int i) {
        Cocos2dxHelper.runOnGLThread(new C0953Ta(str, i));
    }

    public static void onDisconnected(String str, int i) {
        Cocos2dxHelper.runOnGLThread(new C0955Ua(str, i));
    }

    public static void onDrawFrameStart() {
        ArrayList<Runnable> arrayList = sRunnableFrameStartList;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                sRunnableFrameStartList.get(i).run();
            }
        }
    }

    public static void removeRunnableFromFrameStartList(Runnable runnable) {
        ArrayList<Runnable> arrayList = sRunnableFrameStartList;
        if (arrayList != null) {
            arrayList.remove(runnable);
        }
    }
}

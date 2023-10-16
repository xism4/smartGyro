package org.cocos2dx.package_1;

final class Item implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ boolean c;
    final /* synthetic */ float d;
    final /* synthetic */ boolean g;
    final /* synthetic */ int id;

    Item(String str, int i, int i2, boolean z, float f, boolean z2) {
        this.a = str;
        this.b = i;
        this.id = i2;
        this.c = z;
        this.d = f;
        this.g = z2;
    }

    public void run() {
        GameControllerAdapter.nativeControllerButtonEvent(this.a, this.b, this.id, this.c, this.d, this.g);
    }
}

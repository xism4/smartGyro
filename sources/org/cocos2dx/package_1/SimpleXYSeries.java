package org.cocos2dx.package_1;

final class SimpleXYSeries implements Runnable {
    final /* synthetic */ float a;
    final /* synthetic */ float b;
    final /* synthetic */ float c;
    final /* synthetic */ long d;

    SimpleXYSeries(float f, float f2, float f3, long j) {
        this.a = f;
        this.c = f2;
        this.b = f3;
        this.d = j;
    }

    public void run() {
        Cocos2dxAccelerometer.onSensorChanged(this.a, this.c, this.b, this.d);
    }
}

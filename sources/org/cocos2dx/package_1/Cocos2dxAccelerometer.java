package org.cocos2dx.package_1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.view.WindowManager;

public class Cocos2dxAccelerometer implements SensorEventListener {
    static final float ALPHA = 0.25f;
    private static final String PAGE_KEY = "Cocos2dxAccelerometer";
    final float[] accelerometerValues = new float[3];
    final float[] compassFieldValues = new float[3];
    private final Sensor mAccelerometer;
    private final Sensor mCompass;
    private final Context mContext;
    private final int mNaturalOrientation;
    private final SensorManager mSensorManager;

    public Cocos2dxAccelerometer(Context context) {
        this.mContext = context;
        this.mSensorManager = (SensorManager) this.mContext.getSystemService("sensor");
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(1);
        this.mCompass = this.mSensorManager.getDefaultSensor(2);
        this.mNaturalOrientation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getOrientation();
    }

    public static native void onSensorChanged(float f, float f2, float f3, long j);

    public void disable() {
        this.mSensorManager.unregisterListener(this);
    }

    public void enableAccel() {
        this.mSensorManager.registerListener(this, this.mAccelerometer, 1);
    }

    public void enableCompass() {
        this.mSensorManager.registerListener(this, this.mCompass, 1);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float $f3;
        if (sensorEvent.sensor.getType() == 1) {
            float[] $r3 = sensorEvent.values;
            float $f1 = $r3[0];
            float $f0 = $r3[1];
            float $f2 = $r3[2];
            float[] $r32 = this.accelerometerValues;
            $r32[0] = $f1;
            $r32[1] = $f0;
            $r32[2] = $f2;
            int $i0 = this.mContext.getResources().getConfiguration().orientation;
            if ($i0 == 2 && this.mNaturalOrientation != 0) {
                $f3 = -$f0;
            } else if ($i0 != 1 || this.mNaturalOrientation == 0) {
                $f3 = $f1;
                $f1 = $f0;
            } else {
                $f1 = -$f1;
                $f3 = $f0;
            }
            int $i02 = Cocos2dxHelper.getActivity().getWindowManager().getDefaultDisplay().getRotation();
            if ($i02 == 2 || $i02 == 3) {
                $f3 = -$f3;
                $f1 = -$f1;
            }
            long $l2 = sensorEvent.timestamp;
            long j = $l2;
            Cocos2dxGLSurfaceView.queueAccelerometer($f3, $f1, $f2, $l2);
        } else if (sensorEvent.sensor.getType() == 2) {
            float[] $r33 = this.compassFieldValues;
            float[] $r10 = sensorEvent.values;
            $r33[0] = $r10[0];
            $r33[1] = $r10[1];
            $r33[2] = $r10[2];
        }
    }

    public void setInterval(float f) {
        if (Build.VERSION.SDK_INT < 11) {
            this.mSensorManager.registerListener(this, this.mAccelerometer, 1);
        } else {
            this.mSensorManager.registerListener(this, this.mAccelerometer, (int) (f * 1000000.0f));
        }
    }
}

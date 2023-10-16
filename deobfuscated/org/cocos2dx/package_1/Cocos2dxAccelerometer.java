package org.cocos2dx.package_1;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;

public class Cocos2dxAccelerometer
  implements SensorEventListener
{
  static final float ALPHA = 0.25F;
  private static final String PAGE_KEY = "Cocos2dxAccelerometer";
  final float[] accelerometerValues = new float[3];
  final float[] compassFieldValues = new float[3];
  private final Sensor mAccelerometer;
  private final Sensor mCompass;
  private final Context mContext;
  private final int mNaturalOrientation;
  private final SensorManager mSensorManager;
  
  public Cocos2dxAccelerometer(Context paramContext)
  {
    mContext = paramContext;
    mSensorManager = ((SensorManager)mContext.getSystemService("sensor"));
    mAccelerometer = mSensorManager.getDefaultSensor(1);
    mCompass = mSensorManager.getDefaultSensor(2);
    mNaturalOrientation = ((WindowManager)mContext.getSystemService("window")).getDefaultDisplay().getOrientation();
  }
  
  public static native void onSensorChanged(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
  
  public void disable()
  {
    mSensorManager.unregisterListener(this);
  }
  
  public void enableAccel()
  {
    mSensorManager.registerListener(this, mAccelerometer, 1);
  }
  
  public void enableCompass()
  {
    mSensorManager.registerListener(this, mCompass, 1);
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    float[] arrayOfFloat;
    if (sensor.getType() == 1)
    {
      arrayOfFloat = values;
      float f2 = arrayOfFloat[0];
      float f1 = arrayOfFloat[1];
      float f5 = arrayOfFloat[2];
      arrayOfFloat = accelerometerValues;
      arrayOfFloat[0] = f2;
      arrayOfFloat[1] = f1;
      arrayOfFloat[2] = f5;
      int i = mContext.getResources().getConfiguration().orientation;
      float f3;
      if ((i == 2) && (mNaturalOrientation != 0))
      {
        f3 = -f1;
        f1 = f2;
        f2 = f3;
      }
      else if ((i == 1) && (mNaturalOrientation != 0))
      {
        f3 = -f2;
        f2 = f1;
        f1 = f3;
      }
      i = Cocos2dxHelper.getActivity().getWindowManager().getDefaultDisplay().getRotation();
      float f4;
      if (i != 2)
      {
        f4 = f1;
        f3 = f2;
        if (i != 3) {}
      }
      else
      {
        f3 = -f2;
        f4 = -f1;
      }
      Cocos2dxGLSurfaceView.queueAccelerometer(f3, f4, f5, timestamp);
      return;
    }
    if (sensor.getType() == 2)
    {
      arrayOfFloat = compassFieldValues;
      paramSensorEvent = values;
      arrayOfFloat[0] = paramSensorEvent[0];
      arrayOfFloat[1] = paramSensorEvent[1];
      arrayOfFloat[2] = paramSensorEvent[2];
    }
  }
  
  public void setInterval(float paramFloat)
  {
    if (Build.VERSION.SDK_INT < 11)
    {
      mSensorManager.registerListener(this, mAccelerometer, 1);
      return;
    }
    mSensorManager.registerListener(this, mAccelerometer, (int)(paramFloat * 1000000.0F));
  }
}
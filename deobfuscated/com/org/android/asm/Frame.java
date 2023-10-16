package com.org.android.asm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import com.org.android.manager.Label;
import com.org.android.ui.asm.e;
import com.org.android.util.SimpleArrayMap;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

class Frame
  extends f
{
  private static final Method a;
  private static final Constructor c;
  private static final Method d;
  private static final Class g;
  
  static
  {
    Object localObject3 = null;
    try
    {
      Object localObject6 = Class.forName("android.graphics.FontFamily");
      Object localObject1 = localObject6;
      localObject4 = ((Class)localObject6).getConstructor(new Class[0]);
      localObject5 = Integer.TYPE;
      Class localClass1 = Integer.TYPE;
      Class localClass2 = Boolean.TYPE;
      localObject5 = ((Class)localObject6).getMethod("addFontWeightStyle", new Class[] { ByteBuffer.class, localObject5, List.class, localClass1, localClass2 });
      localObject6 = Array.newInstance((Class)localObject6, 1);
      localObject6 = localObject6.getClass();
      localObject6 = Typeface.class.getMethod("createFromFamiliesWithDefault", new Class[] { localObject6 });
      localObject3 = localObject4;
      localObject4 = localObject5;
      localObject5 = localObject6;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}catch (ClassNotFoundException localClassNotFoundException) {}
    Log.e("TypefaceCompatApi24Impl", localClassNotFoundException.getClass().getName(), localClassNotFoundException);
    Object localObject2 = null;
    Object localObject5 = null;
    Object localObject4 = null;
    c = localObject3;
    g = localObject2;
    d = (Method)localObject4;
    a = (Method)localObject5;
  }
  
  Frame() {}
  
  private static boolean a(Object paramObject, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Method localMethod = d;
    try
    {
      paramObject = localMethod.invoke(paramObject, new Object[] { paramByteBuffer, Integer.valueOf(paramInt1), null, Integer.valueOf(paramInt2), Boolean.valueOf(paramBoolean) });
      paramObject = (Boolean)paramObject;
      paramBoolean = paramObject.booleanValue();
      return paramBoolean;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    throw new RuntimeException(paramObject);
  }
  
  private static Typeface get(Object paramObject)
  {
    Object localObject = g;
    try
    {
      localObject = Array.newInstance((Class)localObject, 1);
      Array.set(localObject, 0, paramObject);
      paramObject = a;
      paramObject = paramObject.invoke(null, new Object[] { localObject });
      return (Typeface)paramObject;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    throw new RuntimeException(paramObject);
  }
  
  private static Object get()
  {
    Object localObject = c;
    try
    {
      localObject = ((Constructor)localObject).newInstance(new Object[0]);
      return localObject;
    }
    catch (InvocationTargetException localInvocationTargetException) {}catch (InstantiationException localInstantiationException) {}catch (IllegalAccessException localIllegalAccessException) {}
    throw new RuntimeException(localIllegalAccessException);
  }
  
  public static boolean set()
  {
    if (d == null) {
      Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
    }
    return d != null;
  }
  
  public Typeface a(Context paramContext, CancellationSignal paramCancellationSignal, Label[] paramArrayOfLabel, int paramInt)
  {
    Object localObject = get();
    SimpleArrayMap localSimpleArrayMap = new SimpleArrayMap();
    int j = paramArrayOfLabel.length;
    int i = 0;
    while (i < j)
    {
      Label localLabel = paramArrayOfLabel[i];
      Uri localUri = localLabel.getValue();
      ByteBuffer localByteBuffer2 = (ByteBuffer)localSimpleArrayMap.get(localUri);
      ByteBuffer localByteBuffer1 = localByteBuffer2;
      if (localByteBuffer2 == null)
      {
        localByteBuffer2 = ByteVector.read(paramContext, paramCancellationSignal, localUri);
        localByteBuffer1 = localByteBuffer2;
        localSimpleArrayMap.put(localUri, localByteBuffer2);
      }
      if (!a(localObject, localByteBuffer1, localLabel.d(), localLabel.b(), localLabel.a())) {
        return null;
      }
      i += 1;
    }
    return Typeface.create(get(localObject), paramInt);
  }
  
  public Typeface a(Context paramContext, e paramE, Resources paramResources, int paramInt)
  {
    Object localObject1 = get();
    paramE = paramE.a();
    int i = paramE.length;
    paramInt = 0;
    while (paramInt < i)
    {
      Object localObject2 = paramE[paramInt];
      ByteBuffer localByteBuffer = ByteVector.a(paramContext, paramResources, localObject2.b());
      if (localByteBuffer == null) {
        return null;
      }
      if (!a(localObject1, localByteBuffer, localObject2.r(), localObject2.f(), localObject2.c())) {
        return null;
      }
      paramInt += 1;
    }
    return get(localObject1);
  }
}

package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class Frame
{
  private static Field a;
  private static Field b;
  private static Class c;
  private static boolean d;
  private static boolean e;
  private static boolean f;
  private static Field g;
  private static boolean i;
  
  private static void a(Resources paramResources)
  {
    if (!f)
    {
      try
      {
        Field localField1 = Resources.class.getDeclaredField("mResourcesImpl");
        a = localField1;
        localField1 = a;
        localField1.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException1)
      {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", localNoSuchFieldException1);
      }
      f = true;
    }
    Field localField2 = a;
    if (localField2 == null) {
      return;
    }
    Object localObject2 = null;
    try
    {
      paramResources = localField2.get(paramResources);
    }
    catch (IllegalAccessException paramResources)
    {
      Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", paramResources);
      paramResources = null;
    }
    if (paramResources == null) {
      return;
    }
    if (!d)
    {
      try
      {
        localField2 = paramResources.getClass().getDeclaredField("mDrawableCache");
        g = localField2;
        localField2 = g;
        localField2.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException2)
      {
        Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", localNoSuchFieldException2);
      }
      d = true;
    }
    Field localField3 = g;
    Object localObject1 = localObject2;
    if (localField3 != null) {
      try
      {
        localObject1 = localField3.get(paramResources);
      }
      catch (IllegalAccessException paramResources)
      {
        Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", paramResources);
        localObject1 = localObject2;
      }
    }
    if (localObject1 != null) {
      init(localObject1);
    }
  }
  
  private static void init(Resources paramResources)
  {
    if (!d)
    {
      try
      {
        Field localField1 = Resources.class.getDeclaredField("mDrawableCache");
        g = localField1;
        localField1 = g;
        localField1.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", localNoSuchFieldException);
      }
      d = true;
    }
    Object localObject2 = null;
    Field localField2 = g;
    Object localObject1 = localObject2;
    if (localField2 != null) {
      try
      {
        localObject1 = localField2.get(paramResources);
      }
      catch (IllegalAccessException paramResources)
      {
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", paramResources);
        localObject1 = localObject2;
      }
    }
    if (localObject1 == null) {
      return;
    }
    init(localObject1);
  }
  
  private static void init(Object paramObject)
  {
    if (!i)
    {
      try
      {
        Class localClass = Class.forName("android.content.res.ThemedResourceCache");
        c = localClass;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", localClassNotFoundException);
      }
      i = true;
    }
    Object localObject = c;
    if (localObject == null) {
      return;
    }
    if (!e)
    {
      try
      {
        localObject = ((Class)localObject).getDeclaredField("mUnthemedEntries");
        b = (Field)localObject;
        localObject = b;
        ((Field)localObject).setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", localNoSuchFieldException);
      }
      e = true;
    }
    Field localField = b;
    if (localField == null) {
      return;
    }
    try
    {
      paramObject = localField.get(paramObject);
      paramObject = (LongSparseArray)paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", paramObject);
      paramObject = null;
    }
    if (paramObject != null) {
      paramObject.clear();
    }
  }
  
  static void initialize(Resources paramResources)
  {
    int j = Build.VERSION.SDK_INT;
    if (j >= 28) {
      return;
    }
    if (j >= 24)
    {
      a(paramResources);
      return;
    }
    if (j >= 23)
    {
      init(paramResources);
      return;
    }
    if (j >= 21) {
      set(paramResources);
    }
  }
  
  private static void set(Resources paramResources)
  {
    if (!d)
    {
      try
      {
        Field localField1 = Resources.class.getDeclaredField("mDrawableCache");
        g = localField1;
        localField1 = g;
        localField1.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", localNoSuchFieldException);
      }
      d = true;
    }
    Field localField2 = g;
    if (localField2 != null)
    {
      try
      {
        paramResources = localField2.get(paramResources);
        paramResources = (Map)paramResources;
      }
      catch (IllegalAccessException paramResources)
      {
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", paramResources);
        paramResources = null;
      }
      if (paramResources != null) {
        paramResources.clear();
      }
    }
  }
}

package org.cocos2dx.package_1;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Cocos2dxReflectionHelper
{
  public Cocos2dxReflectionHelper() {}
  
  public static Object getConstantValue(Class paramClass, String paramString)
  {
    try
    {
      localObject = paramClass.getDeclaredField(paramString).get(null);
      return localObject;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      Object localObject;
      for (;;) {}
    }
    catch (IllegalAccessException paramClass)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException paramClass)
    {
      for (;;) {}
    }
    catch (Exception paramClass)
    {
      label37:
      label63:
      label119:
      for (;;) {}
    }
    paramClass = new StringBuilder();
    localObject = "can not get constant";
    break label37;
    paramClass = new StringBuilder();
    localObject = "arguments error when get ";
    paramClass.append((String)localObject);
    break label63;
    paramClass = new StringBuilder();
    paramClass.append(paramString);
    paramString = " is not accessible";
    paramClass.append(paramString);
    paramClass = paramClass.toString();
    break label119;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("can not find ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(" in ");
    ((StringBuilder)localObject).append(paramClass.getName());
    paramClass = ((StringBuilder)localObject).toString();
    Log.e("error", paramClass);
    return null;
  }
  
  public static Object invokeInstanceMethod(Object paramObject, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    Class localClass = paramObject.getClass();
    try
    {
      paramObject = localClass.getMethod(paramString, paramArrayOfClass).invoke(paramObject, paramArrayOfObject);
      return paramObject;
    }
    catch (NoSuchMethodException paramObject)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramObject)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException paramObject)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramObject)
    {
      for (;;) {}
    }
    paramObject = new StringBuilder();
    paramArrayOfClass = "an exception was thrown by the invoked method when invoking ";
    break label46;
    paramObject = new StringBuilder();
    paramArrayOfClass = "arguments are error when invoking ";
    label46:
    paramObject.append(paramArrayOfClass);
    break label109;
    paramObject = new StringBuilder();
    paramObject.append(paramString);
    paramString = " is not accessible";
    break label109;
    paramObject = new StringBuilder();
    paramObject.append("can not find ");
    paramObject.append(paramString);
    paramObject.append(" in ");
    paramString = localClass.getName();
    label109:
    paramObject.append(paramString);
    Log.e("error", paramObject.toString());
    return null;
  }
}

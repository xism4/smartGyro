package cz.msebera.android.http.client.ssl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CloneUtils
{
  public static Object cloneObject(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof Cloneable))
    {
      Object localObject = paramObject.getClass();
      try
      {
        localObject = ((Class)localObject).getMethod("clone", null);
        try
        {
          paramObject = ((Method)localObject).invoke(paramObject, null);
          return paramObject;
        }
        catch (IllegalAccessException paramObject)
        {
          throw new IllegalAccessError(paramObject.getMessage());
        }
        catch (InvocationTargetException paramObject)
        {
          paramObject = paramObject.getCause();
          if ((paramObject instanceof CloneNotSupportedException)) {
            throw ((CloneNotSupportedException)paramObject);
          }
          throw new Error("Unexpected exception", paramObject);
        }
        throw new CloneNotSupportedException();
      }
      catch (NoSuchMethodException paramObject)
      {
        throw new NoSuchMethodError(paramObject.getMessage());
      }
    }
  }
}

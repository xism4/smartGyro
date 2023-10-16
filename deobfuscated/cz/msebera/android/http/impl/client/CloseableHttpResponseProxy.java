package cz.msebera.android.http.impl.client;

import c.a.a.a.b.c.e;
import c.a.a.a.i.b.i;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.client.auth.CloseableHttpResponse;
import cz.msebera.android.http.mime.EntityUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Deprecated
class CloseableHttpResponseProxy
  implements InvocationHandler
{
  private static final Constructor<?> CONSTRUCTOR;
  private final HttpResponse original;
  
  static
  {
    try
    {
      Object localObject = i.class.getClassLoader();
      localObject = Proxy.getProxyClass((ClassLoader)localObject, new Class[] { e.class });
      localObject = ((Class)localObject).getConstructor(new Class[] { InvocationHandler.class });
      CONSTRUCTOR = (Constructor)localObject;
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new IllegalStateException(localNoSuchMethodException);
    }
  }
  
  CloseableHttpResponseProxy(HttpResponse paramHttpResponse)
  {
    original = paramHttpResponse;
  }
  
  public static CloseableHttpResponse newProxy(HttpResponse paramHttpResponse)
  {
    Constructor localConstructor = CONSTRUCTOR;
    try
    {
      paramHttpResponse = new CloseableHttpResponseProxy(paramHttpResponse);
      paramHttpResponse = localConstructor.newInstance(new Object[] { paramHttpResponse });
      return (CloseableHttpResponse)paramHttpResponse;
    }
    catch (IllegalAccessException paramHttpResponse)
    {
      throw new IllegalStateException(paramHttpResponse);
    }
    catch (InvocationTargetException paramHttpResponse)
    {
      throw new IllegalStateException(paramHttpResponse);
    }
    catch (InstantiationException paramHttpResponse)
    {
      throw new IllegalStateException(paramHttpResponse);
    }
  }
  
  public void close()
  {
    EntityUtils.consume(original.getEntity());
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    if (paramMethod.getName().equals("close"))
    {
      close();
      return null;
    }
    paramObject = original;
    try
    {
      paramObject = paramMethod.invoke(paramObject, paramArrayOfObject);
      return paramObject;
    }
    catch (InvocationTargetException paramObject)
    {
      paramMethod = paramObject.getCause();
      if (paramMethod != null) {
        throw paramMethod;
      }
      throw paramObject;
    }
  }
}

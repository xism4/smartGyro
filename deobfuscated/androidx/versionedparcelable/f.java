package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class f
{
  public f() {}
  
  private void a(Context paramContext)
  {
    try
    {
      Class localClass = get(paramContext.getClass());
      a(localClass.getName());
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getClass().getSimpleName());
      localStringBuilder.append(" does not have a Parcelizer");
      throw new RuntimeException(localStringBuilder.toString(), localClassNotFoundException);
    }
  }
  
  protected static void a(Context paramContext, f paramF)
  {
    try
    {
      Object localObject = e(paramContext);
      Class localClass = paramContext.getClass();
      localObject = ((Class)localObject).getDeclaredMethod("write", new Class[] { localClass, b.class });
      ((Method)localObject).invoke(null, new Object[] { paramContext, paramF });
      return;
    }
    catch (ClassNotFoundException paramContext)
    {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", paramContext);
    }
    catch (NoSuchMethodException paramContext)
    {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      if ((paramContext.getCause() instanceof RuntimeException)) {
        throw ((RuntimeException)paramContext.getCause());
      }
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      throw new RuntimeException("VersionedParcel encountered IllegalAccessException", paramContext);
    }
  }
  
  private static Class e(Context paramContext)
  {
    return get(paramContext.getClass());
  }
  
  protected static Context get(String paramString, f paramF)
  {
    try
    {
      paramString = Class.forName(paramString, true, b.class.getClassLoader());
      paramString = paramString.getDeclaredMethod("read", new Class[] { b.class });
      paramString = paramString.invoke(null, new Object[] { paramF });
      return (Context)paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", paramString);
    }
    catch (NoSuchMethodException paramString)
    {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", paramString);
    }
    catch (InvocationTargetException paramString)
    {
      if ((paramString.getCause() instanceof RuntimeException)) {
        throw ((RuntimeException)paramString.getCause());
      }
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", paramString);
    }
    catch (IllegalAccessException paramString)
    {
      throw new RuntimeException("VersionedParcel encountered IllegalAccessException", paramString);
    }
  }
  
  private static Class get(Class paramClass)
  {
    return Class.forName(String.format("%s.%sParcelizer", new Object[] { paramClass.getPackage().getName(), paramClass.getSimpleName() }), false, paramClass.getClassLoader());
  }
  
  public String a(String paramString, int paramInt)
  {
    if (!add(paramInt)) {
      return paramString;
    }
    return getValue();
  }
  
  protected abstract void a();
  
  public void a(int paramInt1, int paramInt2)
  {
    e(paramInt2);
    b(paramInt1);
  }
  
  public void a(Parcelable paramParcelable, int paramInt)
  {
    e(paramInt);
    clear(paramParcelable);
  }
  
  protected abstract void a(String paramString);
  
  public void a(boolean paramBoolean1, boolean paramBoolean2) {}
  
  protected abstract void a(byte[] paramArrayOfByte);
  
  public byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    if (!add(paramInt)) {
      return paramArrayOfByte;
    }
    return b();
  }
  
  public int add(int paramInt1, int paramInt2)
  {
    if (!add(paramInt2)) {
      return paramInt1;
    }
    return size();
  }
  
  protected abstract Parcelable add();
  
  public Parcelable add(Parcelable paramParcelable, int paramInt)
  {
    if (!add(paramInt)) {
      return paramParcelable;
    }
    return add();
  }
  
  protected abstract boolean add(int paramInt);
  
  protected abstract void b(int paramInt);
  
  public void b(String paramString, int paramInt)
  {
    e(paramInt);
    a(paramString);
  }
  
  protected abstract byte[] b();
  
  protected abstract f c();
  
  protected abstract void clear(Parcelable paramParcelable);
  
  public void clear(byte[] paramArrayOfByte, int paramInt)
  {
    e(paramInt);
    a(paramArrayOfByte);
  }
  
  protected void d(Context paramContext)
  {
    if (paramContext == null)
    {
      a(null);
      return;
    }
    a(paramContext);
    f localF = c();
    a(paramContext, localF);
    localF.a();
  }
  
  protected abstract void e(int paramInt);
  
  protected Context f()
  {
    String str = getValue();
    if (str == null) {
      return null;
    }
    return get(str, c());
  }
  
  protected abstract String getValue();
  
  public boolean processBytes()
  {
    return false;
  }
  
  protected abstract int size();
}

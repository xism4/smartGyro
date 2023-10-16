package com.org.android.util;

public class ByteVector<E>
  implements Cloneable
{
  private static final Object a = new Object();
  private Object[] b;
  private int[] c;
  private int i;
  private boolean s = false;
  
  public ByteVector()
  {
    this(10);
  }
  
  public ByteVector(int paramInt)
  {
    if (paramInt == 0)
    {
      c = ContainerHelpers.c;
      b = ContainerHelpers.b;
    }
    else
    {
      paramInt = ContainerHelpers.idealIntArraySize(paramInt);
      c = new int[paramInt];
      b = new Object[paramInt];
    }
    i = 0;
  }
  
  private void d()
  {
    int n = i;
    int[] arrayOfInt = c;
    Object[] arrayOfObject = b;
    int j = 0;
    int m;
    for (int k = 0; j < n; k = m)
    {
      Object localObject = arrayOfObject[j];
      m = k;
      if (localObject != a)
      {
        if (j != k)
        {
          arrayOfInt[k] = arrayOfInt[j];
          arrayOfObject[k] = localObject;
          arrayOfObject[j] = null;
        }
        m = k + 1;
      }
      j += 1;
    }
    s = false;
    i = k;
  }
  
  public int a(int paramInt)
  {
    if (s) {
      d();
    }
    return c[paramInt];
  }
  
  public void a(int paramInt, Object paramObject)
  {
    int j = ContainerHelpers.a(c, i, paramInt);
    if (j >= 0)
    {
      b[j] = paramObject;
      return;
    }
    int k = j;
    Object localObject1;
    if (k < i)
    {
      localObject1 = b;
      if (localObject1[k] == a)
      {
        c[k] = paramInt;
        localObject1[k] = paramObject;
        return;
      }
    }
    j = k;
    if (s)
    {
      j = k;
      if (i >= c.length)
      {
        d();
        j = ContainerHelpers.a(c, i, paramInt);
      }
    }
    k = i;
    if (k >= c.length)
    {
      k = ContainerHelpers.idealIntArraySize(k + 1);
      localObject1 = new int[k];
      Object[] arrayOfObject = new Object[k];
      Object localObject2 = c;
      System.arraycopy(localObject2, 0, localObject1, 0, localObject2.length);
      localObject2 = b;
      System.arraycopy(localObject2, 0, arrayOfObject, 0, localObject2.length);
      c = ((int[])localObject1);
      b = arrayOfObject;
    }
    k = i;
    if (k - j != 0)
    {
      localObject1 = c;
      int m = j + 1;
      System.arraycopy(localObject1, j, localObject1, m, k - j);
      localObject1 = b;
      System.arraycopy(localObject1, j, localObject1, m, i - j);
    }
    c[j] = paramInt;
    b[j] = paramObject;
    i += 1;
  }
  
  public Object add(int paramInt, Object paramObject)
  {
    paramInt = ContainerHelpers.a(c, i, paramInt);
    Object localObject = paramObject;
    if (paramInt >= 0)
    {
      localObject = b;
      if (localObject[paramInt] == a) {
        return paramObject;
      }
      localObject = localObject[paramInt];
    }
    return localObject;
  }
  
  public ByteVector clone()
  {
    try
    {
      Object localObject1 = super.clone();
      localObject1 = (ByteVector)localObject1;
      Object localObject2 = c;
      localObject2 = localObject2.clone();
      c = ((int[])localObject2);
      localObject2 = b;
      localObject2 = localObject2.clone();
      b = ((Object[])localObject2);
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }
  
  public void d(int paramInt, Object paramObject)
  {
    int j = i;
    if ((j != 0) && (paramInt <= c[(j - 1)]))
    {
      a(paramInt, paramObject);
      return;
    }
    if ((s) && (i >= c.length)) {
      d();
    }
    j = i;
    if (j >= c.length)
    {
      int k = ContainerHelpers.idealIntArraySize(j + 1);
      int[] arrayOfInt = new int[k];
      Object[] arrayOfObject = new Object[k];
      Object localObject = c;
      System.arraycopy(localObject, 0, arrayOfInt, 0, localObject.length);
      localObject = b;
      System.arraycopy(localObject, 0, arrayOfObject, 0, localObject.length);
      c = arrayOfInt;
      b = arrayOfObject;
    }
    c[j] = paramInt;
    b[j] = paramObject;
    i = (j + 1);
  }
  
  public Object get(int paramInt)
  {
    return add(paramInt, null);
  }
  
  public int read()
  {
    if (s) {
      d();
    }
    return i;
  }
  
  public Object read(int paramInt)
  {
    if (s) {
      d();
    }
    return b[paramInt];
  }
  
  public String toString()
  {
    if (read() <= 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder(i * 28);
    localStringBuilder.append('{');
    int j = 0;
    while (j < i)
    {
      if (j > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(a(j));
      localStringBuilder.append('=');
      Object localObject = read(j);
      if (localObject != this) {
        localStringBuilder.append(localObject);
      } else {
        localStringBuilder.append("(this Map)");
      }
      j += 1;
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}

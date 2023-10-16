package com.org.android.util;

import java.util.ConcurrentModificationException;

public class SimpleArrayMap<K, V>
{
  static Object[] a;
  static Object[] e;
  static int i;
  static int j;
  Object[] b;
  int[] c;
  int n;
  
  public SimpleArrayMap()
  {
    c = ContainerHelpers.c;
    b = ContainerHelpers.b;
    n = 0;
  }
  
  public SimpleArrayMap(int paramInt)
  {
    if (paramInt == 0)
    {
      c = ContainerHelpers.c;
      b = ContainerHelpers.b;
    }
    else
    {
      init(paramInt);
    }
    n = 0;
  }
  
  private static int a(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    try
    {
      paramInt1 = ContainerHelpers.a(paramArrayOfInt, paramInt1, paramInt2);
      return paramInt1;
    }
    catch (ArrayIndexOutOfBoundsException paramArrayOfInt)
    {
      for (;;) {}
    }
    throw new ConcurrentModificationException();
  }
  
  private static void a(int[] paramArrayOfInt, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfInt.length == 8) {
      try
      {
        if (i < 10)
        {
          paramArrayOfObject[0] = e;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt = (paramInt << 1) - 1;
          break label118;
          e = paramArrayOfObject;
          i += 1;
        }
        return;
      }
      catch (Throwable paramArrayOfInt)
      {
        throw paramArrayOfInt;
      }
    }
    if (paramArrayOfInt.length == 4) {}
    for (;;)
    {
      try
      {
        if (j < 10)
        {
          paramArrayOfObject[0] = a;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt = (paramInt << 1) - 1;
          break label134;
          a = paramArrayOfObject;
          j += 1;
        }
        return;
      }
      catch (Throwable paramArrayOfInt)
      {
        throw paramArrayOfInt;
      }
      return;
      label118:
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
      break;
      label134:
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
    }
  }
  
  private void init(int paramInt)
  {
    if (paramInt == 8) {
      try
      {
        if (e != null)
        {
          Object[] arrayOfObject1 = e;
          b = arrayOfObject1;
          e = (Object[])arrayOfObject1[0];
          c = ((int[])arrayOfObject1[1]);
          arrayOfObject1[1] = null;
          arrayOfObject1[0] = null;
          i -= 1;
          return;
        }
      }
      catch (Throwable localThrowable1)
      {
        throw localThrowable1;
      }
    }
    if (paramInt == 4) {
      try
      {
        if (a != null)
        {
          Object[] arrayOfObject2 = a;
          b = arrayOfObject2;
          a = (Object[])arrayOfObject2[0];
          c = ((int[])arrayOfObject2[1]);
          arrayOfObject2[1] = null;
          arrayOfObject2[0] = null;
          j -= 1;
          return;
        }
      }
      catch (Throwable localThrowable2)
      {
        throw localThrowable2;
      }
    }
    c = new int[paramInt];
    b = new Object[paramInt << 1];
  }
  
  int a()
  {
    int m = n;
    if (m == 0) {
      return -1;
    }
    int i1 = a(c, m, 0);
    if (i1 < 0) {
      return i1;
    }
    if (b[(i1 << 1)] == null) {
      return i1;
    }
    int k = i1 + 1;
    while ((k < m) && (c[k] == 0))
    {
      if (b[(k << 1)] == null) {
        return k;
      }
      k += 1;
    }
    m = i1 - 1;
    while ((m >= 0) && (c[m] == 0))
    {
      if (b[(m << 1)] == null) {
        return m;
      }
      m -= 1;
    }
    return k;
  }
  
  int a(Object paramObject, int paramInt)
  {
    int m = n;
    if (m == 0) {
      return -1;
    }
    int i1 = a(c, m, paramInt);
    if (i1 < 0) {
      return i1;
    }
    if (paramObject.equals(b[(i1 << 1)])) {
      return i1;
    }
    int k = i1 + 1;
    while ((k < m) && (c[k] == paramInt))
    {
      if (paramObject.equals(b[(k << 1)])) {
        return k;
      }
      k += 1;
    }
    m = i1 - 1;
    while ((m >= 0) && (c[m] == paramInt))
    {
      if (paramObject.equals(b[(m << 1)])) {
        return m;
      }
      m -= 1;
    }
    return k;
  }
  
  public Object a(int paramInt)
  {
    Object localObject2 = b;
    int i2 = paramInt << 1;
    Object localObject1 = localObject2[(i2 + 1)];
    int i1 = n;
    int k;
    if (i1 <= 1)
    {
      a(c, (Object[])localObject2, i1);
      c = ContainerHelpers.c;
      b = ContainerHelpers.b;
      k = 0;
    }
    else
    {
      int m = i1 - 1;
      localObject2 = c;
      int i3 = localObject2.length;
      k = 8;
      if ((i3 > 8) && (i1 < localObject2.length / 3))
      {
        if (i1 > 8) {
          k = i1 + (i1 >> 1);
        }
        localObject2 = c;
        Object[] arrayOfObject = b;
        init(k);
        if (i1 == n)
        {
          if (paramInt > 0)
          {
            System.arraycopy(localObject2, 0, c, 0, paramInt);
            System.arraycopy(arrayOfObject, 0, b, 0, i2);
          }
          k = m;
          if (paramInt < m)
          {
            k = paramInt + 1;
            int[] arrayOfInt = c;
            i3 = m - paramInt;
            System.arraycopy(localObject2, k, arrayOfInt, paramInt, i3);
            System.arraycopy(arrayOfObject, k << 1, b, i2, i3 << 1);
            k = m;
          }
        }
        else
        {
          throw new ConcurrentModificationException();
        }
      }
      else
      {
        if (paramInt < m)
        {
          localObject2 = c;
          k = paramInt + 1;
          i3 = m - paramInt;
          System.arraycopy(localObject2, k, localObject2, paramInt, i3);
          localObject2 = b;
          System.arraycopy(localObject2, k << 1, localObject2, i2, i3 << 1);
        }
        localObject2 = b;
        paramInt = m << 1;
        localObject2[paramInt] = null;
        localObject2[(paramInt + 1)] = null;
        k = m;
      }
    }
    if (i1 == n)
    {
      n = k;
      return localObject1;
    }
    throw new ConcurrentModificationException();
  }
  
  public void clear()
  {
    int k = n;
    if (k > 0)
    {
      int[] arrayOfInt = c;
      Object[] arrayOfObject = b;
      c = ContainerHelpers.c;
      b = ContainerHelpers.b;
      n = 0;
      a(arrayOfInt, arrayOfObject, k);
    }
    if (n <= 0) {
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public void clear(int paramInt)
  {
    int k = n;
    int[] arrayOfInt = c;
    if (arrayOfInt.length < paramInt)
    {
      Object[] arrayOfObject = b;
      init(paramInt);
      if (n > 0)
      {
        System.arraycopy(arrayOfInt, 0, c, 0, k);
        System.arraycopy(arrayOfObject, 0, b, 0, k << 1);
      }
      a(arrayOfInt, arrayOfObject, k);
    }
    if (n == k) {
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return indexOfKey(paramObject) >= 0;
  }
  
  public boolean containsValue(Object paramObject)
  {
    return indexOfValue(paramObject) >= 0;
  }
  
  /* Error */
  public boolean equals(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: if_acmpne +5 -> 7
    //   5: iconst_1
    //   6: ireturn
    //   7: aload_1
    //   8: instanceof 2
    //   11: ifeq +105 -> 116
    //   14: aload_1
    //   15: checkcast 2	com/org/android/util/SimpleArrayMap
    //   18: astore_1
    //   19: aload_0
    //   20: invokevirtual 90	com/org/android/util/SimpleArrayMap:size	()I
    //   23: aload_1
    //   24: invokevirtual 90	com/org/android/util/SimpleArrayMap:size	()I
    //   27: if_icmpeq +5 -> 32
    //   30: iconst_0
    //   31: ireturn
    //   32: iconst_0
    //   33: istore_2
    //   34: aload_0
    //   35: getfield 29	com/org/android/util/SimpleArrayMap:n	I
    //   38: istore_3
    //   39: iload_2
    //   40: iload_3
    //   41: if_icmpge +73 -> 114
    //   44: aload_0
    //   45: iload_2
    //   46: invokevirtual 93	com/org/android/util/SimpleArrayMap:keyAt	(I)Ljava/lang/Object;
    //   49: astore 5
    //   51: aload_0
    //   52: iload_2
    //   53: invokevirtual 96	com/org/android/util/SimpleArrayMap:valueAt	(I)Ljava/lang/Object;
    //   56: astore 6
    //   58: aload_1
    //   59: aload 5
    //   61: invokevirtual 100	com/org/android/util/SimpleArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   64: astore 7
    //   66: aload 6
    //   68: ifnonnull +23 -> 91
    //   71: aload 7
    //   73: ifnonnull +170 -> 243
    //   76: aload_1
    //   77: aload 5
    //   79: invokevirtual 102	com/org/android/util/SimpleArrayMap:containsKey	(Ljava/lang/Object;)Z
    //   82: istore 4
    //   84: iload 4
    //   86: ifne +21 -> 107
    //   89: iconst_0
    //   90: ireturn
    //   91: aload 6
    //   93: aload 7
    //   95: invokevirtual 64	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   98: istore 4
    //   100: iload 4
    //   102: ifne +5 -> 107
    //   105: iconst_0
    //   106: ireturn
    //   107: iload_2
    //   108: iconst_1
    //   109: iadd
    //   110: istore_2
    //   111: goto -77 -> 34
    //   114: iconst_1
    //   115: ireturn
    //   116: aload_1
    //   117: instanceof 104
    //   120: ifeq +111 -> 231
    //   123: aload_1
    //   124: checkcast 104	java/util/Map
    //   127: astore_1
    //   128: aload_0
    //   129: invokevirtual 90	com/org/android/util/SimpleArrayMap:size	()I
    //   132: aload_1
    //   133: invokeinterface 105 1 0
    //   138: if_icmpeq +5 -> 143
    //   141: iconst_0
    //   142: ireturn
    //   143: iconst_0
    //   144: istore_2
    //   145: aload_0
    //   146: getfield 29	com/org/android/util/SimpleArrayMap:n	I
    //   149: istore_3
    //   150: iload_2
    //   151: iload_3
    //   152: if_icmpge +77 -> 229
    //   155: aload_0
    //   156: iload_2
    //   157: invokevirtual 93	com/org/android/util/SimpleArrayMap:keyAt	(I)Ljava/lang/Object;
    //   160: astore 5
    //   162: aload_0
    //   163: iload_2
    //   164: invokevirtual 96	com/org/android/util/SimpleArrayMap:valueAt	(I)Ljava/lang/Object;
    //   167: astore 6
    //   169: aload_1
    //   170: aload 5
    //   172: invokeinterface 106 2 0
    //   177: astore 7
    //   179: aload 6
    //   181: ifnonnull +25 -> 206
    //   184: aload 7
    //   186: ifnonnull +57 -> 243
    //   189: aload_1
    //   190: aload 5
    //   192: invokeinterface 107 2 0
    //   197: istore 4
    //   199: iload 4
    //   201: ifne +21 -> 222
    //   204: iconst_0
    //   205: ireturn
    //   206: aload 6
    //   208: aload 7
    //   210: invokevirtual 64	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   213: istore 4
    //   215: iload 4
    //   217: ifne +5 -> 222
    //   220: iconst_0
    //   221: ireturn
    //   222: iload_2
    //   223: iconst_1
    //   224: iadd
    //   225: istore_2
    //   226: goto -81 -> 145
    //   229: iconst_1
    //   230: ireturn
    //   231: iconst_0
    //   232: ireturn
    //   233: astore_1
    //   234: iconst_0
    //   235: ireturn
    //   236: astore_1
    //   237: iconst_0
    //   238: ireturn
    //   239: astore_1
    //   240: iconst_0
    //   241: ireturn
    //   242: astore_1
    //   243: iconst_0
    //   244: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	245	0	this	SimpleArrayMap
    //   0	245	1	paramObject	Object
    //   33	193	2	k	int
    //   38	115	3	m	int
    //   82	134	4	bool	boolean
    //   49	142	5	localObject1	Object
    //   56	151	6	localObject2	Object
    //   64	145	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   34	39	233	java/lang/NullPointerException
    //   44	66	233	java/lang/NullPointerException
    //   76	84	233	java/lang/NullPointerException
    //   91	100	233	java/lang/NullPointerException
    //   44	66	236	java/lang/ClassCastException
    //   76	84	236	java/lang/ClassCastException
    //   91	100	236	java/lang/ClassCastException
    //   145	150	239	java/lang/NullPointerException
    //   155	179	239	java/lang/NullPointerException
    //   189	199	239	java/lang/NullPointerException
    //   206	215	239	java/lang/NullPointerException
    //   155	179	242	java/lang/ClassCastException
    //   189	199	242	java/lang/ClassCastException
    //   206	215	242	java/lang/ClassCastException
  }
  
  public Object get(Object paramObject)
  {
    int k = indexOfKey(paramObject);
    if (k >= 0) {
      return b[((k << 1) + 1)];
    }
    return null;
  }
  
  public int hashCode()
  {
    int[] arrayOfInt = c;
    Object[] arrayOfObject = b;
    int i3 = n;
    int m = 0;
    int i1 = 0;
    int k = 1;
    while (m < i3)
    {
      Object localObject = arrayOfObject[k];
      int i4 = arrayOfInt[m];
      int i2;
      if (localObject == null) {
        i2 = 0;
      } else {
        i2 = localObject.hashCode();
      }
      i1 += (i2 ^ i4);
      m += 1;
      k += 2;
    }
    return i1;
  }
  
  public int indexOfKey(Object paramObject)
  {
    if (paramObject == null) {
      return a();
    }
    return a(paramObject, paramObject.hashCode());
  }
  
  int indexOfValue(Object paramObject)
  {
    int m = n * 2;
    Object[] arrayOfObject = b;
    if (paramObject == null)
    {
      k = 1;
      while (k < m)
      {
        if (arrayOfObject[k] == null) {
          return k >> 1;
        }
        k += 2;
      }
    }
    int k = 1;
    while (k < m)
    {
      if (paramObject.equals(arrayOfObject[k])) {
        return k >> 1;
      }
      k += 2;
    }
    return -1;
  }
  
  public boolean isEmpty()
  {
    return n <= 0;
  }
  
  public Object keyAt(int paramInt)
  {
    return b[(paramInt << 1)];
  }
  
  public Object put(Object paramObject1, Object paramObject2)
  {
    int i1 = n;
    int m;
    if (paramObject1 == null)
    {
      k = a();
      m = 0;
    }
    else
    {
      m = paramObject1.hashCode();
      k = a(paramObject1, m);
    }
    Object localObject;
    if (k >= 0)
    {
      k = (k << 1) + 1;
      paramObject1 = b;
      localObject = paramObject1[k];
      paramObject1[k] = paramObject2;
      return localObject;
    }
    int i2 = k;
    if (i1 >= c.length)
    {
      k = 4;
      if (i1 >= 8) {
        k = (i1 >> 1) + i1;
      } else if (i1 >= 4) {
        k = 8;
      }
      localObject = c;
      Object[] arrayOfObject = b;
      init(k);
      if (i1 == n)
      {
        int[] arrayOfInt = c;
        if (arrayOfInt.length > 0)
        {
          System.arraycopy(localObject, 0, arrayOfInt, 0, localObject.length);
          System.arraycopy(arrayOfObject, 0, b, 0, arrayOfObject.length);
        }
        a((int[])localObject, arrayOfObject, i1);
      }
      else
      {
        throw new ConcurrentModificationException();
      }
    }
    if (i2 < i1)
    {
      localObject = c;
      k = i2 + 1;
      System.arraycopy(localObject, i2, localObject, k, i1 - i2);
      localObject = b;
      System.arraycopy(localObject, i2 << 1, localObject, k << 1, n - i2 << 1);
    }
    int k = n;
    if (i1 == k)
    {
      localObject = c;
      if (i2 < localObject.length)
      {
        localObject[i2] = m;
        localObject = b;
        m = i2 << 1;
        localObject[m] = paramObject1;
        localObject[(m + 1)] = paramObject2;
        n = (k + 1);
        return null;
      }
    }
    throw new ConcurrentModificationException();
  }
  
  public Object remove(Object paramObject)
  {
    int k = indexOfKey(paramObject);
    if (k >= 0) {
      return a(k);
    }
    return null;
  }
  
  public Object setValueAt(int paramInt, Object paramObject)
  {
    paramInt = (paramInt << 1) + 1;
    Object[] arrayOfObject = b;
    Object localObject = arrayOfObject[paramInt];
    arrayOfObject[paramInt] = paramObject;
    return localObject;
  }
  
  public int size()
  {
    return n;
  }
  
  public String toString()
  {
    if (isEmpty()) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder(n * 28);
    localStringBuilder.append('{');
    int k = 0;
    while (k < n)
    {
      if (k > 0) {
        localStringBuilder.append(", ");
      }
      Object localObject = keyAt(k);
      if (localObject != this) {
        localStringBuilder.append(localObject);
      } else {
        localStringBuilder.append("(this Map)");
      }
      localStringBuilder.append('=');
      localObject = valueAt(k);
      if (localObject != this) {
        localStringBuilder.append(localObject);
      } else {
        localStringBuilder.append("(this Map)");
      }
      k += 1;
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public Object valueAt(int paramInt)
  {
    return b[((paramInt << 1) + 1)];
  }
}

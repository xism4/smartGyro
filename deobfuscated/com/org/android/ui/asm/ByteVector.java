package com.org.android.ui.asm;

import java.lang.reflect.Array;

final class ByteVector
{
  public static int[] get(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = paramArrayOfInt;
    if (paramInt1 + 1 > paramArrayOfInt.length)
    {
      arrayOfInt = new int[read(paramInt1)];
      System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, paramInt1);
    }
    arrayOfInt[paramInt1] = paramInt2;
    return arrayOfInt;
  }
  
  public static int read(int paramInt)
  {
    if (paramInt <= 4) {
      return 8;
    }
    return paramInt * 2;
  }
  
  public static Object[] read(Object[] paramArrayOfObject, int paramInt, Object paramObject)
  {
    Object[] arrayOfObject = paramArrayOfObject;
    if (paramInt + 1 > paramArrayOfObject.length)
    {
      arrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), read(paramInt));
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramInt);
    }
    arrayOfObject[paramInt] = paramObject;
    return arrayOfObject;
  }
}

package com.org.android.util;

public class LongSparseArray<E>
  implements Cloneable
{
  private static final Object DELETED = new Object();
  private boolean mGarbage = false;
  private long[] mKeys;
  private int mSize;
  private Object[] mValues;
  
  public LongSparseArray()
  {
    this(10);
  }
  
  public LongSparseArray(int paramInt)
  {
    if (paramInt == 0)
    {
      mKeys = ContainerHelpers.t;
      mValues = ContainerHelpers.b;
    }
    else
    {
      paramInt = ContainerHelpers.idealLongArraySize(paramInt);
      mKeys = new long[paramInt];
      mValues = new Object[paramInt];
    }
    mSize = 0;
  }
  
  private void gc()
  {
    int m = mSize;
    long[] arrayOfLong = mKeys;
    Object[] arrayOfObject = mValues;
    int i = 0;
    int k;
    for (int j = 0; i < m; j = k)
    {
      Object localObject = arrayOfObject[i];
      k = j;
      if (localObject != DELETED)
      {
        if (i != j)
        {
          arrayOfLong[j] = arrayOfLong[i];
          arrayOfObject[j] = localObject;
          arrayOfObject[i] = null;
        }
        k = j + 1;
      }
      i += 1;
    }
    mGarbage = false;
    mSize = j;
  }
  
  public void append(long paramLong, Object paramObject)
  {
    int i = mSize;
    if ((i != 0) && (paramLong <= mKeys[(i - 1)]))
    {
      put(paramLong, paramObject);
      return;
    }
    if ((mGarbage) && (mSize >= mKeys.length)) {
      gc();
    }
    i = mSize;
    if (i >= mKeys.length)
    {
      int j = ContainerHelpers.idealLongArraySize(i + 1);
      long[] arrayOfLong = new long[j];
      Object[] arrayOfObject = new Object[j];
      Object localObject = mKeys;
      System.arraycopy(localObject, 0, arrayOfLong, 0, localObject.length);
      localObject = mValues;
      System.arraycopy(localObject, 0, arrayOfObject, 0, localObject.length);
      mKeys = arrayOfLong;
      mValues = arrayOfObject;
    }
    mKeys[i] = paramLong;
    mValues[i] = paramObject;
    mSize = (i + 1);
  }
  
  public LongSparseArray clone()
  {
    try
    {
      Object localObject1 = super.clone();
      localObject1 = (LongSparseArray)localObject1;
      Object localObject2 = mKeys;
      localObject2 = localObject2.clone();
      mKeys = ((long[])localObject2);
      localObject2 = mValues;
      localObject2 = localObject2.clone();
      mValues = ((Object[])localObject2);
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }
  
  public void delete(long paramLong)
  {
    int i = ContainerHelpers.binarySearch(mKeys, mSize, paramLong);
    if (i >= 0)
    {
      Object[] arrayOfObject = mValues;
      Object localObject1 = arrayOfObject[i];
      Object localObject2 = DELETED;
      if (localObject1 != localObject2)
      {
        arrayOfObject[i] = localObject2;
        mGarbage = true;
      }
    }
  }
  
  public Object get(long paramLong)
  {
    return get(paramLong, null);
  }
  
  public Object get(long paramLong, Object paramObject)
  {
    int i = ContainerHelpers.binarySearch(mKeys, mSize, paramLong);
    Object localObject = paramObject;
    if (i >= 0)
    {
      localObject = mValues;
      if (localObject[i] == DELETED) {
        return paramObject;
      }
      localObject = localObject[i];
    }
    return localObject;
  }
  
  public long keyAt(int paramInt)
  {
    if (mGarbage) {
      gc();
    }
    return mKeys[paramInt];
  }
  
  public void put(long paramLong, Object paramObject)
  {
    int i = ContainerHelpers.binarySearch(mKeys, mSize, paramLong);
    if (i >= 0)
    {
      mValues[i] = paramObject;
      return;
    }
    int j = i;
    Object localObject1;
    if (j < mSize)
    {
      localObject1 = mValues;
      if (localObject1[j] == DELETED)
      {
        mKeys[j] = paramLong;
        localObject1[j] = paramObject;
        return;
      }
    }
    i = j;
    if (mGarbage)
    {
      i = j;
      if (mSize >= mKeys.length)
      {
        gc();
        i = ContainerHelpers.binarySearch(mKeys, mSize, paramLong);
      }
    }
    j = mSize;
    if (j >= mKeys.length)
    {
      j = ContainerHelpers.idealLongArraySize(j + 1);
      localObject1 = new long[j];
      Object[] arrayOfObject = new Object[j];
      Object localObject2 = mKeys;
      System.arraycopy(localObject2, 0, localObject1, 0, localObject2.length);
      localObject2 = mValues;
      System.arraycopy(localObject2, 0, arrayOfObject, 0, localObject2.length);
      mKeys = ((long[])localObject1);
      mValues = arrayOfObject;
    }
    j = mSize;
    if (j - i != 0)
    {
      localObject1 = mKeys;
      int k = i + 1;
      System.arraycopy(localObject1, i, localObject1, k, j - i);
      localObject1 = mValues;
      System.arraycopy(localObject1, i, localObject1, k, mSize - i);
    }
    mKeys[i] = paramLong;
    mValues[i] = paramObject;
    mSize += 1;
  }
  
  public int size()
  {
    if (mGarbage) {
      gc();
    }
    return mSize;
  }
  
  public String toString()
  {
    if (size() <= 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder(mSize * 28);
    localStringBuilder.append('{');
    int i = 0;
    while (i < mSize)
    {
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(keyAt(i));
      localStringBuilder.append('=');
      Object localObject = valueAt(i);
      if (localObject != this) {
        localStringBuilder.append(localObject);
      } else {
        localStringBuilder.append("(this Map)");
      }
      i += 1;
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public Object valueAt(int paramInt)
  {
    if (mGarbage) {
      gc();
    }
    return mValues[paramInt];
  }
}

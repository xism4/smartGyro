package com.org.v4.graphics.drawable;

import a.a.c.f.d;
import a.a.c.f.j;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import com.org.android.util.ByteVector;
import com.org.android.util.LongSparseArray;

class ClassWriter
  extends Document
{
  d<Long> a;
  j<Integer> x;
  
  ClassWriter(ClassWriter paramClassWriter, VectorDrawableCompat paramVectorDrawableCompat, Resources paramResources)
  {
    super(paramClassWriter, paramVectorDrawableCompat, paramResources);
    if (paramClassWriter != null)
    {
      a = a;
      paramClassWriter = x;
    }
    else
    {
      a = new LongSparseArray();
      paramClassWriter = new ByteVector();
    }
    x = paramClassWriter;
  }
  
  private static long add(int paramInt1, int paramInt2)
  {
    long l = paramInt1;
    return paramInt2 | l << 32;
  }
  
  int a(int paramInt)
  {
    if (paramInt < 0) {
      return 0;
    }
    return ((Integer)x.add(paramInt, Integer.valueOf(0))).intValue();
  }
  
  int a(int paramInt1, int paramInt2)
  {
    long l = add(paramInt1, paramInt2);
    return (int)((Long)a.get(l, Long.valueOf(-1L))).longValue();
  }
  
  int a(int[] paramArrayOfInt, Drawable paramDrawable, int paramInt)
  {
    int i = super.write(paramArrayOfInt, paramDrawable);
    x.a(i, Integer.valueOf(paramInt));
    return i;
  }
  
  int get(int paramInt1, int paramInt2, Drawable paramDrawable, boolean paramBoolean)
  {
    int i = super.init(paramDrawable);
    long l2 = add(paramInt1, paramInt2);
    long l1;
    if (paramBoolean) {
      l1 = 8589934592L;
    } else {
      l1 = 0L;
    }
    paramDrawable = a;
    long l3 = i;
    paramDrawable.append(l2, Long.valueOf(l3 | l1));
    if (paramBoolean)
    {
      l2 = add(paramInt2, paramInt1);
      a.append(l2, Long.valueOf(0x100000000 | l3 | l1));
    }
    return i;
  }
  
  int get(int[] paramArrayOfInt)
  {
    int i = super.indexOf(paramArrayOfInt);
    if (i >= 0) {
      return i;
    }
    return super.indexOf(StateSet.WILD_CARD);
  }
  
  boolean get(int paramInt1, int paramInt2)
  {
    long l = add(paramInt1, paramInt2);
    return (((Long)a.get(l, Long.valueOf(-1L))).longValue() & 0x200000000) != 0L;
  }
  
  void init()
  {
    a = a.clone();
    x = x.clone();
  }
  
  public Drawable newDrawable()
  {
    return new VectorDrawableCompat(this, null);
  }
  
  public Drawable newDrawable(Resources paramResources)
  {
    return new VectorDrawableCompat(this, paramResources);
  }
  
  boolean put(int paramInt1, int paramInt2)
  {
    long l = add(paramInt1, paramInt2);
    return (((Long)a.get(l, Long.valueOf(-1L))).longValue() & 0x100000000) != 0L;
  }
}

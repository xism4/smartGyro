package com.org.android.ui.asm;

import java.util.List;

final class Item
{
  final float[] a;
  final int[] k;
  
  Item(int paramInt1, int paramInt2)
  {
    k = new int[] { paramInt1, paramInt2 };
    a = new float[] { 0.0F, 1.0F };
  }
  
  Item(int paramInt1, int paramInt2, int paramInt3)
  {
    k = new int[] { paramInt1, paramInt2, paramInt3 };
    a = new float[] { 0.0F, 0.5F, 1.0F };
  }
  
  Item(List paramList1, List paramList2)
  {
    int j = paramList1.size();
    k = new int[j];
    a = new float[j];
    int i = 0;
    while (i < j)
    {
      k[i] = ((Integer)paramList1.get(i)).intValue();
      a[i] = ((Float)paramList2.get(i)).floatValue();
      i += 1;
    }
  }
}
